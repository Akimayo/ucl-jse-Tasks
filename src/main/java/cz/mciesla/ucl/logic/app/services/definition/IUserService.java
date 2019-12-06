package cz.mciesla.ucl.logic.app.services.definition;

import cz.mciesla.ucl.logic.app.entities.definition.IUser;
import cz.mciesla.ucl.logic.exceptions.AlreadyLoggedInException;
import cz.mciesla.ucl.logic.exceptions.EmailAddressAlreadyUsedException;
import cz.mciesla.ucl.logic.exceptions.InvalidCredentialsException;
import cz.mciesla.ucl.logic.exceptions.NotLoggedInException;

/**
 * This interface describes a class which should contain all application logic related to user
 * management, manipulation and authentication
 */
public interface IUserService {
    /** Allows user to login (AppLogin will remember which user is logged in) */
    void loginUser(String email, String password) throws AlreadyLoggedInException, InvalidCredentialsException;

    /** Allows user to logout from the system */
    void logoutUser() throws NotLoggedInException;

    /** Allows user to register into the system
     *  This method will not login the registered user. After registration, user has to login by the loginUser(...) method
     */
    void registerUser(String email, String username, String password) throws EmailAddressAlreadyUsedException;

    /** Returns true if there is a user logged into the system, false othewise
     *  When this method returns true, the userLoggedIn() method should not return null
     */
    boolean isUserLoggedIn();

    /** Returns the logged in user or null */
    IUser getUserLoggedIn();

    /** Destroys a logged in, registered user */
    void destroyUserLoggedIn() throws NotLoggedInException;

	void updateUserLoggedIn(String email, String username, String checkedPassword);

}
