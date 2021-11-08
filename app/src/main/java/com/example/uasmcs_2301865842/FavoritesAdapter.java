package com.example.uasmcs_2301865842;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {

    Context ctx;
    ArrayList<Word> wordsList;

    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void OnDeleteClick(int position);
        void OnItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) { mListener = listener; }

    public FavoritesAdapter(Context ctx) { this.ctx = ctx; }

    public void setWordsList(ArrayList<Word> mWordList) { wordsList = mWordList; notifyDataSetChanged();}

    @Override
    public FavoritesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorites_list, parent, false);
        FavoritesAdapter.ViewHolder fvh = new FavoritesAdapter.ViewHolder(v, mListener);
        return fvh;
    }

    @Override
    public void onBindViewHolder(FavoritesAdapter.ViewHolder holder, int position) {
        Word currentItem = wordsList.get(position);

        holder.itemName.setText(currentItem.getwName());
    }

    @Override
    public int getItemCount() {
        if (wordsList != null) {
            return wordsList.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView itemName;
        Button delete;

        public ViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);

            itemName = itemView.findViewById(R.id.tvFavName);
            delete = itemView.findViewById(R.id.delBtn);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION)
                        {
                            mListener.OnDeleteClick(position);
                        }
                    }
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                    {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.OnItemClick(position);
                        }
                    }
                }
            });
        }
    }
}