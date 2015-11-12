package com.myshopapp.lxc.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.myshopapp.lxc.dao.BrandTitle;
import com.myshopapp.lxc.fragment.BrandFragment;

import java.util.List;

/**
 * Created by Administrator on 2015/10/16.
 */
public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {

    private List<BrandFragment> list;
    private List<BrandTitle> titles;
    public ViewPagerFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public ViewPagerFragmentAdapter(FragmentManager fm,List<BrandFragment> list, List<BrandTitle> titles) {
        super(fm);

        this.list = list;
        this.titles = titles;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position).getName();
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

}
