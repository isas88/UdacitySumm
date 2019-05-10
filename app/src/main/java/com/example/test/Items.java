package com.example.test;

public class Items {

    private String head;
    private String desc;
    public static int test_static;

    public Items(String head, String desc){
        this.head = head;
        this.desc = desc;
        ++ this.test_static;
    }

    public String getHead(){
        return head;
    }

    public String getDesc() {
        return desc;
    }

    public int getTest_static(){
        return test_static;
    }

}
