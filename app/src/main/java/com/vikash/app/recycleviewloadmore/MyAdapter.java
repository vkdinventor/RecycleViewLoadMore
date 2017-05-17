package com.vikash.app.recycleviewloadmore;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

/**
 * Created by vikash on 28/7/16.
 */
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    int VIEW_ITEM = 0;
    int VIEW_FOOTER = 1;

    public interface LoadMoreItem{
        void onLoad();
    }

    LoadMoreItem loadMoreItem;

    public void setListner(LoadMoreItem loadMoreItem){
        this.loadMoreItem = loadMoreItem;
    }

    Context mContext;
    List<String> arrayList;

    MyAdapter(Context context, List<String> arrayList){
        mContext=context;
        this.arrayList=arrayList;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView textviewheader;
        TextView textViewfooter;
        public ItemViewHolder(View itemView) {
            super(itemView);
            textviewheader=(TextView)itemView.findViewById(R.id.textViewm);
            textViewfooter=(TextView)itemView.findViewById(R.id.textViews);
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder{
        Button loadButton;

        public FooterViewHolder(View footer) {
            super(footer);
            loadButton=(Button) itemView.findViewById(R.id.button);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position >= arrayList.size() ){
            return VIEW_FOOTER;
        }else {
            return VIEW_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size()+1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == VIEW_ITEM){
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);
            return new ItemViewHolder(view);
        }else {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_item,parent,false);
            return new FooterViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == VIEW_ITEM ) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.textviewheader.setText(arrayList.get(position).toString());
        }else {
            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
            footerViewHolder.loadButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(loadMoreItem != null)
                        loadMoreItem.onLoad();
                }
            });

        }
    }
}
