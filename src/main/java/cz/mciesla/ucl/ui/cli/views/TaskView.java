package cz.mciesla.ucl.ui.cli.views;

import cz.mciesla.ucl.logic.app.entities.definition.ITask;
import cz.mciesla.ucl.ui.definition.views.ITaskView;

public class TaskView implements ITaskView {

    @Override
    public String formatTaskList(ITask[] taskList) {
        String ret = "";
        for(ITask task : taskList) {
            ret += System.lineSeparator() + task.getTitle();
        }
        return ret.substring(System.lineSeparator().length());
    }

    @Override
    public String formatTask(ITask task) {
        String ret = task.getTitle() + System.lineSeparator() + "    Kategorie";
        int catLengh = task.getCategory().getTitle().length();
        for(int i = 0; i < 16 - catLengh; i++) ret += " ";
        ret += "Značky";
        for(int i = 0; i < task.getTags().length; i++) {
            ret += System.lineSeparator() + "    ";
            if(i == 0) {
                ret += task.getCategory().getTitle();
                for(int j = 0; j < 16 - catLengh; j++) ret += " ";
            } else {
                for(int j = 0; j < 16; j++) ret += " ";
            }
            ret += task.getTags()[i].getTitle();
        }
        return ret;
    }

}
