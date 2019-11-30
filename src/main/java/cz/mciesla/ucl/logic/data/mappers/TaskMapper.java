package cz.mciesla.ucl.logic.data.mappers;

import cz.mciesla.ucl.logic.app.entities.definition.ITask;
import cz.mciesla.ucl.logic.data.dao.TaskDAO;
import cz.mciesla.ucl.logic.data.mappers.definition.ITaskMapper;

import java.util.ArrayList;
import java.util.List;

public class TaskMapper implements ITaskMapper {
    MapperFactory factory;

    public TaskMapper(MapperFactory factory) {
        this.factory = factory;
    }

    @Override
    public ITask mapFromDAOShallow(TaskDAO dao) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TaskDAO mapToDAOShallow(ITask entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ITask mapFromDAODeep(TaskDAO dao) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TaskDAO mapToDAODeep(ITask entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ITask> mapFromDAOsShallow(List<TaskDAO> daos) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<TaskDAO> mapToDAOsShallow(List<ITask> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    // TODO
}