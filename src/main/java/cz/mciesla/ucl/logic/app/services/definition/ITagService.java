package cz.mciesla.ucl.logic.app.services.definition;

import cz.mciesla.ucl.logic.app.entities.definition.Color;
import cz.mciesla.ucl.logic.app.entities.definition.ITag;

/**
 * This interface describes a class which should contain all application logic related to tags
 * management and manipulation
 */
public interface ITagService {
    /** Returns all tags */
    ITag[] getAllTags();

    /** Returns one tag by its ID */
    ITag getTagById(int id);

    /** Creates a tag with title */
    void createTag(String title);

    /** Creates a tag with title and color */
    void createTag(String title, Color color);

    /** Finds a tag by its ID and updates it */
    void updateTag(int id, String title, Color color);

    /** Finds a tag by its ID and destroys it */
    void destroyTag(int id);
}
