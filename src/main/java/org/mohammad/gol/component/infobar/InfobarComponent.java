package org.mohammad.gol.component.infobar;

import org.mohammad.gol.component.ApplicationComponent;
import org.mohammad.gol.component.ApplicationContext;
import org.mohammad.gol.component.editor.EditorState;

public class InfobarComponent implements ApplicationComponent {



    @Override
    public void initComponent(ApplicationContext context) {
        EditorState editorState = context.getRegistry().getState(EditorState.class);

        InfoBarViewModel infoBarViewModel = new InfoBarViewModel();

        editorState.getCellPosProperty().listenTo(infoBarViewModel.getCellPostionProperty()::setValue);
        InfoBar infoBar = new InfoBar(infoBarViewModel);

        context.getMainView().setBottom(infoBar);
    }

    @Override
    public void initState(ApplicationContext context) {

    }
}
