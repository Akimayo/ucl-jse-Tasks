package cz.mciesla.ucl.ui.cli.views;

import cz.mciesla.ucl.logic.app.entities.definition.ITag;
import cz.mciesla.ucl.logic.app.entities.definition.ITask;
import cz.mciesla.ucl.ui.definition.views.ITagView;

/**
 * TagView
 */
public class TagView implements ITagView {

    @Override
    public String formatTagList(ITag[] tagList) {
        String ret = "";
        for (ITag tag : tagList) {
            ret += System.lineSeparator() + tag.getTitle() + "(" + tag.tasksCount() + " úkolů)";
        }
        return ret.substring(System.lineSeparator().length());
    }

    @Override
    public String formatTag(ITag tag) {
        String ret = "Značka " + tag.getTitle();
        for (ITask task : tag.getTasks()) {
            ret += System.lineSeparator() + "    " + task.getTitle();
        }
        return ret;
    }

}