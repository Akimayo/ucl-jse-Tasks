package cz.mciesla.ucl.logic.data.mappers;

import cz.mciesla.ucl.logic.app.entities.Tag;
import cz.mciesla.ucl.logic.app.entities.definition.ITag;
import cz.mciesla.ucl.logic.app.entities.definition.ITask;
import cz.mciesla.ucl.logic.app.entities.definition.IUser;
import cz.mciesla.ucl.logic.data.dao.TagDAO;
import cz.mciesla.ucl.logic.data.dao.TaskDAO;
import cz.mciesla.ucl.logic.data.dao.UserDAO;
import cz.mciesla.ucl.logic.data.mappers.definition.ITagMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TagMapper implements ITagMapper {
    MapperFactory factory;

    public TagMapper(MapperFactory factory) {
        this.factory = factory;
    }

    @Override
    public ITag mapFromDAOShallow(TagDAO dao) {
        IUser userEntity = factory.getUserMapper().mapFromDAOShallow(dao.getUser());
        ITag tagEntity = new Tag(userEntity, dao.getId(), dao.getTitle(), dao.getColor(), dao.getCreatedAt(), dao.getUpdatedAt());
        return tagEntity;
    }

    @Override
    public TagDAO mapToDAOShallow(ITag entity) {
        UserDAO userDao = factory.getUserMapper().mapToDAOShallow(entity.getUser());
        TagDAO tagDao = new TagDAO();
        tagDao.setUser(userDao);
        tagDao.setId(entity.getId());
        tagDao.setTitle(entity.getTitle());
        tagDao.setColor(entity.getColor());
        tagDao.setCreatedAt(entity.getCreatedAt());
        tagDao.setUpdatedAt(entity.getUpdatedAt());
        return tagDao;
    }

    @Override
    public ITag mapFromDAODeep(TagDAO dao) {
        ITag tagEntity = mapFromDAOShallow(dao);
        List<ITask> tasksEntities = factory.getTaskMapper().mapFromDAOsShallow(dao.getTasks());
        for (ITask taskEntity : tasksEntities) {
            tagEntity.addTask(taskEntity);
        }
        return tagEntity;
    }

    @Override
    public TagDAO mapToDAODeep(ITag entity) {
        TagDAO tagDao = mapToDAOShallow(entity);
        List<TaskDAO> taskDaos = factory.getTaskMapper().mapToDAOsShallow(Arrays.asList(entity.getTasks()));
        for (TaskDAO taskDao : taskDaos) {
            tagDao.getTasks().add(taskDao);
        }
        return tagDao;
    }

    @Override
    public List<ITag> mapFromDAOsShallow(List<TagDAO> daos) {
        List<ITag> tagEntities = new ArrayList<>();
        for (TagDAO dao : daos) {
            tagEntities.add(mapFromDAOShallow(dao));
        }
        return tagEntities;
    }

    @Override
    public List<TagDAO> mapToDAOsShallow(List<ITag> entities) {
        List<TagDAO> tagDaos = new ArrayList<>();
        for (ITag entity : entities) {
            tagDaos.add(mapToDAOShallow(entity));
        }
        return tagDaos;
    }

}
