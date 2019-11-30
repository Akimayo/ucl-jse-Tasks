package cz.mciesla.ucl.logic.data.managers;

import cz.mciesla.ucl.logic.app.entities.definition.IUser;
import cz.mciesla.ucl.logic.data.dao.UserDAO;
import cz.mciesla.ucl.logic.data.managers.definition.IUserManager;
import cz.mciesla.ucl.logic.data.mappers.MapperFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class UserManager implements IUserManager {
    private List<UserDAO> userDatabase;
    private MapperFactory mappers;
    @SuppressWarnings("unused")
    private ManagerFactory managers;

    public UserManager(ManagerFactory managers, MapperFactory mappers) {
        this.userDatabase = new ArrayList<>();
        this.mappers = mappers;
        this.managers = managers;
    }

    @Override
    public IUser[] getAllUsers() {
        return (IUser[]) userDatabase.stream().map(i -> mappers.getUserMapper().mapFromDAODeep(i)).toArray();
    }

    @Override
    public IUser getUserByEmail(String email) {
        return this.getFirstUserFromDAOStream(userDatabase.stream().filter(i -> i.getEmail() == email));
    }

    private IUser getFirstUserFromDAOStream(Stream<UserDAO> stream) {
        return mappers.getUserMapper().mapFromDAODeep(stream.findFirst().get());
    }

    @Override
    public IUser getUserById(int userId) {
        return this.getFirstUserFromDAOStream(userDatabase.stream().filter(i -> i.getId() == userId));
    }

    @Override
    public void createUser(IUser user) {
        UserDAO dao = mappers.getUserMapper().mapToDAODeep(user);
        userDatabase.add(dao);
    }

    @Override
    public void updateUser(IUser user) {
        UserDAO newDao = mappers.getUserMapper().mapToDAODeep(user);
        userDatabase.set(userDatabase.indexOf(userDatabase.stream().filter(i -> i.getId() == user.getId()).findFirst().get()), newDao);
    }

    @Override
    public void deleteUserById(int userId) {
        userDatabase.remove(userDatabase.indexOf(userDatabase.stream().filter(i -> i.getId() == userId).findFirst().get()));
    }

}
