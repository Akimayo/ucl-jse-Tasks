package cz.mciesla.ucl.logic.data.managers;

import cz.mciesla.ucl.logic.app.entities.definition.ICategory;
import cz.mciesla.ucl.logic.app.entities.definition.IUser;
import cz.mciesla.ucl.logic.data.dao.CategoryDAO;
import cz.mciesla.ucl.logic.data.managers.definition.ICategoryManager;
import cz.mciesla.ucl.logic.data.mappers.MapperFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryManager implements ICategoryManager {
    /** Keys in the map will be emails of user who owns the category */
    private Map<String, List<CategoryDAO>> categoryDatabase;
    private MapperFactory mappers;
    private ManagerFactory managers;

    public CategoryManager(ManagerFactory managers, MapperFactory mappers) {
        this.categoryDatabase = new HashMap<>();
        this.mappers = mappers;
        this.managers = managers;
    }

    public ICategory[] getAllCategoriesForUser(IUser user) {
        List<CategoryDAO> userCategories = getDAOsForUserLoggedIn(user);

        ICategory[] loadedCategories = new ICategory[userCategories.size()];

        for (int i = 0; i < userCategories.size(); i++) {
            CategoryDAO dao = userCategories.get(i);

            loadedCategories[i] = mappers.getCategoryMapper().mapFromDAODeep(dao);
        }

        return loadedCategories;
    }

    public ICategory getCategoryByIdForUser(int categoryId, IUser user) {
        List<CategoryDAO> userCategories = getDAOsForUserLoggedIn(user);

        for (CategoryDAO categoryDao : userCategories) {
            if (categoryDao.getId() == categoryId) {
                return mappers.getCategoryMapper().mapFromDAODeep(categoryDao);
            }
        }

        return null;
    }

    public void createCategory(ICategory category) {
        List<CategoryDAO> userCategories = getDAOsForUserLoggedIn(category.getUser());

        CategoryDAO dao = mappers.getCategoryMapper().mapToDAODeep(category);

        userCategories.add(dao);
    }

    public void updateCategory(ICategory category) {
        List<CategoryDAO> userCategories = getDAOsForUserLoggedIn(category.getUser());

        int daoIndex = -1;
        for(int i = 0; i < userCategories.size(); i++) {
            if (userCategories.get(i).getId() == category.getId()) {
                daoIndex = i;
                break;
            }
        }

        CategoryDAO newDao = mappers.getCategoryMapper().mapToDAODeep(category);

        userCategories.set(daoIndex, newDao);
    }

    public void deleteCategoryByIdForUser(int categoryId, IUser user) {
        List<CategoryDAO> userCategories = getDAOsForUserLoggedIn(user);

        int foundCategoryIndex = -1;
        for (CategoryDAO dao : userCategories) {
            if (dao.getId() == categoryId) {
                foundCategoryIndex = userCategories.indexOf(dao);
            }
        }

        userCategories.remove(foundCategoryIndex);
    }

    private List<CategoryDAO> getDAOsForUserLoggedIn(IUser user) {
        List<CategoryDAO> userCategories = categoryDatabase.get(user.getEmail());

        // We have to create a new empty ArrayList in the map in case it does not exists yet
        if (userCategories == null) {
            userCategories = new ArrayList<>();

            categoryDatabase.put(user.getEmail(), userCategories);
        }

        return userCategories;
    }
}
