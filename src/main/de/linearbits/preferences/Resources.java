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

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

/**
 * This class provides access to basic resources.
 *
 * @author Fabian Prasser
 */
public class Resources {

    /** Image*/
    private static Image imageUndo    = null;
    /** Image*/
    private static Image imageDefault = null;

    /**
     * Returns an image
     * @return
     */
    public static Image getImageUndo() {
        if (imageUndo == null || imageUndo.isDisposed()) {
            imageUndo = getImage("undo.png");
        }
        return imageUndo;
    }

    /**
     * Returns an image
     * @return
     */
    public static Image getImageDefault() {
        if (imageDefault == null || imageDefault.isDisposed()) {
            imageDefault = getImage("default.png");
        }
        return imageDefault;
    }

    /**
     * Loads an image. Adds a dispose listener that disposes the image when the display is disposed
     * @param display
     * @param resource
     * @return
     */
    private static final Image getImage(String resource) {
        final Image image = new Image(Display.getCurrent(), Resources.class.getResourceAsStream(resource));
        Display.getCurrent().addListener(SWT.Dispose, new Listener() {
            public void handleEvent(Event arg0) {
                if (image != null && !image.isDisposed()) {
                    image.dispose();
                }
            }
        });
        return image;
    }
}
