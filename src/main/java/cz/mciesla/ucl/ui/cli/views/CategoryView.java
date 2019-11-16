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
        String ret = "Kategorie " + category.getTitle();
        for (ITask task : category.getTasks()) {
            ret += System.lineSeparator() + "    " + task.getTitle();
        }
        return ret;
    }

    
}