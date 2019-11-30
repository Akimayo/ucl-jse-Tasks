package cz.mciesla.ucl.logic.data.managers;

import cz.mciesla.ucl.logic.app.entities.definition.IUser;
import cz.mciesla.ucl.logic.data.dao.UserDAO;
import cz.mciesla.ucl.logic.data.managers.definition.IUserManager;
import cz.mciesla.ucl.logic.data.mappers.MapperFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserManager implements IUserManager {
    private List<UserDAO> userDatabase;
    private MapperFactory mappers;
    private ManagerFactory managers;

    public UserManager(ManagerFactory managers, MapperFactory mappers) {
        this.userDatabase = new ArrayList<>();
        this.mappers = mappers;
        this.managers = managers;
    }

    @Override
    public IUser[] getAllUsers() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IUser getUserByEmail(String email) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IUser getUserById(int userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void createUser(IUser user) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateUser(IUser user) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteUserById(int userId) {
        // TODO Auto-generated method stub

    }

    // TODO
}
