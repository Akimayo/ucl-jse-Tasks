package cz.mciesla.ucl.logic.data.managers;

import cz.mciesla.ucl.logic.app.entities.definition.ITag;
import cz.mciesla.ucl.logic.app.entities.definition.IUser;
import cz.mciesla.ucl.logic.data.dao.TagDAO;
import cz.mciesla.ucl.logic.data.managers.definition.ITagManager;
import cz.mciesla.ucl.logic.data.mappers.MapperFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TagManager implements ITagManager {
    /** Keys in the map will be emails of user who owns the tag */
    private Map<String, List<TagDAO>> tagDatabase;
    private MapperFactory mappers;
    @SuppressWarnings("unused")
    private ManagerFactory managers;

    public TagManager(ManagerFactory managers, MapperFactory mappers) {
        this.tagDatabase = new HashMap<>();
        this.mappers = mappers;
        this.managers = managers;
    }

    @Override
    public ITag[] getAllTagsForUser(IUser user) {
        return (ITag[]) this.getDAOsForUserLoggedIn(user).stream().map(i -> mappers.getTagMapper().mapFromDAODeep(i))
                .toArray();
    }

    private List<TagDAO> getDAOsForUserLoggedIn(IUser user) {
        List<TagDAO> userTags = tagDatabase.get(user.getEmail());
        if (userTags == null) {
            userTags = new ArrayList<>();
            tagDatabase.put(user.getEmail(), userTags);
        }
        return userTags;
    }

    @Override
    public ITag getTagByIdForUser(int tagId, IUser user) {
        TagDAO dao = this.getDAOsForUserLoggedIn(user).stream().filter(i -> i.getId() == tagId).findFirst().get();
        if (dao != null)
            return mappers.getTagMapper().mapFromDAODeep(dao);
        return null;
    }

    @Override
    public void createTag(ITag tag) {
        TagDAO dao = mappers.getTagMapper().mapToDAODeep(tag);
        this.getDAOsForUserLoggedIn(tag.getUser()).add(dao);
    }

    @Override
    public void updateTag(ITag tag) {
        TagDAO newDao = mappers.getTagMapper().mapToDAODeep(tag);
        List<TagDAO> userTags = this.getDAOsForUserLoggedIn(tag.getUser());
        userTags.set(userTags.indexOf(userTags.stream().filter(i -> i.getId() == tag.getId()).findFirst().get()),
                newDao);
    }

    @Override
    public void deleteTagByIdForUser(int tagId, IUser user) {
        List<TagDAO> userTags = this.getDAOsForUserLoggedIn(user);
        userTags.remove(userTags.indexOf(userTags.stream().filter(i -> i.getId() == tagId).findFirst().get()));
    }

}
