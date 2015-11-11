package com.example.tacademy.testbabycharts;

/**
 * Created by dongja94 on 2015-10-19.
 */
public class BabyItem {
    String _id;
    String id;
    int gender;
    int birth;
    String name;
    String image;

    @Override
    public String toString() {
        return name + "(" + birth + ")";
    }
}
