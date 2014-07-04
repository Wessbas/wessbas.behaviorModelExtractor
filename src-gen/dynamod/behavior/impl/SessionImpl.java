/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dynamod.behavior.impl;

import dynamod.behavior.BehaviorPackage;
import dynamod.behavior.ObservedUseCaseExecution;
import dynamod.behavior.Session;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Session</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link dynamod.behavior.impl.SessionImpl#getId <em>Id</em>}</li>
 *   <li>{@link dynamod.behavior.impl.SessionImpl#getStartTime <em>Start Time</em>}</li>
 *   <li>{@link dynamod.behavior.impl.SessionImpl#getEndTime <em>End Time</em>}</li>
 *   <li>{@link dynamod.behavior.impl.SessionImpl#getObservedUseCaseExecutions <em>Observed Use Case Executions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SessionImpl extends EObjectImpl implements Session {
    /**
     * The default value of the '{@link #getId() <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getId()
     * @generated
     * @ordered
     */
    protected static final String ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getId()
     * @generated
     * @ordered
     */
    protected String id = ID_EDEFAULT;

    /**
     * The default value of the '{@link #getStartTime() <em>Start Time</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStartTime()
     * @generated
     * @ordered
     */
    protected static final long START_TIME_EDEFAULT = 0L;

    /**
     * The cached value of the '{@link #getStartTime() <em>Start Time</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStartTime()
     * @generated
     * @ordered
     */
    protected long startTime = START_TIME_EDEFAULT;

    /**
     * The default value of the '{@link #getEndTime() <em>End Time</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEndTime()
     * @generated
     * @ordered
     */
    protected static final long END_TIME_EDEFAULT = 0L;

    /**
     * The cached value of the '{@link #getEndTime() <em>End Time</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEndTime()
     * @generated
     * @ordered
     */
    protected long endTime = END_TIME_EDEFAULT;

    /**
     * The cached value of the '{@link #getObservedUseCaseExecutions() <em>Observed Use Case Executions</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getObservedUseCaseExecutions()
     * @generated
     * @ordered
     */
    protected EList<ObservedUseCaseExecution> observedUseCaseExecutions;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SessionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return BehaviorPackage.Literals.SESSION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getId() {
        return id;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setId(String newId) {
        String oldId = id;
        id = newId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BehaviorPackage.SESSION__ID, oldId, id));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public long getStartTime() {
        return startTime;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setStartTime(long newStartTime) {
        long oldStartTime = startTime;
        startTime = newStartTime;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BehaviorPackage.SESSION__START_TIME, oldStartTime, startTime));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public long getEndTime() {
        return endTime;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEndTime(long newEndTime) {
        long oldEndTime = endTime;
        endTime = newEndTime;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BehaviorPackage.SESSION__END_TIME, oldEndTime, endTime));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<ObservedUseCaseExecution> getObservedUseCaseExecutions() {
        if (observedUseCaseExecutions == null) {
            observedUseCaseExecutions = new EObjectContainmentEList<ObservedUseCaseExecution>(ObservedUseCaseExecution.class, this, BehaviorPackage.SESSION__OBSERVED_USE_CASE_EXECUTIONS);
        }
        return observedUseCaseExecutions;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case BehaviorPackage.SESSION__OBSERVED_USE_CASE_EXECUTIONS:
                return ((InternalEList<?>)getObservedUseCaseExecutions()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case BehaviorPackage.SESSION__ID:
                return getId();
            case BehaviorPackage.SESSION__START_TIME:
                return getStartTime();
            case BehaviorPackage.SESSION__END_TIME:
                return getEndTime();
            case BehaviorPackage.SESSION__OBSERVED_USE_CASE_EXECUTIONS:
                return getObservedUseCaseExecutions();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case BehaviorPackage.SESSION__ID:
                setId((String)newValue);
                return;
            case BehaviorPackage.SESSION__START_TIME:
                setStartTime((Long)newValue);
                return;
            case BehaviorPackage.SESSION__END_TIME:
                setEndTime((Long)newValue);
                return;
            case BehaviorPackage.SESSION__OBSERVED_USE_CASE_EXECUTIONS:
                getObservedUseCaseExecutions().clear();
                getObservedUseCaseExecutions().addAll((Collection<? extends ObservedUseCaseExecution>)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case BehaviorPackage.SESSION__ID:
                setId(ID_EDEFAULT);
                return;
            case BehaviorPackage.SESSION__START_TIME:
                setStartTime(START_TIME_EDEFAULT);
                return;
            case BehaviorPackage.SESSION__END_TIME:
                setEndTime(END_TIME_EDEFAULT);
                return;
            case BehaviorPackage.SESSION__OBSERVED_USE_CASE_EXECUTIONS:
                getObservedUseCaseExecutions().clear();
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case BehaviorPackage.SESSION__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case BehaviorPackage.SESSION__START_TIME:
                return startTime != START_TIME_EDEFAULT;
            case BehaviorPackage.SESSION__END_TIME:
                return endTime != END_TIME_EDEFAULT;
            case BehaviorPackage.SESSION__OBSERVED_USE_CASE_EXECUTIONS:
                return observedUseCaseExecutions != null && !observedUseCaseExecutions.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (id: ");
        result.append(id);
        result.append(", startTime: ");
        result.append(startTime);
        result.append(", endTime: ");
        result.append(endTime);
        result.append(')');
        return result.toString();
    }

} //SessionImpl
