package cz.mciesla.ucl.logic.app.services;

import java.util.stream.Stream;

import cz.mciesla.ucl.logic.app.entities.Category;
import cz.mciesla.ucl.logic.app.entities.definition.Color;
import cz.mciesla.ucl.logic.app.entities.definition.ICategory;
import cz.mciesla.ucl.logic.app.entities.definition.IUser;
import cz.mciesla.ucl.logic.app.services.definition.ICategoryService;
import cz.mciesla.ucl.logic.app.services.definition.IUserService;

public class CategoryService implements ICategoryService {
    private IUserService userService;

    public CategoryService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ICategory[] getAllCategories() {
        IUser user = this.userService.getUserLoggedIn();
        if (user != null)
            return user.getCategories();
        else
            return new ICategory[0];
    }

    @Override
    public ICategory getCategoryById(int id) {
        return Stream.of(this.getAllCategories()).filter(i -> i.getId() == id).findFirst().get();
    }

    @Override
    public void createCategory(String title) {
        IUser user = this.userService.getUserLoggedIn();
        if (user != null)
            user.addCategory(new Category(title, Color.BLACK));
    }

    @Override
    public void createCategory(String title, Color color) {
        IUser user = this.userService.getUserLoggedIn();
        if (user != null)
            user.addCategory(new Category(title, color));
    }

    @Override
    public void updateCategory(int id, String title, Color color) {
        // WTF: ?
        // TODO: Update category

    }

    @Override
    public void destroyCategory(int id) {
        IUser user = this.userService.getUserLoggedIn();
        if (user != null) {
            // FIXME: Use index insted of ID
            user.saveCategory(id, null);
        }
    }

}
