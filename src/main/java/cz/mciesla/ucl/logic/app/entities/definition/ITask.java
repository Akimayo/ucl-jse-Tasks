package cz.mciesla.ucl.logic.app.entities.definition;

import java.time.LocalDateTime;

public interface ITask {
    //region Getters
    int getId();

    String getTitle();

    String getNote();

    IUser getUser();

    boolean isDone();

    ICategory getCategory();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();
    //endregion

    //region Tags
    ITag[] getTags();

    ITag getTag(int i);

    void saveTag(int i, ITag tag);

    void addTag(ITag tag);

    int tagsCount();
    //endregion

    //region Completion
    void complete();

    void reopen();
    //endregion

	void setTitle(String title);

	void setNote(String note);

	void setCategory(ICategory category);
}
