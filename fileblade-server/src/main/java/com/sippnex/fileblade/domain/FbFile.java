package com.sippnex.fileblade.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class FbFile extends FbElement {

    @JsonIgnore
    private byte[] content;

    public byte[] getContent() {
        return content;
    }

    public FbFile(String path) {
        super(path, false);
    }

    public FbFile(String path, byte[] content) {
        super(path, false);
        this.content = content;
    }

}
