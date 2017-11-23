package com.example.android.miwok;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Pavlo Kotelnytskyi on 8/24/2017.
 */

public class CategoriesPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;

    public CategoriesPagerAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        mContext = context;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0: return mContext.getResources().getString(R.string.category_numbers);
            case 1: return mContext.getResources().getString(R.string.category_colors);
            case 2: return mContext.getResources().getString(R.string.category_family);
            case 3: return mContext.getResources().getString(R.string.category_phrases);
            default: return null;
        }
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new NumbersFragment();
            case 1: return new ColorsFragment();
            case 2: return new FamilyFragment();
            case 3: return new PhrasesFragment();
            default: return null;
        }
    }
}
