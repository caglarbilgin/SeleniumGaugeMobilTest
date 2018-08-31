package com.saha.bigpara.mapping;

public enum MapValue {
    ID("id"), CLASSNAME("className"), XPATH("xpath"), CSSSELECTOR("cssSelector"), CONTAINS("contains");

    private final String text;

    public String getText(){
        return text;
    }
    MapValue(String text){
        this.text= text;
    }

}
