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
 * A preference for boolean variables
 * @author Fabian Prasser
 */
public abstract class PreferenceBoolean extends Preference<Boolean> {

	// view or hide the label in chkBox
	private Boolean hideLabel = false;
	
    /**
     * Constructor
     * @param label
     * @param default
     * @param hideLabel
     */
    public PreferenceBoolean(String label, Boolean _default, Boolean hideLabel) {
        super(label, _default);
        this.hideLabel = hideLabel;
    }
        
    /**
     * Constructor
     * @param label
     * @param default
     */
    public PreferenceBoolean(String label, Boolean _default ) {
        super(label, _default);
    }
        
    /**
     * Constructor
     * @param label
     */
    public PreferenceBoolean(String label) {
        super(label);
    }

    @Override
    protected Editor<Boolean> getEditor() {
        return new EditorBoolean(getDialog(), getDefault(),this.hideLabel);
    }

    @Override
    protected Validator<Boolean> getValidator() {
        return null;
    }

    public Boolean getHideLabel() {
		return hideLabel;
	}

	public void setHideLabel(Boolean hideLabel) {
		this.hideLabel = hideLabel;
		if (hideLabel == null) { throw new NullPointerException("hideLabel must not be null!"); }
	}
}