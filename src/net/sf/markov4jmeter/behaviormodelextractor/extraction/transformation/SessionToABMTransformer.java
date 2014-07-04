package net.sf.markov4jmeter.behaviormodelextractor.extraction.transformation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.EList;

import dynamod.behavior.BehaviorFactory;
import dynamod.behavior.BehaviorModelAbsolute;
import dynamod.behavior.ObservedUseCaseExecution;
import dynamod.behavior.Session;
import dynamod.behavior.Transition;
import dynamod.behavior.UseCase;
import dynamod.behavior.Vertex;

/**
 * This class provides methods for transforming user session traces to
 * "absolute" Behavior Models.
 *
 * <p>Absolute Behavior Models are equipped with "absolute" probabilities and a
 * set of time ranges for each transition. Absolute probability values denote
 * the number of transition-occurrences within a session; they serve as basis
 * for the calculation of actual probabilities. The time ranges serve as basis
 * for the calculation of think times.
 *
 * @author Eike Schulz (esc@informatik.uni-kiel.de)
 *
 * @version 1.0
 */
public class SessionToABMTransformer {


    /* *****************************  constants  **************************** */


    /** Warning message for the case that a negative time range has been
     *  detected. */
    private final static String WARNING_NEGATIVE_TIME_RANGE =
            "Warning: negative time range detected in transition from state "
            + "\"%s\" to state \"%s\" in session \"%s\"; range will be ignored";


    /* **************************  public methods  ************************** */


    /**
     * Transforms a given set of session records to (absolute) Behavior Models.
     * Each transition of the resulting Behavior Model will be equipped with an
     * "absolute" probability and a set of time ranges.
     *
     * <p> A list of use cases might be passed additionally, for associated
     * vertices being added to each Behavior Model by default; such use cases
     * might originate from a template matrix, which possibly provides
     * additional uses case that are not included to monitored data. if
     * <code>null</code> is passed, the transformation starts with an empty set
     * of vertices.
     *
     * @param sessionRepository
     *     the session repository, which provides the sessions.
     *
     * @return
     *     the resulting Behavior Models.
     */
    public BehaviorModelAbsolute[] transformSessionsToBehaviorModels (
            final List<Session> sessions,
            final List<UseCase> defaultUseCases) {

        final ArrayList<BehaviorModelAbsolute> behaviorModelsAbsolute =
                new ArrayList<BehaviorModelAbsolute>();

        for (final Session session : sessions) {

            final BehaviorModelAbsolute behaviorModelAbsolute =
                    this.transformSessionToBehaviorModel(
                            session,
                            defaultUseCases);

            behaviorModelsAbsolute.add(behaviorModelAbsolute);
        }
        return behaviorModelsAbsolute.toArray( new BehaviorModelAbsolute[]{} );
    }


    /* **************************  private methods  ************************* */


    /**
     * Transforms a given session record to an (absolute) Behavior Model. Each
     * transition of the resulting Behavior Model will be equipped with an
     * "absolute" probability and a set of time ranges.
     *
     * <p> A list of use cases might be passed additionally, for associated
     * vertices being added by default; such use cases might originate from a
     * template matrix, which possibly provides additional uses case that are
     * not included to monitored data. if <code>null</code> is passed, the
     * transformation starts with an empty set of vertices.
     *
     * @param session
     *     session to be transformed to a Behavior Model.
     * @param defaultUseCases
     *     list of use cases, which indicate vertices to be created by default.
     *
     * @return
     *     the resulting Behavior Model.
     */
    private BehaviorModelAbsolute transformSessionToBehaviorModel (
            final Session session,
            final List<UseCase> defaultUseCases) {

        final BehaviorFactory factory = BehaviorFactory.eINSTANCE;

        final BehaviorModelAbsolute behaviorModelAbsolute =
                factory.createBehaviorModelAbsolute();

        // collect the session-related vertices including their transitions;
        final LinkedList<Vertex> vertices =
                this.transformSessionToVertices(session, defaultUseCases);

        // fill the absolute Behavior Model with vertices (and transitions);
        behaviorModelAbsolute.getVertices().addAll(vertices);

        return behaviorModelAbsolute;
    }

