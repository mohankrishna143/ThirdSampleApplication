package com.android.sampleapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import adapter.LiveGridAdapter;

public class ThirdScreen   extends Fragment implements View.OnClickListener  {

    Context mContext;
    RecyclerView recycler;
    LiveGridAdapter liveAdapter;
    int itemsSize;

    public static ThirdScreen newInstance() {
        return new ThirdScreen();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recclerview, container, false);

        mContext = getActivity();
        initViews(view);
        return view;
    }

    private  void initViews(View v){
        itemsSize=10;
        recycler=v.findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layoutManager);
        recycler.setItemAnimator(new DefaultItemAnimator());

        liveAdapter = new LiveGridAdapter(mContext, itemsSize);
        recycler.setAdapter(liveAdapter);
    }



    @Override
    public void onClick(View view) {

    }
}
