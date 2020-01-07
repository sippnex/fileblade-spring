package com.sippnex.fileblade.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class FbElement {

    private String path;

    private Boolean directory;

    public String getPath() {
        return path;
    }

    public Boolean isDirectory() {
        return directory;
    }

    public String getName() {
        return path.substring(path.lastIndexOf("/") + 1);
    }

    public FbElement(String path, Boolean directory) {
        this.path = path;
        this.directory = directory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FbElement that = (FbElement) o;
        return path.equals(that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path);
    }
}

