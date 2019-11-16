package cz.mciesla.ucl.logic.exceptions;

public class AlreadyLoggedInException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public AlreadyLoggedInException(String s) {
        super(s);
    }
}
