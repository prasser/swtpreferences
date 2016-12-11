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
 * This class provides basic configuration options for the dialog
 * @author Fabian Prasser
 *
 */
public class PreferencesDialogConfiguration {

    /** Layout*/
    private int    minimalTextWidth  = 60;
    /** Layout*/
    private int    minimalTextHeight = 60;
    /** String*/
    private String yes               = "Yes";
    /** String*/
    private String no                = "No";
    /** String*/
    private String ok                = "OK";
    /** String*/
    private String undo              = "Undo changes";
    /** String*/
    private String _default          = "Set to default";

    /**
     * Returns the minimal height of a multi-line text field
     */
    public int getMinimalTextHeight() {
        return minimalTextHeight;
    }

    /**
     * Returns the minimal width of a text field
     */
    public int getMinimalTextWidth() {
        return minimalTextWidth;
    }

    /**
     * Returns the string for "no"
     * @return
     */
    public String getStringNo() {
        return this.no;
    }

    /**
     * Returns the string for "ok"
     * @return
     */
    public String getStringOK() {
        return this.ok;
    }

    /**
     * Returns the string for "yes"
     * @return
     */
    public String getStringYes() {
        return this.yes;
    }

    /**
     * Returns the string for "default"
     * @return
     */
    public String getStringDefault() {
        return this._default;
    }

    /**
     * Returns the string for "undo"
     * @return
     */
    public String getStringUndo() {
        return this.undo;
    }

    /**
     * Sets the minimal height of a multi-line text field
     */
    public void setMinimalTextHeight(int minimalTextHeight) {
        checkPositive(minimalTextHeight);
        this.minimalTextHeight = minimalTextHeight;
    }

    /**
     * Sets the minimal width of a text field
     */
    public void setMinimalTextWidth(int minimalTextWidth) {
        checkPositive(minimalTextWidth);
        this.minimalTextWidth = minimalTextWidth;
    }

    /**
     * Sets the string for "no"
     */
    public void setStringNo(String no) {
        checkNull(no);
        this.no = no;
    }

    /**
     * Sets the string for "ok"
     */
    public void setStringOK(String ok) {
        checkNull(ok);
        this.ok = ok;
    }

    /**
     * Sets the string for "yes"
     */
    public void setStringYes(String yes) {
        checkNull(yes);
        this.yes = yes;
    }

    /**
     * Sets the string for "default"
     */
    public void setStringDefault(String _default) {
        checkNull(_default);
        this._default = _default;
    }

    /**
     * Sets the string for "undo"
     */
    public void setStringUndo(String undo) {
        checkNull(undo);
        this.undo = undo;
    }

    /**
     * Check argument for validity
     * @param arg
     */
    private void checkNull(Object arg) {
        if (arg == null) { throw new NullPointerException("Argument must not be null"); }
    }

    /**
     * Check argument for validity
     * @param arg
     */
    private void checkPositive(int arg) {
        if (arg < 0) { throw new IllegalArgumentException("Argument must be a positive integer"); }
    }
}
