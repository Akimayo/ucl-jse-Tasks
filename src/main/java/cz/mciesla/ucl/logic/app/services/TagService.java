package cz.mciesla.ucl.logic.app.services;

import cz.mciesla.ucl.logic.app.entities.Tag;
import cz.mciesla.ucl.logic.app.entities.definition.Color;
import cz.mciesla.ucl.logic.app.entities.definition.ITag;
import cz.mciesla.ucl.logic.app.services.definition.ITagService;
import cz.mciesla.ucl.logic.app.services.definition.IUserService;
import cz.mciesla.ucl.logic.data.managers.definition.ITagManager;

/**
 * TagService
 */
public class TagService implements ITagService {
    private IUserService userService;
    private ITagManager manager;

    public TagService(UserService userService) {
        this.manager = userService.getManagerFactory().getTagManager();
        this.userService = userService;
    }

    @Override
    public ITag[] getAllTags() {
        if (this.userService.isUserLoggedIn())
            return this.manager.getAllTagsForUser(this.userService.getUserLoggedIn());
        else
            return new ITag[0];
    }

    @Override
    public ITag getTagById(int id) {
        if (this.userService.isUserLoggedIn())
            return this.manager.getTagByIdForUser(id, this.userService.getUserLoggedIn());
        else
            return null;
    }

    @Override
    public void createTag(String title) {
        this.createTag(title, Color.BLACK);
    }

    @Override
    public void createTag(String title, Color color) {
        if (this.userService.isUserLoggedIn())
            this.manager.createTag(new Tag(this.userService.getUserLoggedIn(), title, color));
    }

    @Override
    public void updateTag(int id, String title, Color color) {
        if(this.userService.isUserLoggedIn()) {
            ITag target = this.manager.getTagByIdForUser(id, this.userService.getUserLoggedIn());
            if(title != "") target.setTitle(title);
            if(color != null) target.setColor(color);
            this.manager.updateTag(target);
        }
    }

    @Override
    public void destroyTag(int id) {
        if (this.userService.isUserLoggedIn()) {
            this.manager.deleteTagByIdForUser(id, this.userService.getUserLoggedIn());
        }
    }

}