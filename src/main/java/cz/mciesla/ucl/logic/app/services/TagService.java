package cz.mciesla.ucl.logic.app.services;

import java.util.List;
import java.util.stream.Collectors;

import cz.mciesla.ucl.logic.app.entities.Tag;
import cz.mciesla.ucl.logic.app.entities.definition.Color;
import cz.mciesla.ucl.logic.app.entities.definition.ITag;
import cz.mciesla.ucl.logic.app.services.definition.ITagService;

/**
 * TagService
 */
public class TagService implements ITagService {
    private List<ITag> tags;

    public TagService(UserService userService) {
	}

	@Override
    public ITag[] getAllTags() {
        return this.tags.toArray(new ITag[0]);
    }

    @Override
    public ITag getTagById(int id) {
        return this.tags.stream().filter(i -> i.getId() == id).collect(Collectors.toList()).get(0);
    }

    @Override
    public void createTag(String title) {
        this.createTag(title, Color.BLACK);
    }

    @Override
    public void createTag(String title, Color color) {
        this.tags.add(new Tag(title, color));
    }

    @Override
    public void updateTag(int id, String title, Color color) {
        // TODO: Update tag

    }

    @Override
    public void destroyTag(int id) {
        this.tags.remove(this.getTagById(id));
    }

    
}