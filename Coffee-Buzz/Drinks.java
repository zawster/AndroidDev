package com.example.root.coffeebuzz;

public class Drinks {
    private String name,desc;
    private int imgID;

    public static final Drinks[] drinks={
            new Drinks("Latte","this is description for latte",R.drawable.latte),
            new Drinks("Cappuccino","this is description for cappuccino",R.drawable.latte),
            new Drinks("Filter","this is description for filter",R.drawable.coffee4),

    };
    private Drinks(String name, String desc, int imgID){
        this.name=name;
        this.desc=desc;
        this.imgID=imgID;
    }

    public String getName(){
        return name;
    }
    public String getDes(){
        return desc;
    }
    public int getImgID(){
        return imgID;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

