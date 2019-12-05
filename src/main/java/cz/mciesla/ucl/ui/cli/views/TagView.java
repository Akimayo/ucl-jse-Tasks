package cz.mciesla.ucl.ui.cli.views;

import java.time.format.DateTimeFormatter;

import cz.mciesla.ucl.logic.app.entities.definition.ITag;
import cz.mciesla.ucl.logic.app.entities.definition.ITask;
import cz.mciesla.ucl.ui.definition.views.ITagView;

/**
 * TagView
 */
public class TagView implements ITagView {

    private DateTimeFormatter format;

    public TagView() {
        this.format = DateTimeFormatter.ofPattern("dd. MM. YYYY HH:mm:ss");
    }

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
        StringBuilder ret = new StringBuilder("= Barva: " + tag.getColor().name() + " =" + System.lineSeparator());
        ITask[] tagTasks = tag.getTasks();
        if(tagTasks.length > 0) ret.append("= " + tagTasks.length + " úkolů =");
        else ret.append("= Zatím žádné úkoly =");
        ret.append(System.lineSeparator());
        ret.append("Vytvořeno: " + tag.getCreatedAt().format(this.format) + System.lineSeparator());
        ret.append(" Upraveno: " + tag.getUpdatedAt().format(this.format) + System.lineSeparator());
        ret.append("    Úkoly");
        for (ITask task : tag.getTasks()) {
            ret.append(System.lineSeparator() + "    " + task.getTitle());
        }
        return ret.toString();
    }

}