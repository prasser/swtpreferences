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
 * An abstract base class for preferences
 * @author Fabian Prasser
 *
 * @param <T>
 */
abstract class Preference<T> {

    /** Label*/
    private String            label;
    /** Dialog*/
    private PreferencesDialog dialog;
    /** Default*/
    private T                 _default = null;

    /**
     * Constructor
     * @param _default
     * @param label
     */
    Preference(String label, T _default) {
        this(label);
        this._default = _default;
    }
    
    /** 
     * Constructor
     * @param label
     */
    Preference(String label) {
        this.label = label;
        if (label == null) { throw new NullPointerException("Label must not be null"); }
    }

    /**
     * Retrieve value from the model
     * @return
     */
    protected abstract T getValue();

    /**
     * Write value to the model
     * @param t
     */
    protected abstract void setValue(Object t);

    /**
     * Returns the dialog
     * @return
     */
    PreferencesDialog getDialog() {
        return dialog;
    }

    /**
     * Returns the editor
     * @return
     */
    abstract Editor<T> getEditor();

    /**
     * Returns the label
     * @return
     */
    String getLabel() {
        return label;
    }

    /**
     * Provides the validator, if any
     * @return
     */
    abstract Validator<T> getValidator();

    /**
     * Called when the preference is added to the dialog
     * @param dialog
     */
    void setDialog(PreferencesDialog dialog) {
        this.dialog = dialog;
    }
    
    /**
     * Returns the default value, if any
     * @return
     */
    T getDefault() {
        return _default;
    }
}
