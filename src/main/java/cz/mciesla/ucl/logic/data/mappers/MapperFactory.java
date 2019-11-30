package cz.mciesla.ucl.logic.data.mappers;

import cz.mciesla.ucl.logic.data.mappers.definition.ICategoryMapper;
import cz.mciesla.ucl.logic.data.mappers.definition.ITagMapper;
import cz.mciesla.ucl.logic.data.mappers.definition.ITaskMapper;
import cz.mciesla.ucl.logic.data.mappers.definition.IUserMapper;

public class MapperFactory {
    ICategoryMapper categoryMapper;
    IUserMapper userMapper;
    ITaskMapper taskMapper;
    ITagMapper tagMapper;

    public MapperFactory() {
        this.categoryMapper = new CategoryMapper(this);
        this.userMapper = new UserMapper(this);
        this.taskMapper = new TaskMapper(this);
        this.tagMapper = new TagMapper(this);
    }

    public ICategoryMapper getCategoryMapper() {
        return categoryMapper;
    }

    public IUserMapper getUserMapper() {
        return userMapper;
    }

    public ITaskMapper getTaskMapper() {
        return taskMapper;
    }

    public ITagMapper getTagMapper() {
        return tagMapper;
    }
}
