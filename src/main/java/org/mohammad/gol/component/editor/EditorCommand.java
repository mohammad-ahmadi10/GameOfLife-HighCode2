package org.mohammad.gol.component.editor;

import org.mohammad.app.command.Command;

public interface EditorCommand extends Command<EditorState> {

    @Override
    void execute(EditorState editorState);

    @Override
    default Class<EditorState> getInsClass() {
        return EditorState.class;
    }
}
