package cz.mciesla.ucl.logic.app.services;

import java.util.stream.Stream;

import cz.mciesla.ucl.logic.app.entities.Tag;
import cz.mciesla.ucl.logic.app.entities.definition.Color;
import cz.mciesla.ucl.logic.app.entities.definition.ITag;
import cz.mciesla.ucl.logic.app.entities.definition.IUser;
import cz.mciesla.ucl.logic.app.services.definition.ITagService;
import cz.mciesla.ucl.logic.app.services.definition.IUserService;

/**
 * TagService
 */
public class TagService implements ITagService {
    private IUserService userService;

    public TagService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ITag[] getAllTags() {
        IUser user = this.userService.getUserLoggedIn();
        if (user != null) {
            return user.getTags();
        } else
            return new ITag[0];
    }

    @Override
    public ITag getTagById(int id) {
        IUser user = this.userService.getUserLoggedIn();
        if (user != null) {
            return Stream.of(user.getTags()).filter(i -> i.getId() == id).findFirst().get();
        } else
            return null;
    }

    @Override
    public void createTag(String title) {
        IUser user = this.userService.getUserLoggedIn();
        if (user != null)
            user.addTag(new Tag(title, Color.BLACK));
    }

    @Override
    public void createTag(String title, Color color) {
        IUser user = this.userService.getUserLoggedIn();
        if (user != null)
            user.addTag(new Tag(title, color));
    }

    @Override
    public void updateTag(int id, String title, Color color) {
        // TODO: Update tag

    }

    @Override
    public void destroyTag(int id) {
        IUser user = this.userService.getUserLoggedIn();
        if (user != null) {
            // FIXME: Use index instead of ID
            user.saveTag(id, null);
        }
    }

}