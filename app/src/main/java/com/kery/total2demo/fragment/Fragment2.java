package com.kery.total2demo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kery.total2demo.R;

/**
 * Created by Administrator on 2018/9/27.
 */

public class Fragment2 extends Fragment {
    private TextView tv;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View messageLayout = inflater.inflate(R.layout.message, container, false);
        tv = (TextView) messageLayout.findViewById(R.id.message);
        tv.setText("哈哈哈哈哈哈222222222222");
        return messageLayout;
    }


}
