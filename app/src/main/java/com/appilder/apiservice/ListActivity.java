package com.appilder.apiservice;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.appilder.apilibrary.ServiceApi;
import com.appilder.apilibrary.models.Repo;
import com.appilder.apiservice.adapter.ListAdapter;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ListActivity extends ActionBarActivity {
    ListView listView;
    ListAdapter adapter;
    boolean needCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = (ListView) findViewById(R.id.listView);
        needCache = getIntent().getBooleanExtra("cache", true);
        if (needCache) {
            getSupportActionBar().setTitle("Cached");
            ServiceApi.getInstance().getCachedApiService().getRepositories(new Callback<List<Repo>>() {
                @Override
                public void success(List<Repo> repos, Response response) {
                    adapter = new ListAdapter(ListActivity.this, repos);
                    listView.setAdapter(adapter);
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(ListActivity.this, "That was an error", Toast.LENGTH_LONG).show();

                }
            });
        } else {

            getSupportActionBar().setTitle("Normal");
            ServiceApi.getInstance().getApiService().getRepositories(new Callback<List<Repo>>() {
                @Override
                public void success(List<Repo> repos, Response response) {
                    adapter = new ListAdapter(ListActivity.this, repos);
                    listView.setAdapter(adapter);
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(ListActivity.this, "That was an error", Toast.LENGTH_LONG).show();

                }
            });
        }


    }


}
