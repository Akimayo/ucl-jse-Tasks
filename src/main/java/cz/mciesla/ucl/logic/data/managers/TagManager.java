package cz.mciesla.ucl.logic.data.managers;

import cz.mciesla.ucl.logic.app.entities.definition.ITag;
import cz.mciesla.ucl.logic.app.entities.definition.IUser;
import cz.mciesla.ucl.logic.data.dao.TagDAO;
import cz.mciesla.ucl.logic.data.managers.definition.ITagManager;
import cz.mciesla.ucl.logic.data.mappers.MapperFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TagManager implements ITagManager {
    /** Keys in the map will be emails of user who owns the tag */
    private Map<String, List<TagDAO>> tagDatabase;
    private MapperFactory mappers;
    private ManagerFactory managers;

    public TagManager(ManagerFactory managers, MapperFactory mappers) {
        this.tagDatabase = new HashMap<>();
        this.mappers = mappers;
        this.managers = managers;
    }

    @Override
    public ITag[] getAllTagsForUser(IUser user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ITag getTagByIdForUser(int tagId, IUser user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void createTag(ITag tag) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateTag(ITag tag) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteTagByIdForUser(int tagId, IUser user) {
        // TODO Auto-generated method stub

    }

    // TODO
}
