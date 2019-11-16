package cz.mciesla.ucl.ui.definition.views;

import cz.mciesla.ucl.logic.app.entities.definition.ITag;

/** Views are used only for formatting purposes. They are stateless. */
public interface ITagView {
    String formatTagList(ITag[] tagList);
    String formatTag(ITag tag);
}
