/* ******************************************************************************
 * Copyright (c) 2014 - 2015 Fabian Prasser.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Fabian Prasser - initial API and implementation
 * ****************************************************************************
 */

package de.linearbits.preferences;

/**
 * Interface for validators
 * 
 * @author Fabian Prasser
 */
interface Validator<T> {

    /**
     * Is the given value valid
     * @param s
     * @return
     */
    public abstract boolean isValid(final T s);
}
