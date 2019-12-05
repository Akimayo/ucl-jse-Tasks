package cz.mciesla.ucl.ui.cli.views;

import cz.mciesla.ucl.logic.app.entities.definition.ICategory;
import cz.mciesla.ucl.logic.app.entities.definition.ITask;
import cz.mciesla.ucl.ui.definition.views.ICategoryView;

/**
 * CategoryView
 */
public class CategoryView implements ICategoryView {

    @Override
    public String formatCategoryList(ICategory[] categoryList) {
        String ret = "";
        for(ICategory category : categoryList) {
            ret += System.lineSeparator() + category.getTitle() + "(" + category.tasksCount() + " úkolů)";
        }
        return ret.substring(System.lineSeparator().length());
    }

    @Override
    public String formatCategory(ICategory category) {
        StringBuilder ret = new StringBuilder("= Barva: " + category.getColor().name() + " =" + System.lineSeparator());
        ITask[] catTasks = category.getTasks();
        if(catTasks.length > 0) ret.append("= " + catTasks.length + " úkolů =");
        else ret.append("= Zatím žádné úkoly =");
        ret.append(System.lineSeparator());
        for (ITask task : catTasks) {
            ret.append(System.lineSeparator() + "    " + task.getTitle());
        }
        return ret.toString();
    }

    
}