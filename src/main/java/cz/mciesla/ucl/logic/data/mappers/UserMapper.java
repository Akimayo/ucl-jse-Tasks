package cz.mciesla.ucl.logic.data.mappers;

import cz.mciesla.ucl.logic.app.entities.User;
import cz.mciesla.ucl.logic.app.entities.definition.ICategory;
import cz.mciesla.ucl.logic.app.entities.definition.ITag;
import cz.mciesla.ucl.logic.app.entities.definition.ITask;
import cz.mciesla.ucl.logic.app.entities.definition.IUser;
import cz.mciesla.ucl.logic.data.dao.CategoryDAO;
import cz.mciesla.ucl.logic.data.dao.TagDAO;
import cz.mciesla.ucl.logic.data.dao.TaskDAO;
import cz.mciesla.ucl.logic.data.dao.UserDAO;
import cz.mciesla.ucl.logic.data.mappers.definition.IUserMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserMapper implements IUserMapper {
    MapperFactory factory;

    public UserMapper(MapperFactory factory) {
        this.factory = factory;
    }

    @Override
    public IUser mapFromDAOShallow(UserDAO dao) {
        IUser userEntity = new User(dao.getId(), dao.getEmail(), dao.getUsername(), dao.getPassword(), dao.getCreatedAt(), dao.getUpdatedAt());

        return userEntity;
    }

    @Override
    public UserDAO mapToDAOShallow(IUser entity) {
        UserDAO userDao = new UserDAO();

        userDao.setId(entity.getId());
        userDao.setEmail(entity.getEmail());
        userDao.setUsername(entity.getUsername());
        userDao.setPassword(entity.getPassword());
        userDao.setCreatedAt(entity.getCreatedAt());
        userDao.setUpdatedAt(entity.getUpdatedAt());

        return userDao;
    }

    @Override
    public IUser mapFromDAODeep(UserDAO dao) {
        IUser userEntity = mapFromDAOShallow(dao);

        List<ICategory> categoriesEntities = factory.getCategoryMapper().mapFromDAOsShallow(dao.getCategories());
        for (ICategory categoryEntity : categoriesEntities) {
            userEntity.addCategory(categoryEntity);
        }

        List<ITag> tagsEntities = factory.getTagMapper().mapFromDAOsShallow(dao.getTags());
        for (ITag tagEntity : tagsEntities) {
            userEntity.addTag(tagEntity);
        }

        List<ITask> tasksEntities = factory.getTaskMapper().mapFromDAOsShallow(dao.getTasks());
        for (ITask taskEntity : tasksEntities) {
            userEntity.addTask(taskEntity);
        }

        return userEntity;
    }

    @Override
    public UserDAO mapToDAODeep(IUser entity) {
        UserDAO userDao = mapToDAOShallow(entity);

        List<ICategory> categoryEntities = Arrays.asList(entity.getCategories());
        List<CategoryDAO> categoryDaos = factory.getCategoryMapper().mapToDAOsShallow(categoryEntities);
        for (CategoryDAO categoryDao : categoryDaos) {
            userDao.getCategories().add(categoryDao);
        }

        List<ITag> tagEntities = Arrays.asList(entity.getTags());
        List<TagDAO> tagDaos = factory.getTagMapper().mapToDAOsShallow(tagEntities);
        for (TagDAO tagDao : tagDaos) {
            userDao.getTags().add(tagDao);
        }

        List<ITask> taskEntities = Arrays.asList(entity.getTasks());
        List<TaskDAO> taskDaos = factory.getTaskMapper().mapToDAOsShallow(taskEntities);
        for (TaskDAO taskDao : taskDaos) {
            userDao.getTasks().add(taskDao);
        }

        return userDao;
    }

    @Override
    public List<IUser> mapFromDAOsShallow(List<UserDAO> daos) {
        List<IUser> userEntities = new ArrayList<>();

        for (UserDAO dao : daos) {
            userEntities.add(mapFromDAOShallow(dao));
        }

        return userEntities;
    }

    @Override
    public List<UserDAO> mapToDAOsShallow(List<IUser> entities) {
        List<UserDAO> userDaos = new ArrayList<>();

        for (IUser entity : entities) {
            userDaos.add(mapToDAOShallow(entity));
        }

        return userDaos;
    }
}
