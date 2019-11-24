package cz.mciesla.ucl.ui.gui.views;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;

import cz.mciesla.ucl.logic.app.entities.definition.ICategory;

public class CategoryView {

    public JPanel[] formatCategoryList(ICategory[] categoryList) {
        JPanel[] out = new JPanel[categoryList.length];

        ICategory currentCategory;
        JPanel currentPanel;

        JLabel title;

        for(int  i = 0; i < categoryList.length; i++) {
            currentCategory = categoryList[i];
            currentPanel = new JPanel();

            title = new JLabel(currentCategory.getTitle());
            Color fg;
            switch (currentCategory.getColor()) {
                case BLUE:
                fg = Color.BLUE;
                break;
                case GREEN:
                fg = Color.GREEN;
                break;
                case RED:
                fg = Color.RED;
                break;
                case BLACK:
                default:
                    fg = Color.BLACK;
                    break;
            }
            title.setForeground(fg);
            currentPanel.add("title", title);

            out[i] = currentPanel;
        }
        
        return out;
    }

    public String formatCategory(ICategory category) {
        // TODO Auto-generated method stub
        return null;
    }

}
