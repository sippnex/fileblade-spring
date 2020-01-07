package com.sippnex.fileblade.domain;

import java.util.Set;

public class FbDirectory extends FbElement {

    private Set<FbElement> elements;

    public FbDirectory(String path) {
        super(path, true);
    }

    public FbDirectory(String path, Set<FbElement> elements) {
        super(path, true);
        this.elements = elements;
    }

    public Set<FbElement> getElements() {
        return elements;
    }
}
