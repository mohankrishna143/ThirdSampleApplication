package com.android.sampleapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


public class DynamicFragmentAdapter extends FragmentStatePagerAdapter {
    private int mNumOfTabs;

    DynamicFragmentAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle b = new Bundle();
        Fragment frag = null;
        if(position==0) {
            b = new Bundle();
            b.putInt("position", position);
            frag = DynamicFragment.newInstance();
            frag.setArguments(b);
        }else if(position==1){
            b = new Bundle();
            b.putInt("position", position);
            frag = SecondFragment.newInstance();
            frag.setArguments(b);
        }else if(position==2){
            b = new Bundle();
            b.putInt("position", position);
            frag = ThirdScreen.newInstance();
            frag.setArguments(b);
        }/*else if(position==3){
            b = new Bundle();
            b.putInt("position", position);
            frag = SecondFragment.newInstance();
            frag.setArguments(b);
        }*/
        return frag;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