    /**
     * Transforms a given session record to Behavior Model vertices, including
     * their outgoing transitions. Each transition will be equipped with an
     * "absolute" probability value and a set of time ranges.
     *
     * <p> A list of use cases might be passed additionally, for associated
     * vertices being added by default; such use cases might originate from a
     * template matrix, which possibly provides additional uses case that are
     * not included to monitored data. if <code>null</code> is passed, the
     * transformation starts with an empty set of vertices.
     *
     * @param session
     *     session to be transformed to Behavior Model vertices.
     * @param defaultUseCases
     *     list of use cases, which indicate vertices to be created by default.
     *
     * @return
     *     the resulting vertices, including their outgoing transitions.
     */
    private LinkedList<Vertex> transformSessionToVertices (
            final Session session,
            final List<UseCase> defaultUseCases) {

        final LinkedList<Vertex> vertices = new LinkedList<Vertex>();

        if (defaultUseCases != null) {

            Vertex vertex;

            for (final UseCase useCase : defaultUseCases) {

                vertex = this.createVertex(useCase);
                vertices.add(vertex);
            }
        }

        // temporary variables;
        ObservedUseCaseExecution srcUCExecution = null;
        Vertex dstVertex = null;

        for (final ObservedUseCaseExecution dstUCExecution :
             session.getObservedUseCaseExecutions() ) {

            dstVertex = this.findVertex(dstUCExecution, vertices);

            if (dstVertex == null) {  // vertex not registered yet?

                dstVertex = this.createVertex( dstUCExecution.getUseCase() );
                vertices.add(dstVertex);
            }

            // in the first iteration (srcUCExecution == null), only one use
            // case is available and has been already stored as dstVertex
            // before -> no more vertices nor transitions need to be stored;
            if (srcUCExecution != null) {

                // srcVertex always exists, since it has been added (as
                // dstVertex) in the previous iteration;
                final Vertex srcVertex =
                        this.findVertex(srcUCExecution, vertices);

                Transition transition =
                        this.findTransition(srcVertex, dstVertex);

                if (transition == null) {  // transition not registered yet?

                    transition = this.installTransition(srcVertex, dstVertex);

                } else {  // transition is registered -> increase value by 1;

                    final double value = transition.getValue();

                    transition.setValue(value + 1);
                }

                // note that start times are considered only, since the duration
                // of a use case is included to the think time; for a separation
                // of use case duration and transition think time, the Protocol
                // Layer needs to provide a dedicated "use case think time" for
                // each state otherwise;

                final long timeDistance =
                        dstUCExecution.getStartTime() -
                        srcUCExecution.getStartTime();

                if (timeDistance < 0) {

                    // this case only occurs, if the monitoring data is invalid;
                    final String message = String.format(
                            SessionToABMTransformer.WARNING_NEGATIVE_TIME_RANGE,
                            srcUCExecution.getUseCase().getName(),
                            dstUCExecution.getUseCase().getName(),
                            session.getId());

                    System.out.println(message);

                } else {

                    transition.getTimes().add( new BigDecimal(timeDistance) );
                }
            }

            // shift vertices for next iteration;
            srcUCExecution = dstUCExecution;

        }  // for each;

        // add a transition from last use case vertex to final-state vertex;
        if (dstVertex != null) {

            // create a special vertex for the final state;
            final Vertex finalVertex = BehaviorFactory.eINSTANCE.createVertex();

            // register final-state vertex, whose use case is irrelevant;
            finalVertex.setUseCase(null);
            vertices.add(finalVertex);

            this.installTransition(dstVertex, finalVertex);
        }

        return vertices;
    }

    /**
     * Creates a new vertex which is associated with the given use case.
     *
     * @param useCase
     *     the use case for which a new vertex shall be created.
     *
     * @return
     *     the newly created vertex.
     */
    private Vertex createVertex (final UseCase useCase) {

        final BehaviorFactory factory = BehaviorFactory.eINSTANCE;
        final Vertex vertex = factory.createVertex();

        vertex.setUseCase(useCase);

        return vertex;
    }

    /**
     * Installs a new transition between two vertices; the (newly created)
     * transition will be added to the outgoing transitions of the source
     * vertex.
     *
     * @param sourceVertex  source vertex of the transition.
     * @param targetVertex  target vertex of the transition.
     *
     * @return  the installed transition.
     */
    private Transition installTransition (
            final Vertex sourceVertex,
            final Vertex targetVertex) {

        final BehaviorFactory factory = BehaviorFactory.eINSTANCE;
        final Transition transition = factory.createTransition();

        transition.setTargetVertex(targetVertex);
        transition.setValue(1);  // transition implies initial counter value 1;

        sourceVertex.getOutgoingTransitions().add(transition);

        return transition;
    }

    /**
     * Searches for a vertex which is associated with the given
     * <code>ObservedUseCaseExecution</code> instance.
     *
     * @param ouce
     *     instance which is associated with the vertex to be searched for.
     * @param vertices
     *     list of vertices to be searched through.
     *
     * @return
     *     a matching vertex, or <code>null</code> if such a vertex does not
     *     exist.
     */
    private Vertex findVertex (
            final ObservedUseCaseExecution ouce,
            final LinkedList<Vertex> vertices) {

        final String useCaseId = ouce.getUseCase().getId();

        return this.findVertexByUseCaseId(useCaseId, vertices);
    }


    /**
     * Searches for a vertex which is associated with a use case of certain ID.
     *
     * @param useCaseId
     *     ID of the use case.
     * @param vertices
     *     list of vertices to be searched through.
     *
     * @return
     *     a matching vertex, or <code>null</code> if such a vertex does not
     *     exist.
     */
    private Vertex findVertexByUseCaseId (
            final String useCaseId,
            final LinkedList<Vertex> vertices) {

        for (final Vertex vertex : vertices) {

            final UseCase vertexUseCase = vertex.getUseCase();

            if (vertexUseCase != null) {  // final vertex has no use case;

                final String vertexUseCaseId  = vertexUseCase.getId();

                if (useCaseId.equals(vertexUseCaseId)) {

                    return vertex;
                }
            }
        }

        return null;  // no matching vertex found;
    }

    /**
     * Searches for a transition between two given vertices.
     *
     * @param srcVertex
     *     source vertex of the searched transition.
     * @param dstVertex
     *     destination vertex of the searched transition.
     *
     * @return
     *     a transition between the given vertices, if one exists;
     *     otherwise <code>null</code> will be returned.
     */
    private Transition findTransition (
            final Vertex srcVertex,
            final Vertex dstVertex) {

        final EList<Transition> transitions =
                srcVertex.getOutgoingTransitions();

        for (final Transition transition : transitions) {

            if (dstVertex == transition.getTargetVertex()) {

                return transition;
            }
        }

        return null;  // no matching transition found;
    }
}
