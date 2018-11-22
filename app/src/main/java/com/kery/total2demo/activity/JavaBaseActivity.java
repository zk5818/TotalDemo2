package com.kery.total2demo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.kery.total2demo.R;
import com.kery.total2demo.model.Student;

import java.util.Iterator;
import java.util.TreeSet;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JavaBaseActivity extends AppCompatActivity {
    @Bind(R.id.button3)
    Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_base);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.button3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button3:
//                HashSet是set的实现类,底层是由哈希算法实现的

//                无序集合，去重
//                HashSet<String> hashSet = new HashSet<>();
//                hashSet.add("c");
//                hashSet.add("b");
//                hashSet.add("d");
//                hashSet.add("d");
//                System.out.println(hashSet);

//                HashSet<Student> hashSet = new HashSet<>();
//                hashSet.add(new Student("张三", 18));
//                hashSet.add(new Student("李四", 19));
//                hashSet.add(new Student("张三", 18));
//                System.out.println(hashSet);
//                break;

//                TreeSet是Set的一个实现类,底层是由二叉树实现的

//                TreeSet<Integer> treeSet = new TreeSet<>();
//                treeSet.add(10);
//                treeSet.add(8);
//                treeSet.add(15);
//                treeSet.add(11);
//                treeSet.add(8);
//                System.out.println(treeSet);//[8, 10, 11, 15]//我们可以根据输出的结果看出TreeSet会将对象排序并将重复的删除了

                TreeSet<Student> treeSet = new TreeSet<>();
                treeSet.add(new Student("张三", 18));
                treeSet.add(new Student("才就", 18));
                treeSet.add(new Student("李四", 19));
//                System.out.println(treeSet);
                Iterator iterator=treeSet.iterator();
                if (iterator.hasNext()){
                    ((Student)iterator.next()).getAge();
                }
        }
    }
}