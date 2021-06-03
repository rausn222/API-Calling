package com.raushan.apisql;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.raushan.apisql.database.DatabaseAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    List<diallist> movieList;
    List<diallist1> movieList1;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    DatabaseAdapter dbadapter;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String key = "key" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieList = new ArrayList<>();
        movieList1 = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new RecyclerAdapter(getApplicationContext(),movieList1);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        dbadapter = new DatabaseAdapter(getApplicationContext(),movieList);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.scrollToPosition(sharedpreferences.getInt(key,0));
        if(dbadapter.getAll().size()>0)
        {
            recyclerAdapter.setMovieList(dbadapter.getAll());
        }
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Stru> call = apiService.getData();
        call.enqueue(new Callback<Stru>() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onResponse(Call<Stru> call, Response<Stru> response) {
                Stru res = response.body();
                movieList = new ArrayList<>(Arrays.asList(res.getData()));
                Log.d("TAG","Response = "+movieList.toString());
                //add to database..
                dbadapter.insert(movieList);
                recyclerAdapter.setMovieList(dbadapter.getAll());
            }
            @Override
            public void onFailure(Call<Stru> call, Throwable t) {
                Log.d("TAG","Response Fail= "+t.toString());
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        int position = ((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
        Log.v("sf",position+" index");
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt(key, position);
        editor.apply();
    }
}