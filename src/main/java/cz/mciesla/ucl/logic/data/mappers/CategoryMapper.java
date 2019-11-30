package cz.mciesla.ucl.logic.data.mappers;

import cz.mciesla.ucl.logic.app.entities.Category;
import cz.mciesla.ucl.logic.app.entities.definition.ICategory;
import cz.mciesla.ucl.logic.app.entities.definition.ITask;
import cz.mciesla.ucl.logic.app.entities.definition.IUser;
import cz.mciesla.ucl.logic.data.dao.CategoryDAO;
import cz.mciesla.ucl.logic.data.dao.TaskDAO;
import cz.mciesla.ucl.logic.data.dao.UserDAO;
import cz.mciesla.ucl.logic.data.mappers.definition.ICategoryMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CategoryMapper implements ICategoryMapper {
    MapperFactory factory;

    public CategoryMapper(MapperFactory factory) {
        this.factory = factory;
    }

    @Override
    public ICategory mapFromDAOShallow(CategoryDAO dao) {
        IUser userEntity = factory.getUserMapper().mapFromDAOShallow(dao.getUser());

        ICategory categoryEntity = new Category(userEntity, dao.getId(), dao.getTitle(), dao.getColor(), dao.getCreatedAt(), dao.getUpdatedAt());

        return categoryEntity;
    }

    @Override
    public CategoryDAO mapToDAOShallow(ICategory entity) {
        UserDAO userDao = factory.getUserMapper().mapToDAOShallow(entity.getUser());

        CategoryDAO categoryDao = new CategoryDAO();

        categoryDao.setUser(userDao);
        categoryDao.setId(entity.getId());
        categoryDao.setTitle(entity.getTitle());
        categoryDao.setColor(entity.getColor());
        categoryDao.setCreatedAt(entity.getCreatedAt());
        categoryDao.setUpdatedAt(entity.getUpdatedAt());

        return categoryDao;
    }

    @Override
    public ICategory mapFromDAODeep(CategoryDAO dao) {
        ICategory categoryEntity = mapFromDAOShallow(dao);

        List<ITask> tasksEntities = factory.getTaskMapper().mapFromDAOsShallow(dao.getTasks());

        for (ITask taskEntity : tasksEntities) {
            categoryEntity.addTask(taskEntity);
        }

        return categoryEntity;
    }

    @Override
    public CategoryDAO mapToDAODeep(ICategory entity) {
        CategoryDAO categoryDao = mapToDAOShallow(entity);

        List<ITask> taskEntities = Arrays.asList(entity.getTasks());
        List<TaskDAO> taskDaos = factory.getTaskMapper().mapToDAOsShallow(taskEntities);

        for (TaskDAO taskDao : taskDaos) {
            categoryDao.getTasks().add(taskDao);
        }

        return categoryDao;
    }

    @Override
    public List<ICategory> mapFromDAOsShallow(List<CategoryDAO> daos) {
        List<ICategory> categoryEntities = new ArrayList<>();

        for (CategoryDAO dao : daos) {
            categoryEntities.add(mapFromDAOShallow(dao));
        }

        return categoryEntities;
    }

    @Override
    public List<CategoryDAO> mapToDAOsShallow(List<ICategory> entities) {
        List<CategoryDAO> categoryDaos = new ArrayList<>();

        for (ICategory entity : entities) {
            categoryDaos.add(mapToDAOShallow(entity));
        }

        return categoryDaos;
    }
}
