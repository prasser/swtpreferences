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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.ShellListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

/**
 * This class implements the preferences dialog
 * 
 * @author Fabian Prasser
 */
public class PreferencesDialog extends TitleAreaDialog {

    /** Model*/
    private PreferencesDialogConfiguration   config      = new PreferencesDialogConfiguration();
    /** Model*/
    private List<String>                     categories  = new ArrayList<String>();
    /** Model*/
    private Map<String, List<Preference<?>>> preferences = new HashMap<String, List<Preference<?>>>();
    /** Model*/
    private Map<Preference<?>, Editor<?>>    editors     = new HashMap<Preference<?>, Editor<?>>();
    /** Model*/
    private String                           category;
    /** Model*/
    private Map<String, Image>               images      = new HashMap<String, Image>();
    /** Model*/
    private String                           title;
    /** Model*/
    private String                           description;

    /** View*/
    private Shell                            shell;
    /** View*/
    private Button                           ok;
    /** View*/
    private TabFolder                        folder;

    /**
     * Constructor
     * @param shell
     * @param title
     * @param description
     */
    public PreferencesDialog(Shell shell, String title, String description) {
        super(shell);
        this.title = title;
        this.description = description;
        this.shell = shell;
    }

    /**
     * Adds a new category
     * @param label
     */
    public void addCategory(String label) {
        addCategory(label, null);
    }

    /**
     * Adds a new category
     * @param label
     * @param image
     */
    public void addCategory(String label, Image image) {
        if (label == null) { throw new NullPointerException("Label must not be null"); }
        if (image == null) { throw new NullPointerException("Image must not be null"); }
        this.preferences.put(label, new ArrayList<Preference<?>>());
        this.category = label;
        this.images.put(label, image);
        this.categories.add(label);
    }

    /**
     * Adds a new preference
     * @param preference
     */
    public void addPreference(Preference<?> preference) {
        if (category == null) { throw new IllegalStateException("Please create a category first"); }
        this.preferences.get(category).add(preference);
        preference.setDialog(this);
    }

    @Override
    public void create() {
        super.create();
        setTitle(title);
        setMessage(description);

        // Build tabs
        for (final String category : categories) {

            // Create the tab folder
            final TabItem tab = new TabItem(folder, SWT.NONE);
            tab.setText(category);
            if (images.get(category) != null) {
                tab.setImage(images.get(category));
            }
            final Composite tabC = createCategory(folder, category, preferences.get(category));
            tab.setControl(tabC);
        }

        // Pack the dialog
        super.getShell().pack();
    }

    /**
     * Returns the dialog's configuration
     * @return
     */
    public PreferencesDialogConfiguration getConfiguration() {
        return config;
    }

    /**
     * Creates a category
     * @param folder
     * @param category
     * @param preferences
     * @return
     */
    private Composite createCategory(TabFolder folder, String category, List<Preference<?>> preferences) {
        final Composite c = new Composite(folder, SWT.NONE);
        c.setLayout(new GridLayout(2, false));
        for (final Preference<?> e : preferences) {
            final Label l = new Label(c, SWT.NONE);
            l.setText(e.getLabel() + ":"); //$NON-NLS-1$
            editors.put(e, e.getEditor());
            editors.get(e).createControl(c);
            editors.get(e).setValue(e.getValue());
        }
        return c;
    }

    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setImages(shell.getImages());
    }

    @Override
    protected void createButtonsForButtonBar(final Composite parent) {
        parent.setLayoutData(GridDataFactory.swtDefaults().grab(true, true).indent(0, 0).align(SWT.FILL, SWT.FILL).create());
        ok = createButton(parent, -5, config.getStringOK(), true); 
        ok.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {

                for (Entry<Preference<?>, Editor<?>> entry : editors.entrySet()) {
                    if (entry.getValue().isDirty()) {
                        entry.getKey().setValue(entry.getValue().getValue());
                    }
                }

                setReturnCode(Window.OK);
                close();
            }
        });
    }

    @Override
    protected Control createDialogArea(final Composite parent) {
        parent.setLayout(new GridLayout(1, false));
        folder = new TabFolder(parent, SWT.NONE);
        folder.setLayoutData(GridDataFactory.swtDefaults().grab(true, true).indent(0, 0).align(SWT.FILL, SWT.FILL).create());
        return parent;
    }

    @Override
    protected ShellListener getShellListener() {
        return new ShellAdapter() {
            @Override
            public void shellClosed(final ShellEvent event) {
                setReturnCode(Window.CANCEL);
            }
        };
    }

    @Override
    protected boolean isResizable() {
        return false;
    }

    /**
     * Updates the OK button
     */
    void update() {
        for (Entry<String, List<Preference<?>>> entry : preferences.entrySet()) {
            for (Preference<?> preference : entry.getValue()) {
                if (editors.containsKey(preference) && !editors.get(preference).isValid()) {
                    ok.setEnabled(false);
                    return;
                }
            }
        }
        ok.setEnabled(true);
    }
}
