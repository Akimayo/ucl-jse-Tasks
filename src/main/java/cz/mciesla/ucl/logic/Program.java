package cz.mciesla.ucl.logic;

import cz.mciesla.ucl.logic.app.entities.definition.Color;
import cz.mciesla.ucl.logic.app.entities.definition.ICategory;
import cz.mciesla.ucl.logic.app.entities.definition.ITag;
import cz.mciesla.ucl.logic.app.entities.definition.ITask;
import cz.mciesla.ucl.logic.app.entities.definition.IUser;
import cz.mciesla.ucl.logic.app.services.CategoryService;
import cz.mciesla.ucl.logic.app.services.TagService;
import cz.mciesla.ucl.logic.app.services.TaskService;
import cz.mciesla.ucl.logic.app.services.UserService;
import cz.mciesla.ucl.logic.exceptions.AlreadyLoggedInException;
import cz.mciesla.ucl.logic.exceptions.EmailAddressAlreadyUsedException;
import cz.mciesla.ucl.logic.exceptions.InvalidCredentialsException;
import cz.mciesla.ucl.logic.exceptions.NotLoggedInException;

/** This class HAS to honor the Facade design pattern!
 *
 *  No direct functionality should be present!
 *  All functionality should be delegated to service classes
 *
 *  All xxxService attributes have to be private!
 */
public class Program implements IAppLogic {
    private CategoryService categoryService;
    private TagService tagService;
    private TaskService taskService;
    private UserService userService;

    public Program() {
        userService = new UserService();
        categoryService = new CategoryService(userService);
        tagService = new TagService(userService);
        taskService = new TaskService(userService);
    }

	@Override
	public ICategory[] getAllCategories() {
		return this.categoryService.getAllCategories();
	}

	@Override
	public ICategory getCategoryById(int id) {
		return this.categoryService.getCategoryById(id);
	}

	@Override
	public void createCategory(String title) {
		this.categoryService.createCategory(title);
	}

	@Override
	public void createCategory(String title, Color color) {
		this.categoryService.createCategory(title, color);
	}

	@Override
	public void updateCategory(int id, String title, Color color) {
		this.categoryService.updateCategory(id, title, color);
	}

	@Override
	public void destroyCategory(int id) {
		this.categoryService.destroyCategory(id);
	}

	@Override
	public void loginUser(String email, String password) throws AlreadyLoggedInException, InvalidCredentialsException {
		this.userService.loginUser(email, password);
	}

	@Override
	public void logoutUser() throws NotLoggedInException {
		this.userService.logoutUser();
	}

	@Override
	public void registerUser(String email, String username, String password) throws EmailAddressAlreadyUsedException {
		this.userService.registerUser(email, username, password);
	}

	@Override
	public boolean isUserLoggedIn() {
		return this.userService.isUserLoggedIn();
	}

	@Override
	public IUser getUserLoggedIn() {
		return this.userService.getUserLoggedIn();
	}

	@Override
	public void destroyUserLoggedIn() throws NotLoggedInException {
		this.userService.destroyUserLoggedIn();
	}

	@Override
	public ITask getTaskById(int id) {
		return this.taskService.getTaskById(id);
	}

	@Override
	public ITask[] getAllTasks() {
		return this.taskService.getAllTasks();
	}

	@Override
	public ITask[] getTasksFilteredByCategory(String categoryTitle) {
		// TODO: String-based search
        //		 (Probably after hooking up DTO - using database query for ID)
		return null;
	}

	@Override
	public ITask[] getTasksFilteredByTags(String[] tagTitles) {
		// TODO: String-based search
		//		 (Probably after hooking up DTO - using database query for ID)
		return null;
	}

	@Override
	public ITask[] getTasksFilteredByCategoryAndTags(String categoryTitle, String[] tagTitles) {
		// TODO: String-based search (using taskService.getAllTasksByTags)
		//		 (Probably after hooking up DTO - using databse query for ID)
		return null;
	}

	@Override
	public ITag[] getAllTags() {
		return this.tagService.getAllTags();
	}

	@Override
	public void generateMockData() {
        // TODO: Generate mcock data
        try {
            userService.registerUser("a@b.c", "ferda", "kulomet");
        } catch (EmailAddressAlreadyUsedException e) {}
	}

    // TODO
}
