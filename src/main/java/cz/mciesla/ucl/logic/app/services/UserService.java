package cz.mciesla.ucl.logic.app.services;

import cz.mciesla.ucl.logic.app.entities.User;
import cz.mciesla.ucl.logic.app.entities.definition.IUser;
import cz.mciesla.ucl.logic.app.services.definition.IUserService;
import cz.mciesla.ucl.logic.exceptions.AlreadyLoggedInException;
import cz.mciesla.ucl.logic.exceptions.EmailAddressAlreadyUsedException;
import cz.mciesla.ucl.logic.exceptions.InvalidCredentialsException;
import cz.mciesla.ucl.logic.exceptions.NotLoggedInException;

/**
 * UserService
 */
public class UserService implements IUserService {

    @Override
    public void loginUser(String email, String password) throws AlreadyLoggedInException, InvalidCredentialsException {
        // TODO: Find out where to store session
        // TODO: Load user data from DTO on login

    }

    @Override
    public void logoutUser() throws NotLoggedInException {
        // TODO: Find out where to store session
        // TODO: Save user data to DTO?

    }

    @Override
    public void registerUser(String email, String username, String password) throws EmailAddressAlreadyUsedException {
        // TODO: Find out where to store users
        // FIXME: Check if email already exists
        new User(email, username, password);
    }

    @Override
    public boolean isUserLoggedIn() {
        // TODO: Find out where to store user sessions
        return false;
    }

    @Override
    public IUser getUserLoggedIn() {
        // TODO: Find out where to store user session
        return null;
    }

    @Override
    public void destroyUserLoggedIn() throws NotLoggedInException {
        // TODO: Find out where to store user session

    }

    
}