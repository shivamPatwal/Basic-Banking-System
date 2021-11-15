package com.example.bankingapp.adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.bankingapp.fragments.AllTranscations;
import com.example.bankingapp.fragments.AllUsers;

public class MyAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;
    public MyAdapter(Context c, FragmentManager fm, int totalTabs) {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                AllUsers usersFragment = new AllUsers();
                return usersFragment;
            case 1:
                AllTranscations usersTranscations = new AllTranscations();
                return usersTranscations;

            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return totalTabs;
    }
}