package cz.mciesla.ucl.logic.app.entities.definition;

public interface ITag extends ITaskOwner {
    int getId();
    IUser getUser();
    String getTitle();
    Color getColor();

    // see ITaskOwner
}
