package com.raushan.apisql;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.raushan.apisql.database.DatabaseAdapter;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyviewHolder> {

    Context context;
    List<diallist1> movieList;
    List<diallist> movieList1;
    DatabaseAdapter dbadapter;

    public RecyclerAdapter(Context context, List<diallist1> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    public void setMovieList(List<diallist1> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerAdapter.MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_adapter,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.MyviewHolder holder, int position) {
        dbadapter = new DatabaseAdapter(context,movieList1);
        if (!movieList.get(position).getChoice().equals("not selected"))
        {
            holder.tvName.setText("You have "+movieList.get(position).getChoice()+" this profile");
            holder.tvName.setVisibility(View.VISIBLE);
            holder.acc.setVisibility(View.GONE);
            holder.rej.setVisibility(View.GONE);
        }


        holder.acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               dbadapter.updateacc(movieList.get(position));
                holder.tvName.setText("You have accepted this profile.");
                holder.tvName.setVisibility(View.VISIBLE);
                holder.acc.setVisibility(View.GONE);
                holder.rej.setVisibility(View.GONE);
            }
        });
        holder.rej.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbadapter.updaterej(movieList.get(position));
                holder.tvName.setText("You have rejected this profile.");
                holder.tvName.setVisibility(View.VISIBLE);
                holder.acc.setVisibility(View.GONE);
                holder.rej.setVisibility(View.GONE);
            }
        });
        holder.tvNumber.setText(movieList.get(position).getName());
        Glide.with(context).load(movieList.get(position).getImage()).apply(RequestOptions.centerCropTransform()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        if(movieList != null){
            return movieList.size();
        }
        return 0;

    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView image;
        TextView tvNumber;
        Button acc,rej;

        public MyviewHolder(View itemView) {
            super(itemView);
            tvName = (TextView)itemView.findViewById(R.id.resulttext);
            tvNumber = (TextView)itemView.findViewById(R.id.number);
            image = (ImageView)itemView.findViewById(R.id.img);
            acc = (Button) itemView.findViewById(R.id.accept);
            rej = (Button) itemView.findViewById(R.id.reject);
        }
    }
}