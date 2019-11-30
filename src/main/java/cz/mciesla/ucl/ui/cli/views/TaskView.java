package cz.mciesla.ucl.ui.cli.views;

import java.time.format.DateTimeFormatter;

import cz.mciesla.ucl.logic.app.entities.definition.ITask;
import cz.mciesla.ucl.ui.definition.views.ITaskView;

public class TaskView implements ITaskView {
    private DateTimeFormatter format;

    public TaskView() {
        this.format = DateTimeFormatter.ofPattern("dd. MM. YYYY HH:mm:ss");
    }

    @Override
    public String formatTaskList(ITask[] taskList) {
        String ret = "";
        for (ITask task : taskList) {
            ret += System.lineSeparator() + task.getTitle();
        }
        return ret.substring(System.lineSeparator().length());
    }

    @Override
    public String formatTask(ITask task) {
        StringBuilder ret = new StringBuilder("= " + task.getNote() + " =" + System.lineSeparator() + System.lineSeparator());
        ret.append("Vytvořeno: ").append(task.getCreatedAt().format(this.format)).append(System.lineSeparator());
        ret.append(" Upraveno: ").append(task.getUpdatedAt().format(this.format)).append(System.lineSeparator());
        ret.append("    Kategorie");
        int catLength;
        if (task.getCategory() == null) catLength = 0;
        else catLength = task.getCategory().getTitle().length();
        for (int i = 0; i < 16 - catLength; i++)
            ret.append(" ");
        ret.append("Značky");
        for (int i = 0; i < task.getTags().length; i++) {
            ret.append(System.lineSeparator()).append("    ");
            if (i == 0) {
                ret.append(task.getCategory().getTitle());
                for (int j = 0; j < 16 - catLength; j++)
                    ret.append(" ");
            } else {
                for (int j = 0; j < 16; j++)
                    ret.append(" ");
            }
            ret.append(task.getTags()[i].getTitle());
        }
        return ret.toString();
    }

}
