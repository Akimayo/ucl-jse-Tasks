package cz.mciesla.ucl.logic.app.entities.definition;

import java.time.LocalDateTime;

public interface IUser extends ITaskOwner {
    int getId();
    String getEmail();
    String getUsername();
    String getPassword();

    // see ITaskOwner

    ICategory[] getCategories();
    ICategory getCategory(int i);
    void saveCategory(int i, ICategory category);
    void addCategory(ICategory category);
    int categoriesCount();

    ITag[] getTags();
    ITag getTag(int i);
    void saveTag(int i, ITag tag);
    void addTag(ITag tag);
    int tagsCount();
	LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();

    void setEmail(String email);
    void setUsername(String username);
    void setPassword(String checkedPassword);
}
