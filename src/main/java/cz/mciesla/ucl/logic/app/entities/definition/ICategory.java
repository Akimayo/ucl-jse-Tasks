package cz.mciesla.ucl.logic.app.entities.definition;

import java.time.LocalDateTime;

public interface ICategory extends ITaskOwner {
    int getId();
    IUser getUser();
    String getTitle();
    Color getColor();
	LocalDateTime getCreatedAt();
	LocalDateTime getUpdatedAt();

    // see ITaskOwner
}
