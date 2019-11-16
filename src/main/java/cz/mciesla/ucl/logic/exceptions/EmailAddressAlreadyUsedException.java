package cz.mciesla.ucl.logic.exceptions;

public class EmailAddressAlreadyUsedException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public EmailAddressAlreadyUsedException(String s) {
        super(s);
    }
}
