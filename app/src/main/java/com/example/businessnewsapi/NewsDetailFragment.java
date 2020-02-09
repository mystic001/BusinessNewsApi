package com.example.businessnewsapi;


import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsDetailFragment extends Fragment {

    private ProgressBar mProgressBar;
    private static final String ARG_URI = "news_page_url";
    private Uri mUri ;


    public static NewsDetailFragment newInstance(Uri NEWSPAGE){
        Bundle mystic = new Bundle();
        mystic.putParcelable(ARG_URI,NEWSPAGE);
        NewsDetailFragment detailFragment = new NewsDetailFragment();
        detailFragment.setArguments(mystic);
        return detailFragment;
    }


    public NewsDetailFragment() {
        // Required empty public constructor
    }


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news_detail, container, false);
        WebView mWeb = view.findViewById(R.id.web);
        assert getArguments() != null;
        mUri = getArguments().getParcelable(ARG_URI);
        mProgressBar = view.findViewById(R.id.progressBar);
        mWeb.getSettings().setJavaScriptEnabled(true);

        mWeb.setWebChromeClient(new WebChromeClient(){
            public void onProgressChanged(WebView webView, int newProgress) {
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                    mProgressBar.setProgress(newProgress);
                }
            }
            public void onReceivedTitle(WebView webView, String title) {
                //activity.getSupportActionBar().setSubtitle(title);
            }
        });
        mWeb.setWebViewClient(new WebViewClient());
        mWeb.loadUrl(mUri.toString());
        return view ;
    }

}
