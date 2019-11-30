package cz.mciesla.ucl.logic.app.services;

import cz.mciesla.ucl.logic.app.entities.Category;
import cz.mciesla.ucl.logic.app.entities.definition.Color;
import cz.mciesla.ucl.logic.app.entities.definition.ICategory;
import cz.mciesla.ucl.logic.app.services.definition.ICategoryService;
import cz.mciesla.ucl.logic.app.services.definition.IUserService;
import cz.mciesla.ucl.logic.data.managers.definition.ICategoryManager;

public class CategoryService implements ICategoryService {
    private IUserService userService;
    private ICategoryManager manager;

    public CategoryService(UserService userService) {
        this.manager = userService.getManagerFactory().getCategoryManager();
        this.userService = userService;
    }

    @Override
    public ICategory[] getAllCategories() {
        if (this.userService.isUserLoggedIn())
            return this.manager.getAllCategoriesForUser(this.userService.getUserLoggedIn());
        else
            return new ICategory[0];
    }

    @Override
    public ICategory getCategoryById(int id) {
        return this.manager.getCategoryByIdForUser(id, this.userService.getUserLoggedIn());
    }

    @Override
    public void createCategory(String title) {
        if (this.userService.isUserLoggedIn())
            this.manager.createCategory(new Category(this.userService.getUserLoggedIn(), title, Color.BLACK));
    }

    @Override
    public void createCategory(String title, Color color) {
        if (this.userService.isUserLoggedIn())
            this.manager.createCategory(new Category(this.userService.getUserLoggedIn(), title, color));
    }

    @Override
    public void updateCategory(int id, String title, Color color) {
        if (this.userService.isUserLoggedIn()) {
            ICategory target = this.manager.getCategoryByIdForUser(id, this.userService.getUserLoggedIn());
            if(title != "") target.setTitle(title);
            if(color != null) target.setColor(color);
            this.manager.updateCategory(target);
        }
    }

    @Override
    public void destroyCategory(int id) {
        if (this.userService.isUserLoggedIn()) {
            this.manager.deleteCategoryByIdForUser(id, this.userService.getUserLoggedIn());
        }
    }

}
