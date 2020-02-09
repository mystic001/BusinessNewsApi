package com.example.businessnewsapi;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.fragment.app.Fragment;



public class NewsDetailsActivity extends SingleFragmentActivity {
    public static Intent newIntent(Context context, Uri NewsPageUri) {
        Intent i = new Intent(context, NewsDetailsActivity.class);
        i.setData(NewsPageUri);
        return i;
    }

    @Override
    protected Fragment createFragment() {
        return NewsDetailFragment.newInstance(getIntent().getData());
    }
}
