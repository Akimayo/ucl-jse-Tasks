package cz.mciesla.ucl.logic;

import java.time.LocalDate;
import java.util.stream.Stream;

import cz.mciesla.ucl.logic.app.entities.definition.Color;
import cz.mciesla.ucl.logic.app.entities.definition.ICategory;
import cz.mciesla.ucl.logic.app.entities.definition.ITag;
import cz.mciesla.ucl.logic.app.entities.definition.ITask;
import cz.mciesla.ucl.logic.app.entities.definition.IUser;
import cz.mciesla.ucl.logic.app.services.CategoryService;
import cz.mciesla.ucl.logic.app.services.TagService;
import cz.mciesla.ucl.logic.app.services.TaskService;
import cz.mciesla.ucl.logic.app.services.UserService;
import cz.mciesla.ucl.logic.app.services.definition.TasksOrder;
import cz.mciesla.ucl.logic.data.managers.ManagerFactory;
import cz.mciesla.ucl.logic.data.mappers.MapperFactory;
import cz.mciesla.ucl.logic.exceptions.AlreadyLoggedInException;
import cz.mciesla.ucl.logic.exceptions.EmailAddressAlreadyUsedException;
import cz.mciesla.ucl.logic.exceptions.InvalidCredentialsException;
import cz.mciesla.ucl.logic.exceptions.NotLoggedInException;

/**
 * This class HAS to honor the Facade design pattern!
 *
 * No direct functionality should be present! All functionality should be
 * delegated to service classes
 *
 * All xxxService attributes have to be private!
 */
public class Program implements IAppLogic {
    private CategoryService categoryService;
    private TagService tagService;
    private TaskService taskService;
    private UserService userService;

    public Program() {
        userService = new UserService(new ManagerFactory(new MapperFactory()));
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
        return this.taskService.getAllTasksByCategory(Stream.of(this.categoryService.getAllCategories())
                .filter(i -> i.getTitle().equals(categoryTitle)).findFirst().get());
    }

    @Override
    public ITask[] getTasksFilteredByTags(String[] tagTitles) {
        Stream<String> tagTitleStream = Stream.of(tagTitles);
        return this.taskService.getAllTasksByTags(Stream.of(this.tagService.getAllTags())
                .filter(i -> tagTitleStream.anyMatch(j -> j.equals(i.getTitle()))).toArray(ITag[]::new));
    }

    @Override
    public ITask[] getTasksFilteredByCategoryAndTags(String categoryTitle, String[] tagTitles) {
        Stream<String> tagTitleStream = Stream.of(tagTitles);
        return this.taskService.getAllTasksByTags(
                Stream.of(this.tagService.getAllTags())
                        .filter(i -> tagTitleStream.anyMatch(j -> j.equals(i.getTitle()))).toArray(ITag[]::new),
                Stream.of(this.categoryService.getAllCategories()).filter(i -> i.getTitle().equals(categoryTitle))
                        .findFirst().get());
    }

    @Override
    public ITag[] getAllTags() {
        return this.tagService.getAllTags();
    }

    @Override
    public void generateMockData() {
        try {
            userService.registerUser("a@b.c", "ferda", "kulomet");
            userService.loginUser("a@b.c", "kulomet");
            // TODO: Generate mock data
        } catch (EmailAddressAlreadyUsedException | AlreadyLoggedInException | InvalidCredentialsException e) {
        }
    }

    @Override
    public ITask[] getAllTasks(TasksOrder order) {
        return this.taskService.getAllTasks(order);
    }

    @Override
    public ITask[] searchTasksForKeyword(String keyword) {
        return this.taskService.searchTasksForKeyword(keyword);
    }

    @Override
    public ITask[] getAllTasksByCategory(ICategory category) {
        return this.taskService.getAllTasksByCategory(category);
    }

    @Override
    public ITask[] getAllTasksByTag(ITag tag) {
        return this.taskService.getAllTasksByTag(tag);
    }

    @Override
    public ITask[] getAllTasksByTags(ITag[] tag) {
        return this.taskService.getAllTasksByTags(tag);
    }

    @Override
    public ITask[] getAllTasksByTags(ITag[] tag, ICategory category) {
        return this.taskService.getAllTasksByTags(tag, category);
    }

    @Override
    public void createTask(String title) {
        this.taskService.createTask(title);
    }

    @Override
    public void createTask(String title, String note) {
        this.taskService.createTask(title, note);
    }

    @Override
    public void createTask(String title, String note, ICategory category, LocalDate deadline) {
        this.taskService.createTask(title, note, category, deadline);
    }

    @Override
    public void updateTask(int id, String title, String note, ICategory category, LocalDate deadline) {
        this.taskService.updateTask(id, title, note, category, deadline);
    }

    @Override
    public void destroyTask(int id) {
        this.taskService.destroyTask(id);
    }

    @Override
    public ITag getTagById(int id) {
        return this.tagService.getTagById(id);
    }

    @Override
    public void createTag(String title) {
        this.tagService.createTag(title);
    }

    @Override
    public void createTag(String title, Color color) {
        this.tagService.createTag(title, color);
    }

    @Override
    public void updateTag(int id, String title, Color color) {
        this.tagService.updateTag(id, title, color);
    }

    @Override
    public void destroyTag(int id) {
        this.tagService.destroyTag(id);
    }

    @Override
    public void updateTask(int id) {
        this.taskService.updateTask(id);
    }

    @Override
    public void updateTask(int id, ITag tag) {
        this.taskService.updateTask(id, tag);
    }

}
