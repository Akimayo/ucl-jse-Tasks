package cz.mciesla.ucl.logic.data.mappers;

import cz.mciesla.ucl.logic.app.entities.Task;
import cz.mciesla.ucl.logic.app.entities.definition.ICategory;
import cz.mciesla.ucl.logic.app.entities.definition.ITag;
import cz.mciesla.ucl.logic.app.entities.definition.ITask;
import cz.mciesla.ucl.logic.app.entities.definition.IUser;
import cz.mciesla.ucl.logic.data.dao.TagDAO;
import cz.mciesla.ucl.logic.data.dao.TaskDAO;
import cz.mciesla.ucl.logic.data.dao.UserDAO;
import cz.mciesla.ucl.logic.data.mappers.definition.ITaskMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskMapper implements ITaskMapper {
    MapperFactory factory;

    public TaskMapper(MapperFactory factory) {
        this.factory = factory;
    }

    @Override
    public ITask mapFromDAOShallow(TaskDAO dao) {
        IUser userEntity = factory.getUserMapper().mapFromDAOShallow(dao.getUser());
        ICategory categoryEntity = factory.getCategoryMapper().mapFromDAOShallow(dao.getCategory());
        ITask taskEntity = new Task(userEntity, dao.getId(), dao.getTitle(), dao.getNote(), categoryEntity, dao.getCreatedAt(), dao.getUpdatedAt());
        return taskEntity;
    }

    @Override
    public TaskDAO mapToDAOShallow(ITask entity) {
        UserDAO userDao = factory.getUserMapper().mapToDAOShallow(entity.getUser());
        TaskDAO taskDao = new TaskDAO();
        taskDao.setUser(userDao);
        taskDao.setId(entity.getId());
        taskDao.setTitle(entity.getTitle());
        taskDao.setNote(entity.getNote());
        taskDao.setCreatedAt(entity.getCreatedAt());
        taskDao.setUpdatedAt(entity.getUpdatedAt());
        return taskDao;
    }

    @Override
    public ITask mapFromDAODeep(TaskDAO dao) {
        ITask taskEntity = mapFromDAOShallow(dao);
        List<ITag> tagEntities = factory.getTagMapper().mapFromDAOsShallow(dao.getTags());
        for (ITag tagEntity : tagEntities) {
            taskEntity.addTag(tagEntity);
        }
        return taskEntity;
    }

    @Override
    public TaskDAO mapToDAODeep(ITask entity) {
        TaskDAO taskDao = mapToDAOShallow(entity);

        List<TagDAO> tagDaos = factory.getTagMapper().mapToDAOsShallow(Arrays.asList(entity.getTags()));
        for (TagDAO tagDao : tagDaos) {
            taskDao.getTags().add(tagDao);
        }
        return taskDao;
    }

    @Override
    public List<ITask> mapFromDAOsShallow(List<TaskDAO> daos) {
        List<ITask> taskEntities = new ArrayList<>();
        for (TaskDAO taskDao : daos) {
            taskEntities.add(mapFromDAOShallow(taskDao));
        }
        return taskEntities;
    }

    @Override
    public List<TaskDAO> mapToDAOsShallow(List<ITask> entities) {
        List<TaskDAO> taskDaos = new ArrayList<>();
        for (ITask entity : entities) {
            taskDaos.add(mapToDAOShallow(entity));
        }
        return taskDaos;
    }

}