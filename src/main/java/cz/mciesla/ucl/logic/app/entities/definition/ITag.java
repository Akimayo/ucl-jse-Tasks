package cz.mciesla.ucl.logic.app.entities.definition;

import java.time.LocalDateTime;

public interface ITag extends ITaskOwner, Comparable<ITag> {
    int getId();
    IUser getUser();
    String getTitle();
    Color getColor();
	LocalDateTime getCreatedAt();
	LocalDateTime getUpdatedAt();
	void setTitle(String title);
    void setColor(Color color);

    // see ITaskOwner
}
