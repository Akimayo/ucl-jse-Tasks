package cz.mciesla.ucl.logic.app.services.definition;

import cz.mciesla.ucl.logic.app.entities.definition.Color;
import cz.mciesla.ucl.logic.app.entities.definition.ICategory;

/**
 * This interface describes a class which should contain all application logic related to categories
 * management and manipulation
 */
public interface ICategoryService {
    /** Returns all categories */
    ICategory[] getAllCategories();

    /** Returns one category by its ID */
    ICategory getCategoryById(int id);

    /** Creates a category with title */
    void createCategory(String title);

    /** Creates a category with title and color */
    void createCategory(String title, Color color);

    /** Finds a category by its ID and updates it */
    void updateCategory(int id, String title, Color color);

    /** Finds a category by its ID and destroys it */
    void destroyCategory(int id);
}
