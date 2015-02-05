SWT Preferences
====

This project provides a simple preferences dialog for SWT/JFace. Why yet another preferences dialog? Because
is very easy to use and it just works. For alternative implementations you may want to look at 
JFace's [PreferenceDialog](http://help.eclipse.org/luna/index.jsp?topic=%2Forg.eclipse.platform.doc.isv%2Freference%2Fapi%2Forg%2Feclipse%2Fjface%2Fpreference%2FPreferenceDialog.html)
or Opal's [PreferenceWindow](http://code.google.com/a/eclipselabs.org/p/opal/wiki/PreferenceWindow).

Features
------

1. Simple and easy to use API
2. Editors for boolean, numeric, textual and set-valued properties
3. Validators
4. Only writes back data that has actually been changed in the dialog
5. Supports internationalization via a configuration object

[![Screenshot](https://raw.github.com/prasser/swtpreferences/master/media/screenshot.png)](https://raw.github.com/prasser/swtpreferences/master/media/screenshot.png)

Example
------	

This basic example displays a dialog with one category containing two preferences:

```Java
// Create dialog
DialogPreference dialog = new DialogPreference(shell, "Settings", "Description");

// Create category
dialog.addCategory("category-1", icon1);

// Create integer preference
dialog.addPreference(new PreferenceInteger("setting-1", 0, 1000000) {
                     protected Integer getValue() { return model.getIntegerValue(); }
                     protected void setValue(Object t) { model.setIntegerValue((Integer)t); }});

// Create double preference        
dialog.addPreference(new PreferenceDouble("setting-2", 0d, 1d) {
                     protected Double getValue() { return model.getDoubleValue(); }
                     protected void setValue(Object t) { model.setDoubleValue((Double)t); }});

// Show
dialog.open();
```

Download
------
A binary version (JAR file) is available for download [here](https://rawgithub.com/prasser/swtpreferences/master/jars/swtpreferences-0.0.1.jar).

The according Javadoc is available for download [here](https://rawgithub.com/prasser/swtpreferences/master/jars/swtpreferences-0.0.1-doc.jar). 

Dependencies
------

SWT Preferences depends on SWT (obviously) and JFace. The versions used to build the binaries provided on
this page can be found [here](https://github.com/prasser/swtpreferences/tree/master/lib).

Documentation
------
Online documentation can be found [here](https://rawgithub.com/prasser/swtpreferences/master/doc/index.html).

SWT Preferences in action
------
Too see SWT Preferences in action, take a look at [ARX](https://github.com/arx-deidentifier/arx).

License
------
EPL 1.0
