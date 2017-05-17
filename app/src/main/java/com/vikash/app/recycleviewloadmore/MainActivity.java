package com.vikash.app.recycleviewloadmore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> mRecycleViewItems;
    RecyclerView mRecycleView;
    MyAdapter myAdapter;
    public static int size =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecycleViewItems = new ArrayList<>();

        mRecycleView = (RecyclerView) findViewById(R.id.recylelistview);

        for(int i=size; i< 20;i++){
            mRecycleViewItems.add("Item "+i);
        }
        size = mRecycleViewItems.size();

        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecycleView.setLayoutManager(layoutManager);
        myAdapter = new MyAdapter(this,mRecycleViewItems);
        myAdapter.setListner(new MyAdapter.LoadMoreItem() {
            @Override
            public void onLoad() {

                for(int i=size; i< size+20;i++){
                    mRecycleViewItems.add("Item "+i);
                }
                size = mRecycleViewItems.size();
                myAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "loading more data", Toast.LENGTH_SHORT).show();
            }
        });
        mRecycleView.setAdapter(myAdapter);

    }
}
