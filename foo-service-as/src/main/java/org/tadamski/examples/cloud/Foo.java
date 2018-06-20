package org.tadamski.examples.cloud;

public class Foo {

    private String name;
    private int blipCount;

    public Foo(String name, int blipCount){
        this.name = name;
        this.blipCount = blipCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBlipCount() {
        return blipCount;
    }

    public void setBlipCount(int blipCount) {
        this.blipCount = blipCount;
    }
}
