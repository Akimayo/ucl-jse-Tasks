package cz.mciesla.ucl.logic.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    private List<IUser> users;
    private IUser loggedInUser;

    public UserService() {
        this.users = new ArrayList<>();
    }

    @Override
    public void loginUser(String email, String password) throws AlreadyLoggedInException, InvalidCredentialsException {
        // TODO: Load user data from DTO on login
        if(this.loggedInUser == null) {
            Optional<IUser> find = this.users.stream().filter(i -> i.getEmail().equals(email) && i.getPassword().equals(password)).findFirst();
            if(find.isPresent()) this.loggedInUser = find.get();
            else throw new InvalidCredentialsException("E-mail nebo heslo je nesprávné.");
        } else throw new AlreadyLoggedInException("Již je přihášen jiný uživatel.");
    }

    @Override
    public void logoutUser() throws NotLoggedInException {
        // TODO: Save user data to DTO?
        if(this.loggedInUser != null) {
            this.loggedInUser = null;
        } else throw new NotLoggedInException("Není přihlášen žádný uživatel.");
    }

    @Override
    public void registerUser(String email, String username, String password) throws EmailAddressAlreadyUsedException {
        if(!this.users.stream().anyMatch(i -> i.getEmail().equals(email))) {
            this.users.add(new User(email, username, password));
        } else throw new EmailAddressAlreadyUsedException("Uživatel s touto e-mailovou adresou již existuje.");
    }

    @Override
    public boolean isUserLoggedIn() {
        return this.loggedInUser != null;
    }

    @Override
    public IUser getUserLoggedIn() {
        return this.loggedInUser;
    }

    @Override
    public void destroyUserLoggedIn() throws NotLoggedInException {
        if(this.loggedInUser != null) {
            this.users.remove(this.loggedInUser);
        } else throw new NotLoggedInException("Není přihlášen žádný uživatel.");
    }

    
}