package com.holidu.tree;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class TreeData implements Tree {

    @JsonProperty("spc_common")
    private String name;

    public TreeData() {
        // for serialization
    }

    public TreeData(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreeData treeData = (TreeData) o;
        return name.equals(treeData.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "TreeData{" +
                "name='" + name + '\'' +
                '}';
    }
}
