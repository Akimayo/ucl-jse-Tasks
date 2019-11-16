package cz.mciesla.ucl.logic.exceptions;

public class NotLoggedInException extends Throwable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public NotLoggedInException(String s) {
        super(s);
    }
}
