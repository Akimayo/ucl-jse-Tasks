package cz.mciesla.ucl.ui.cli.forms;

import cz.mciesla.ucl.ui.definition.forms.FormFieldType;
import cz.mciesla.ucl.ui.definition.forms.IFormField;

public class FormField implements IFormField {
    // TODO

    private String identifier;
    private String title;
    private String label;
    private FormFieldType type;
    private boolean required;

    public FormField(String identifier, String title, FormFieldType type) {
        this(identifier, title, "Prosím zadejte " + title.toLowerCase(), type, true);
    }

    public FormField(String identifier, String title, String label, FormFieldType type, boolean required) {
        this.identifier = identifier;
        this.title = title;
        this.label = label;
        this.type = type;
        this.required = required;
    }

    @Override
    public String getIdentifier() {
        return this.identifier;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public FormFieldType getType() {
        return this.type;
    }

    @Override
    public boolean getIsRequired() {
        return required;
    }

    // TODO
}
