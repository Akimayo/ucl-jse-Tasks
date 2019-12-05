package cz.mciesla.ucl.logic.app.entities.definition;

import java.time.LocalDateTime;

public interface ICategory extends ITaskOwner, Comparable<ICategory> {
    int getId();
    IUser getUser();
    String getTitle();
    Color getColor();
	LocalDateTime getCreatedAt();
	LocalDateTime getUpdatedAt();
	void setColor(Color color);
	void setTitle(String title);

    // see ITaskOwner
}
