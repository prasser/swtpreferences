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
 * A preference for integer variables
 * @author Fabian Prasser
 */
public abstract class PreferenceInteger extends Preference<Integer> {

    /** Validator*/
    private Validator<Integer> validator = null;

    /**
     * Constructor
     * @param label
     */
    public PreferenceInteger(String label) {
        super(label);
    }

    /**
     * Constructor
     * @param label
     * @param min
     * @param max
     */
    public PreferenceInteger(String label, int min, int max) {
        super(label);
        this.validator = new ValidatorInteger(min, max);
    }

    @Override
    protected Editor<Integer> getEditor() {
        return new EditorInteger(getDialog(), getValidator());
    }

    @Override
    Validator<Integer> getValidator() {
        return validator;
    }
}
