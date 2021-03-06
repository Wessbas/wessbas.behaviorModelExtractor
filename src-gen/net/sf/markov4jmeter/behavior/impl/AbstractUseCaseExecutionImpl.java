/***************************************************************************
 * Copyright (c) 2016 the WESSBAS project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/


package net.sf.markov4jmeter.behavior.impl;

import net.sf.markov4jmeter.behavior.AbstractUseCaseExecution;
import net.sf.markov4jmeter.behavior.BehaviorPackage;
import net.sf.markov4jmeter.behavior.UseCase;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Use Case Execution</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link net.sf.markov4jmeter.behavior.impl.AbstractUseCaseExecutionImpl#getUseCase <em>Use Case</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractUseCaseExecutionImpl extends EObjectImpl implements AbstractUseCaseExecution {
    /**
	 * The cached value of the '{@link #getUseCase() <em>Use Case</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getUseCase()
	 * @generated
	 * @ordered
	 */
    protected UseCase useCase;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected AbstractUseCaseExecutionImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    protected EClass eStaticClass() {
		return BehaviorPackage.Literals.ABSTRACT_USE_CASE_EXECUTION;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public UseCase getUseCase() {
		if (useCase != null && useCase.eIsProxy()) {
			InternalEObject oldUseCase = (InternalEObject)useCase;
			useCase = (UseCase)eResolveProxy(oldUseCase);
			if (useCase != oldUseCase) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, BehaviorPackage.ABSTRACT_USE_CASE_EXECUTION__USE_CASE, oldUseCase, useCase));
			}
		}
		return useCase;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public UseCase basicGetUseCase() {
		return useCase;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setUseCase(UseCase newUseCase) {
		UseCase oldUseCase = useCase;
		useCase = newUseCase;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BehaviorPackage.ABSTRACT_USE_CASE_EXECUTION__USE_CASE, oldUseCase, useCase));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BehaviorPackage.ABSTRACT_USE_CASE_EXECUTION__USE_CASE:
				if (resolve) return getUseCase();
				return basicGetUseCase();
		}
		return super.eGet(featureID, resolve, coreType);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case BehaviorPackage.ABSTRACT_USE_CASE_EXECUTION__USE_CASE:
				setUseCase((UseCase)newValue);
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
			case BehaviorPackage.ABSTRACT_USE_CASE_EXECUTION__USE_CASE:
				setUseCase((UseCase)null);
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
			case BehaviorPackage.ABSTRACT_USE_CASE_EXECUTION__USE_CASE:
				return useCase != null;
		}
		return super.eIsSet(featureID);
	}

} //AbstractUseCaseExecutionImpl
