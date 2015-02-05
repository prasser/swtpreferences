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
 * A preference for double variables
 * @author Fabian Prasser
 */
public abstract class PreferenceDouble extends Preference<Double> {

    /** Validator*/
    private Validator<Double> validator = null;

    /**
     * Constructor
     * @param label
     */
    public PreferenceDouble(String label) {
        super(label);
    }

    /**
     * Constructor
     * @param label
     * @param min
     * @param max
     */
    public PreferenceDouble(String label, double min, double max) {
        super(label);
        this.validator = new ValidatorDouble(min, max);
    }

    @Override
    protected Editor<Double> getEditor() {
        return new EditorDouble(getDialog(), getValidator());
    }

    @Override
    Validator<Double> getValidator() {
        return validator;
    }

}
