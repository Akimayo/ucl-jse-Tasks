package cz.mciesla.ucl.ui.cli.views;

import cz.mciesla.ucl.ui.definition.forms.IFormField;
import cz.mciesla.ucl.ui.definition.views.IFormView;

/**
 * FormView
 */
public class FormView implements IFormView {

    @Override
    public String formatFormField(IFormField field) {
        return field.getLabel() + System.lineSeparator() + field.getTitle() + ": ";
    }

    
}