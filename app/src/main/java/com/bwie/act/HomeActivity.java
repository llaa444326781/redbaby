package com.bwie.act;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bwie.R;
import com.bwie.fragment.FragmentBuy;
import com.bwie.fragment.FragmentHome;
import com.bwie.fragment.FragmentMy;
import com.bwie.fragment.FragmentType;

import java.util.ArrayList;
import java.util.List;

import baseactivity.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity {

    @Bind(R.id.btn_home)
    RadioButton btn_Home;
    @Bind(R.id.btn_type)
    RadioButton btn_Type;
    @Bind(R.id.btn_buy)
    RadioButton btn_Buy;
    @Bind(R.id.btn_my)
    RadioButton btn_My;
    @Bind(R.id.mRadioGroup)
    RadioGroup mRadioGroup;
    private List<RadioButton> bts = new ArrayList<RadioButton>();

    @Override
    public int getLayout() {
        return R.layout.activity_home;
    }

    @Override
    public void initUI() {
        final FragmentHome mFragmentHome = new FragmentHome();
        final FragmentType mFragmentType = new FragmentType();
        final FragmentBuy mFragmentBuy = new FragmentBuy();
        final FragmentMy mFragmentMy = new FragmentMy();
        bts.add(btn_Home);
        bts.add(btn_Type);
        bts.add(btn_Buy);
        bts.add(btn_My);
        addFragment(R.id.mFrameLayout,mFragmentHome);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                switch (checkedId) {
                    case R.id.btn_home:
                        replaceFragment(R.id.mFrameLayout,mFragmentHome);
                        break;
                    case R.id.btn_type:
                        replaceFragment(R.id.mFrameLayout,mFragmentType);
                        break;
                    case R.id.btn_buy:
                        replaceFragment(R.id.mFrameLayout,mFragmentBuy);
                        break;
                    case R.id.btn_my:
                        replaceFragment(R.id.mFrameLayout,mFragmentMy);
                        break;

                }
            }
        });
    }

    @Override
    public void getData() {

    }

    @Override
    public void loadData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
