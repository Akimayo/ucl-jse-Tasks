package cz.mciesla.ucl.ui.cli.views;

import java.time.format.DateTimeFormatter;

import cz.mciesla.ucl.logic.app.entities.definition.ITask;
import cz.mciesla.ucl.ui.definition.views.ITaskView;

public class TaskView implements ITaskView {
    private DateTimeFormatter format;
    private DateTimeFormatter dateOnlyFormat;

    public TaskView() {
        this.format = DateTimeFormatter.ofPattern("dd. MM. YYYY HH:mm:ss");
        this.dateOnlyFormat = DateTimeFormatter.ofPattern("dd. MM. YYYY");
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
        StringBuilder ret = new StringBuilder("= " + task.getNote() + " =" + System.lineSeparator());
        if(task.getDeadline() != null)
            ret.append("= Splnit do ").append(task.getDeadline().format(this.dateOnlyFormat)).append(" =").append(System.lineSeparator());
        ret.append(System.lineSeparator());
        ret.append("Vytvořeno: ").append(task.getCreatedAt().format(this.format)).append(System.lineSeparator());
        ret.append(" Upraveno: ").append(task.getUpdatedAt().format(this.format)).append(System.lineSeparator());
        ret.append("    Kategorie");
        int catLength, iterCount = 0;
        if (task.getCategory() == null) catLength = 0;
        else {
            catLength = task.getCategory().getTitle().length();
            iterCount++;
        }
        for (int i = 0; i < 16 - 9; i++)
            ret.append(" ");
        ret.append("Značky");
        if(task.getTags().length > iterCount) iterCount = task.getTags().length;
        for (int i = 0; i < iterCount; i++) {
            ret.append(System.lineSeparator()).append("    ");
            if (i == 0) {
                if(task.getCategory() != null) ret.append(task.getCategory().getTitle());
                for (int j = 0; j < 16 - catLength; j++)
                    ret.append(" ");
            } else {
                for (int j = 0; j < 16; j++)
                    ret.append(" ");
            }
            if(task.getTags().length > 0) ret.append(task.getTags()[i].getTitle());
        }
        return ret.toString();
    }

}
