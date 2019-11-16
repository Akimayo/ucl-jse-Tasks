package cz.mciesla.ucl.ui.definition.forms;

/**
 * This interface represent a form like menu.
 *
 * Every IMenu has to implement the getFormFields() method.
 * But only when the getFormFields() method returns non-empty array, the IMenu is considered as form.
 */
public interface IForm {
    /** Returns array of form fields, or an empty array */
    IFormField[] getFormFields();

    /** Adds form field to the form */
    void addFormField(IFormField field);

    /** Returns rendered form field as string (to be shown in console) */
    String renderFormField(IFormField formField);

    /**
     * Returns true if IMenu has one or more form fields.
     * This method has to return true if getFormFields() returns non-empty array
     */
    boolean isForm();
}
