package com.kery.total2demo.model;

/**
 * Created by Administrator on 2018/9/3.
 */

public class Fruit {
    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public static <T extends Fruit> String getFruitName(T t){
        return t.getName();
    }
    class Banana extends Fruit{
        public Banana(){
            setName("bababa");
        }
    }
    class Apple extends Fruit{
        public Apple(){
            setName("apple");
        }
    }

}
