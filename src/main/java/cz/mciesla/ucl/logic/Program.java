package cz.mciesla.ucl.logic;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;
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
        Optional<ICategory> categoryPot = Stream.of(this.categoryService.getAllCategories())
                .filter(i -> i.getTitle().equals(categoryTitle)).findFirst();
        if (categoryPot.isPresent())
            return this.taskService.getAllTasksByCategory(categoryPot.get());
        else
            return new ITask[0];
    }

    @Override
    public ITask[] getTasksFilteredByTags(String[] tagTitles) {
        /* More efficient than (§), but fails on IllegalStateException */
        // Stream<String> tagTitleStream = Stream.of(tagTitles);
        return this.taskService.getAllTasksByTags(Stream.of(this.tagService.getAllTags())
                .filter(
                    /*   (§) */
                    i -> Stream.of(tagTitles).anyMatch(
                        j -> j != null && j.equals(i.getTitle())
                    )
                ).toArray(ITag[]::new));
    }

    @Override
    public ITask[] getTasksFilteredByCategoryAndTags(String categoryTitle, String[] tagTitles) {
        Stream<String> tagTitleStream = Stream.of(tagTitles);
        Optional<ICategory> categoryPot = Stream.of(this.categoryService.getAllCategories())
                .filter(i -> i.getTitle().equals(categoryTitle)).findFirst();
        if (categoryPot.isPresent())
            return this.taskService.getAllTasksByTags(
                    Stream.of(this.tagService.getAllTags())
                            .filter(i -> tagTitleStream.anyMatch(j -> j.equals(i.getTitle()))).toArray(ITag[]::new),
                    categoryPot.get());
        else
            return new ITask[0];
    }

    @Override
    public ITag[] getAllTags() {
        return this.tagService.getAllTags();
    }

    @Override
    public void generateMockData() {
        try {
            userService.registerUser("shrek@farfaraway.swamp", "shrek_the_ogre", "onions4life");
            userService.loginUser("shrek@farfaraway.swamp", "onions4life");
            for (int i = 0; i < 20; i++)
                categoryService.createCategory("Kategorie " + i);
            for (int i = 0; i < 50; i++)
                tagService.createTag("Značka " + i);
            Random rnd = new Random();
            ITask[] tasksMemoryHelper;
            ICategory[] allCategories = categoryService.getAllCategories();
            ITag[] allTags = tagService.getAllTags();
            for (int i = 0; i < 400; i++) {
                int flags = rnd.nextInt(4);
                ICategory taskCategory = null;
                if ((flags & 2) > 0)
                    taskCategory = allCategories[rnd.nextInt(allCategories.length)];
                taskService.createTask("Task " + i, "", taskCategory, null);
                if ((flags & 1) > 0) {
                    tasksMemoryHelper = taskService.getAllTasks();
                    ITask thisTask = tasksMemoryHelper[tasksMemoryHelper.length - 1];
                    for (int j = rnd.nextInt(11); j > 0; j--)
                        taskService.updateTask(thisTask.getId(), allTags[rnd.nextInt(allTags.length)]);
                }

            }
            userService.logoutUser();
        } catch (EmailAddressAlreadyUsedException | AlreadyLoggedInException | InvalidCredentialsException | NotLoggedInException e) {
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

    @Override
    public void updateUserLoggedIn(String email, String username, String checkedPassword) {
        this.userService.updateUserLoggedIn(email, username, checkedPassword);
    }

}
