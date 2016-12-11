/* ******************************************************************************
 * Copyright (c) 2014 - 2016 Fabian Prasser.
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
 * Validates integer input.
 *
 * @author Fabian Prasser
 */
class ValidatorInteger implements Validator<Integer> {

    /**  Bound */
    private final int min;

    /**  Bound */
    private final int max;

    /**
     * Creates a new instance.
     *
     * @param min
     * @param max
     */
    public ValidatorInteger(final int min, final int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean isValid(final Integer i) {
        return (i >= min) && (i <= max);
    }
}
