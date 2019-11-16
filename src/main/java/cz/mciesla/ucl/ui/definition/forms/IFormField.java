package cz.mciesla.ucl.ui.definition.forms;

/** This interface represents one form field in form IMenu(s) */
public interface IFormField {
    /** Returns system identifier of the form field (eg. "email"). Identifiers should be lowercase and underscore_case */
    String getIdentifier();

    /** Returns title of the form field (eg. "E-Mail") */
    String getTitle();

    /** Returns label which is used to describe the field in the form (eg. "Please enter e-mail") */
    String getLabel();

    /** Returns type of the field */
    FormFieldType getType();

    /** Returns true if field is required or false if it can be left empty */
    boolean getIsRequired();
}
