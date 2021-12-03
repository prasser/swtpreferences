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
 * A preference for boolean variables without label
 * @author Fabian Prasser
 * @author Ibraheem Al-Dhamari
 */
public abstract class PreferenceBooleanNL extends Preference<Boolean> {

    /**
     * Constructor
     * @param label
     * @param default
     */
    public PreferenceBooleanNL(String label, Boolean _default) {
        super(label, _default);
    }
    
    /**
     * Constructor
     * @param label
     */
    public PreferenceBooleanNL(String label) {
        super(label);
    }

    @Override
    protected Editor<Boolean> getEditor() {
        return new EditorBooleanNL(getDialog(), getDefault());
    }
    
    
    @Override
    protected Validator<Boolean> getValidator() {
        return null;
    }
}
