package com.example.businessnewsapi;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class BusinessFragment extends Fragment implements BusinessNewsAdapter.ItemClickListener {
    private static final String TAG = "BusinessFragment";
    private static final String LOG_TAG = "Debug";

    private BusinessNewsAdapter businessNewsAdapter;
    private RecyclerView recyclerView;
    private ArrayList<ArticleNewsModel> mBusinessModelList;
    private ProgressBar bar;
    private SwipeRefreshLayout mSwipeRefreshLayout;


    public static Fragment newInstance() {
        return new BusinessFragment();
    }

    public BusinessFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_business, container, false);
        recyclerView = view.findViewById(R.id.recyclerView1);
        mBusinessModelList = new ArrayList<>();
        mSwipeRefreshLayout = view.findViewById(R.id.swiperefresh);

        String baseUrl = "https://newsapi.org/v2/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BusinessModelApi businessModelApi = retrofit.create(BusinessModelApi.class);
        Call<WholeNews> wholeNewsCall = businessModelApi.getBusinessNews("us", "business", "f4df836ab4b74c7797c82233c0aee258");
        wholeNewsCall.enqueue(new Callback<WholeNews>() {
            @Override
            public void onResponse(Call<WholeNews> call, Response<WholeNews> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "An error occurred", Toast.LENGTH_LONG).show();
                }

                assert response.body() != null;
                ArrayList<ArticleNewsModel> newsModelliist = response.body().getModel();
                mBusinessModelList.addAll(newsModelliist);
                businessNewsAdapter = new BusinessNewsAdapter(getActivity(), mBusinessModelList);
                myUpdateOperation();
                businessNewsAdapter.setClicklIstener(BusinessFragment.this);
                bar = view.findViewById(R.id.prog);
                bar.setVisibility(View.GONE);

            }


            @Override
            public void onFailure(Call<WholeNews> call, Throwable t) {
                Log.d(TAG, "Something is wrong " + t.getMessage());
            }
        });


        mSwipeRefreshLayout.setOnRefreshListener(() -> {
                businessNewsAdapter.notifyDataSetChanged();
                new Handler().postDelayed(() -> mSwipeRefreshLayout.setRefreshing(false),5000);

        });

        return view;
    }



    @Override
    public void readMore(int position) {
        ArticleNewsModel articleNewsModel = mBusinessModelList.get(position);
        Uri uri = Uri.parse(articleNewsModel.getUri());
        Intent i = NewsDetailsActivity.newIntent(getActivity(), uri );
        startActivity(i);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {

            // Check if user triggered a refresh:
            case R.id.menu_refresh:
                Log.i(LOG_TAG, "Refresh menu item selected");

                // Signal SwipeRefreshLayout to start the progress indicator
                mSwipeRefreshLayout.setRefreshing(true);

                // Start the refresh background task.
                // This method calls setRefreshing(false) when it's finished.
                myUpdateOperation();

                return true;

        }

        return super.onOptionsItemSelected(item);

    }

    private void myUpdateOperation(){
        recyclerView.setAdapter(businessNewsAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
    }
}
