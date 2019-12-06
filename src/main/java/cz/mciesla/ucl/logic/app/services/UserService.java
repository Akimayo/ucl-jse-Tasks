package cz.mciesla.ucl.logic.app.services;

import cz.mciesla.ucl.logic.app.entities.User;
import cz.mciesla.ucl.logic.app.entities.definition.IUser;
import cz.mciesla.ucl.logic.app.services.definition.IUserService;
import cz.mciesla.ucl.logic.data.managers.ManagerFactory;
import cz.mciesla.ucl.logic.data.managers.definition.IUserManager;
import cz.mciesla.ucl.logic.exceptions.AlreadyLoggedInException;
import cz.mciesla.ucl.logic.exceptions.EmailAddressAlreadyUsedException;
import cz.mciesla.ucl.logic.exceptions.InvalidCredentialsException;
import cz.mciesla.ucl.logic.exceptions.NotLoggedInException;

/**
 * UserService
 */
public class UserService implements IUserService {
    private IUser loggedInUser;
    private ManagerFactory managerFactory;
    private IUserManager manager;

    public UserService(ManagerFactory managers) {
        this.manager = managers.getUserManager();
        this.managerFactory = managers;
    }

    public ManagerFactory getManagerFactory() {
        return this.managerFactory;
    }

    @Override
    public void loginUser(String email, String password) throws AlreadyLoggedInException, InvalidCredentialsException {
        if(this.loggedInUser == null) {
            IUser find = this.manager.getUserByEmail(email);
            if(find != null && find.getPassword().equals(password)) this.loggedInUser = find;
            else throw new InvalidCredentialsException("E-mail nebo heslo je nesprávné.");
        } else throw new AlreadyLoggedInException("Již je přihášen jiný uživatel.");
    }

    @Override
    public void logoutUser() throws NotLoggedInException {
        if(this.loggedInUser != null) {
            this.loggedInUser = null;
        } else throw new NotLoggedInException("Není přihlášen žádný uživatel.");
    }

    @Override
    public void registerUser(String email, String username, String password) throws EmailAddressAlreadyUsedException {
        if(this.manager.getUserByEmail(email) == null) {
            this.manager.createUser(new User(email, username, password));
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
            this.manager.deleteUserById(this.loggedInUser.getId());
            this.loggedInUser = null;
        } else throw new NotLoggedInException("Není přihlášen žádný uživatel.");
    }

    @Override
    public void updateUserLoggedIn(String email, String username, String checkedPassword) {
        if(this.loggedInUser != null) {
            this.loggedInUser.setEmail(email);
            this.loggedInUser.setUsername(username);
            this.loggedInUser.setPassword(checkedPassword);
            this.manager.updateUser(this.loggedInUser);
        }
    }

}
