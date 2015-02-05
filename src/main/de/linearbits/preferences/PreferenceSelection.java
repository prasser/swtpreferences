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
 * A preference for set-valued variables
 * @author Fabian Prasser
 */
public abstract class PreferenceSelection extends Preference<String> {

    /** Set*/
    private String[] elements;

    /**
     * Constructor
     * @param label
     * @param elements
     */
    public PreferenceSelection(String label, String[] elements) {
        super(label);
        this.elements = elements;
        if (elements == null || elements.length == 0) { throw new IllegalArgumentException("Elements must not be empty"); }
    }

    @Override
    protected Editor<String> getEditor() {
        return new EditorSelection(getDialog(), elements);
    }

    @Override
    protected Validator<String> getValidator() {
        return null;
    }
}
