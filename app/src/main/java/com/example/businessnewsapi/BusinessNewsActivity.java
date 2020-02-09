package com.example.businessnewsapi;


import androidx.fragment.app.Fragment;

public class BusinessNewsActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return BusinessFragment.newInstance();
    }
}
