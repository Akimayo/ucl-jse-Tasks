package cz.mciesla.ucl.logic.app.services;

import java.util.List;
import java.util.stream.Collectors;

import cz.mciesla.ucl.logic.app.entities.Category;
import cz.mciesla.ucl.logic.app.entities.definition.Color;
import cz.mciesla.ucl.logic.app.entities.definition.ICategory;
import cz.mciesla.ucl.logic.app.services.definition.ICategoryService;

public class CategoryService implements ICategoryService {

    private List<ICategory> categories;

    public CategoryService(UserService userService) {
	}

	@Override
    public ICategory[] getAllCategories() {
        return this.categories.toArray(new ICategory[0]);
    }

    @Override
    public ICategory getCategoryById(int id) {
        return this.categories.stream().filter(i -> i.getId() == id).collect(Collectors.toList()).get(0);
    }

    @Override
    public void createCategory(String title) {
        this.createCategory(title, Color.BLACK);
    }

    @Override
    public void createCategory(String title, Color color) {
        this.categories.add(new Category(title, color));
    }

    @Override
    public void updateCategory(int id, String title, Color color) {
        // TODO: Update category
        
    }

    @Override
    public void destroyCategory(int id) {
        this.categories.remove(this.getCategoryById(id));
    }

}
