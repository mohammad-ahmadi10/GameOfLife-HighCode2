package org.mohammad.gol.command;

import org.mohammad.gol.state.EditorState;

public interface EditorCommand extends Command<EditorState>{

    @Override
    void execute(EditorState editorState);


}
