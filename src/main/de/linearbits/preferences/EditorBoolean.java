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

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * Editor for boolean variables
 * @author Fabian Prasser
 */
class EditorBoolean extends Editor<Boolean> {

    /** Widget*/
    private Button checkbox;

    /** Label Visibility*/
    private Boolean hideLabel;

    /**
     * Constructor
     * @param dialog
     */
    public EditorBoolean(PreferencesDialog dialog, Boolean _default, boolean hideLabel ) {
        super(dialog, null, _default);
        this.hideLabel = hideLabel;
    }

    @Override
    void createControl(final Composite parent) {
        checkbox = new Button(parent, SWT.CHECK);
        checkbox.setSelection(false);
        if (! hideLabel) {
            checkbox.setText(this.getDialog().getConfiguration().getStringNo());
        }else {
        	checkbox.setText("");
        }
        checkbox.setLayoutData(GridDataFactory.swtDefaults().grab(true, false).indent(0, 0).align(SWT.FILL, SWT.FILL).create());
        checkbox.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                setValid(true);
                update();
                if (! hideLabel) {
	                if  (checkbox.getSelection()) {
	                    checkbox.setText(getDialog().getConfiguration().getStringYes());
	                } else {
	                    checkbox.setText(getDialog().getConfiguration().getStringNo());
	                };
                }else {
                	checkbox.setText("");	
                };
            };
        });
        
        super.createUndoButton(parent);
        super.createDefaultButton(parent);
        super.update();
    }

    @Override
    String format(Boolean b) {
        return b.toString();
    }

    @Override
    Boolean getValue() {
        return checkbox.getSelection();
    }

    @Override
    Boolean parse(final String s) {
        if (s.equals(Boolean.TRUE.toString())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    void setValue(Object t) {
        setInitialValue((Boolean) t);
        checkbox.setSelection((Boolean) t);
        if (! hideLabel){
           checkbox.setText((Boolean) t ? getDialog().getConfiguration().getStringYes() : getDialog().getConfiguration().getStringNo());
        }else {
        	checkbox.setText("");
        }
        super.update();
    }
}