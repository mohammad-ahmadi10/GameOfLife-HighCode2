package org.mohammad.gol.logic.editor;

import org.mohammad.gol.command.Command;
import org.mohammad.gol.state.EditorState;

public interface EditorCommand extends Command<EditorState> {

    @Override
    void execute(EditorState editorState);

    @Override
    default Class<EditorState> getInsClass() {
        return EditorState.class;
    }
}
