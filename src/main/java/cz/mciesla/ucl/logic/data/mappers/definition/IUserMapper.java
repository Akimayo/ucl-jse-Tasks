package cz.mciesla.ucl.logic.data.mappers.definition;

import cz.mciesla.ucl.logic.app.entities.definition.IUser;
import cz.mciesla.ucl.logic.data.dao.UserDAO;

import java.util.List;

public interface IUserMapper {
    IUser mapFromDAOShallow(UserDAO dao);
    UserDAO mapToDAOShallow(IUser entity);
    IUser mapFromDAODeep(UserDAO dao);
    UserDAO mapToDAODeep(IUser entity);

    List<IUser> mapFromDAOsShallow(List<UserDAO> daos);
    List<UserDAO> mapToDAOsShallow(List<IUser> entities);
}
