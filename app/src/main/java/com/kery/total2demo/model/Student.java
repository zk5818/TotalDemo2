package com.kery.total2demo.model;

import android.support.annotation.NonNull;

/**
 * Created by Administrator on 2018/8/24.
 */
public class Student implements Comparable {
    private String name;
    private int age;

    public Student(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + "]";
    }

    @Override
    public int compareTo(@NonNull Object o) {
        int ageNum = this.age - ((Student) o).age;
        int nameNum = this.name.compareTo(((Student) o).name);
        //当年龄一样的时候,我们就比较俩者的姓名
        return ageNum == 0 ? nameNum : ageNum;
    }

//    //hashset去重（非基础类型的对象）
//    @Override
//    public int hashCode() {
//        //保证数据相同
//        return 1;
//    }
//    //重写equals方法
//    @Override
//    public boolean equals(Object obj)
//    {
//        Student student = (Student)obj;
//        if (student.name.equals(this.name)&&student.age == this.age)
//        {
//            return true;
//        }
//        return false;
//    }
}
