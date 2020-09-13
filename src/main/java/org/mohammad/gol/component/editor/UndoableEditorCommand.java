package org.mohammad.gol.component.editor;

import org.mohammad.app.command.UndoableCommand;

public interface UndoableEditorCommand extends UndoableCommand<EditorState> {

    @Override
    void undo(EditorState state);

    @Override
    void execute(EditorState editorState);

    @Override
    default Class<EditorState> getInsClass() {
        return EditorState.class;
    }


}
