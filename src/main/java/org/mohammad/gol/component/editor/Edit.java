package org.mohammad.gol.component.editor;

import java.util.HashSet;

public class Edit extends HashSet<Change> {

    public Edit(){}
    public Edit(Edit edit) {
        super(edit);
    }
}
