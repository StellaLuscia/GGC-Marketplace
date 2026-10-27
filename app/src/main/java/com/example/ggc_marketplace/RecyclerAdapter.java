package com.example.ggc_marketplace;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{

    //Used to display listings in fragments - creates a scrolling list & reuses/recycles views to improve performance
    private ArrayList<Model> list;
    private RecyclerViewClickListener listener;

    //Main constructor - passes selected list & onClickListener to view individual listings' info
    public RecyclerAdapter(ArrayList<Model> list, RecyclerViewClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView nameTxt, priceTxt;
        private ImageView img;

        //Used to display listing's name, price, and image when scrolling through recyclerview's list
        public MyViewHolder(final View v) {
            super(v);
            //Sets up views/focus
            nameTxt = v.findViewById(R.id.name);
            priceTxt = v.findViewById(R.id.price);
            img = v.findViewById(R.id.imageView);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return new MyViewHolder(itemView);
    }

    //Attaches name, price, and image to each listing in recyclerview
    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        String name = list.get(position).getName();
        String price = list.get(position).getPrice();
        holder.nameTxt.setText(name);
        holder.priceTxt.setText(price);
        holder.img.setImageResource(list.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public interface RecyclerViewClickListener {
        void onClick(View v, int position);
    }
}
