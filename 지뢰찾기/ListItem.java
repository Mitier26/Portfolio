package com.malkang.mitier.a105_minesweeper02;

public class ListItem {

    int rank;
    String name;
    int time;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    ListItem(int rank, String name, int time)
    {
        this.rank = rank;
        this.name = name;
        this.time = time;
    }



}
