package cz.mciesla.ucl.ui.cli.forms;

import cz.mciesla.ucl.ui.definition.forms.FormFieldType;
import cz.mciesla.ucl.ui.definition.forms.IForm;
import cz.mciesla.ucl.ui.definition.forms.IFormField;
import cz.mciesla.ucl.ui.definition.menu.IMenu;

import java.util.ArrayList;
import java.util.List;

public class Form implements IForm {
    private List<IFormField> fields;
    private IMenu menu;

    public Form(IMenu parentMenu) {
        this.menu = parentMenu;
        this.fields = new ArrayList<>();
    }

    // TODO

    @Override
    public String renderFormField(IFormField formField) {
		if(formField.getType().equals(FormFieldType.META)) return "";
        return menu.getParentInterface().getFormView().formatFormField(formField);
    }

	@Override
	public IFormField[] getFormFields() {
		return this.fields.toArray(new IFormField[0]);
	}

	@Override
	public void addFormField(IFormField field) {
		this.fields.add(field);
	}

	@Override
	public boolean isForm() {
		return this.fields.size() > 0;
	}
}
