package com.kery.total2demo.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.kery.total2demo.R;
import com.kery.total2demo.fragment.Fragment1;
import com.kery.total2demo.fragment.Fragment2;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ChartActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.tv1)
    TextView tv1;
    @Bind(R.id.tv2)
    TextView tv2;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        ButterKnife.bind(this);
        fragmentManager = getFragmentManager();
        tv1.setOnClickListener(this
        );
        tv2.setOnClickListener(this
        );
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv1:
                // 当点击了消息tab时，选中第1个tab
                setTabSelection(0);
                break;
            case R.id.tv2:
                // 当点击了设置tab时，选中第4个tab
                setTabSelection(1);
                break;
            default:
                break;
        }
    }

    private Fragment1 messageFragment;

    private Fragment2 contactsFragment;
    private void setTabSelection(int index) {
        // 每次选中之前先清楚掉上次的选中状态
        clearSelection();
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case 0:
                tv1.setBackgroundColor(0xff0000ff);

                if (messageFragment == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    messageFragment = new Fragment1();
                    transaction.add(R.id.content, messageFragment);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(messageFragment);
                }
                break;
            case 1:
                // 当点击了联系人tab时，改变控件的图片和文字颜色
                tv2.setBackgroundColor(0xff0000ff);
                if (contactsFragment == null) {
                    // 如果ContactsFragment为空，则创建一个并添加到界面上
                    contactsFragment = new Fragment2();
                    transaction.add(R.id.content, contactsFragment);
                } else {
                    // 如果ContactsFragment不为空，则直接将它显示出来
                    transaction.show(contactsFragment);
                }
                break;
        }
        transaction.commit();
    }
    private void hideFragments(FragmentTransaction transaction) {
        if (messageFragment != null) {
            transaction.hide(messageFragment);
        }
        if (contactsFragment != null) {
            transaction.hide(contactsFragment);
        }
    }

    /**
     * 清除掉所有的选中状态。
     */
    private void clearSelection() {
        tv1.setBackgroundColor(0xffffffff);
        tv2.setBackgroundColor(0xffffffff);
    }


}
