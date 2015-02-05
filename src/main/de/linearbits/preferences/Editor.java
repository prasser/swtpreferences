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

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * Abstract base class for an editor for a given data type.
 *
 * @author Fabian Prasser
 * @param <T>
 */
abstract class Editor<T> {

    /** Validator*/
    private Validator<T>      validator    = null;

    /** Model*/
    private T                 _default      = null;

    /** Dialog*/
    private PreferencesDialog dialog       = null;

    /** Initial value*/
    private T                 initialValue = null;

    /** Is valid*/
    private boolean           valid         = true;

    /** View*/
    private Button            buttonUndo    = null;

    /** View*/
    private Button            buttonDefault = null;
    
    /**
     * Constructor
     */
    public Editor(PreferencesDialog dialog, Validator<T> validator, T _default) {
        this.validator = validator;
        this.dialog = dialog;
        this._default = _default;
    }

    /**
     * Is the value accepted
     * @param t
     * @return
     */
    boolean accepts(String s) {
        try {
            T t = parse(s);
            if (t == null) return false;
            else if (validator != null && !validator.isValid(t)) return false;
            else return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Creates an according control.
     *
     * @param parent
     */
    abstract void createControl(Composite parent);

    /**
     * Format the value
     *
     * @param t
     * @return
     */
    abstract String format(T t);

    /**
     * @return the dialog
     */
    PreferencesDialog getDialog() {
        return dialog;
    }

    /**
     * Getter
     * @return
     */
    T getInitialValue() {
        return initialValue;
    }

    /**
     * Returns the current value.
     *
     * @return
     */
    abstract T getValue();

    /**
     * Has the value changed since the beginning
     * @return
     */
    boolean isDirty() {
        if (getInitialValue() == null) return false;
        else return !getInitialValue().equals(getValue());
    }

    /**
     * Is the current value valid
     * @return
     */
    boolean isValid() {
        return this.valid;
    }

    /**
     * Parse the value
     *
     * @param s
     * @return
     */
    abstract T parse(String s);

    /**
     * Setter
     * @param t
     */
    void setInitialValue(T t) {
        if (this.initialValue == null) {
            this.initialValue = t;
        }
    }

    /**
     * Setter
     * @param valid
     */
    void setValid(boolean valid) {
        this.valid = valid;
    }

    /**
     * Sets the value.
     *
     * @param t
     */
    abstract void setValue(Object t);

    /**
     * Updates the dialog
     */
    void update() {
        dialog.update();
        buttonUndo.setEnabled(isDirty() && getInitialValue()!=null);
        try {
            buttonDefault.setEnabled(_default != null && !getValue().equals(_default));
        } catch (Exception e){
            buttonDefault.setEnabled(false);
        }
    }
    
    /**
     * Creates a button
     * @param parent
     */
    void createUndoButton(Composite parent) {
        buttonUndo = new Button(parent, SWT.PUSH);
        buttonUndo.setImage(Resources.getImageUndo());
        buttonUndo.setLayoutData(GridDataFactory.swtDefaults().align(SWT.LEFT, SWT.TOP).create());
        buttonUndo.setToolTipText(dialog.getConfiguration().getStringUndo());
        buttonUndo.addSelectionListener(new SelectionAdapter(){
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                setValue(getInitialValue());
            }
        });
    }
    
    /**
     * Creates a button
     * @param parent
     */
    void createDefaultButton(Composite parent) {
        buttonDefault = new Button(parent, SWT.PUSH);
        buttonDefault.setImage(Resources.getImageDefault());
        buttonDefault.setLayoutData(GridDataFactory.swtDefaults().align(SWT.LEFT, SWT.TOP).create());
        buttonDefault.setToolTipText(dialog.getConfiguration().getStringDefault());
        buttonDefault.addSelectionListener(new SelectionAdapter(){
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                setValue(_default);
            }
        });
    }
}
