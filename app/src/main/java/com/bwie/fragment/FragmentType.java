package com.bwie.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.bwie.R;
import com.bwie.adapter.LeftRecyclerView_Adapter;
import com.bwie.adapter.RightRecyclerView_Adapter;
import com.bwie.bean.ChildrenBean;
import com.bwie.bean.RedBaby;
import com.bwie.bean.RsBean;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import baseactivity.BaseFragment;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentType extends BaseFragment {


    EditText mEditText;

    RecyclerView leftRecyclerView;

    RecyclerView rightRecyclerView;
    ;
    List<ChildrenBean> AllList;
    private LeftRecyclerView_Adapter leftadapter;
    private List<RsBean> rs;
    private RightRecyclerView_Adapter rightadapter;

    @Override
    public int getLayout() {
        return R.layout.fragment_type;
    }

    @Override
    public void initUI() {
        mEditText = findview(R.id.editText);
        leftRecyclerView = (RecyclerView) getmRootView().findViewById(R.id.leftType);
        rightRecyclerView = (RecyclerView) getmRootView().findViewById(R.id.rightType);
       AllList= new ArrayList<ChildrenBean>();
    }

    @Override
    public void getData() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    InputStream inputStream = getActivity().getAssets().open("category.txt");
                    byte[] bytes = new byte[1024];
                    int len;
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    while ((len = inputStream.read(bytes)) != -1) {
                        outputStream.write(bytes, 0, len);
                    }
                    String json = outputStream.toString("utf-8");
                    System.out.println("json"+json);
                    final RedBaby redBaby = new Gson().fromJson(json, RedBaby.class);
                    rs = redBaby.getRs();
                    leftRecyclerView.post(new Runnable() {
                        @Override
                        public void run() {
                            //设置布局管理器
                            leftRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            //设置适配器
                            leftadapter = new LeftRecyclerView_Adapter(getActivity(), rs);
                            leftRecyclerView.setAdapter(leftadapter);
                            //设置分割线
                            leftRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));

                            //点击事件
                            leftadapter.setOnRecyclerItemClickListener(new LeftRecyclerView_Adapter.OnRecyclerItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    update(position);
                                    Toast.makeText(getActivity(),"item"+position,Toast.LENGTH_SHORT).show();
                                }
                            });

                            //右边
                            //设备管理器
                            GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),3);
                            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                                @Override
                                public int getSpanSize(int position) {
                                    return AllList.get(position).isHeader?3:1;
                                }
                            });
                            rightRecyclerView.setLayoutManager(gridLayoutManager);
                            //设置适配器
                            List<ChildrenBean> childrenBeen = rs.get(0).getChildren();
                            for (int i = 0; i < childrenBeen.size(); i++) {
                                childrenBeen.get(i).isHeader=true;
                                AllList.add(childrenBeen.get(i));
                                AllList.addAll(childrenBeen.get(i).getChildren());
                            }
                            rightadapter = new RightRecyclerView_Adapter(AllList,getActivity());
                            rightRecyclerView.setAdapter(rightadapter);


                            }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();




    }
    public void update(int position){
        leftadapter.notifyDataSetChanged();
        AllList.clear();
        List<ChildrenBean> childrenBeen =rs.get(position).getChildren();
        for (int i = 0; i < childrenBeen.size(); i++) {
            childrenBeen.get(i).isHeader=true;
            AllList.add(childrenBeen.get(i));
            AllList.addAll(childrenBeen.get(i).getChildren());
        }

        rightadapter.notifyDataSetChanged();
    }
    @Override
    public void loadData() {

    }

}
