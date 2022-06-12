package com.revature.model;

public class Example {

    public int id;
    public String name;
    public String desc;

    public Example(){

    }

    public Example(int id, String name, String desc){
        this.id=id;
        this.name=name;
        this.desc=desc;
    }

    public String toString(){
        return "Example Id: "+id +"\n" +
                "Example Name: "+name+"\n" +
                "Example Desc: "+desc+"\n";
    }
}
