package com.example.uasmcs_2301865842;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ViewHolder> {

    Context ctx;
    ArrayList<Word> wordList;

    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void OnSaveClick(int position);
        void OnItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) { mListener = listener; }

    public ExploreAdapter(Context context) { this.ctx = ctx; }

    public void setWordList(ArrayList<Word> mWordList) { wordList = mWordList; }

    @Override
    public ExploreAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.explore_list, parent, false);
        ExploreAdapter.ViewHolder evh = new ExploreAdapter.ViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExploreAdapter.ViewHolder holder, int position) {
        Word currentItem = wordList.get(position);

        holder.itemName.setText(currentItem.getwName());
    }

    @Override
    public int getItemCount() {
        if (wordList != null) {
            return wordList.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView itemName;
        Button save;

        public ViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);

            itemName = itemView.findViewById(R.id.tvItemName);
            save = itemView.findViewById(R.id.saveBtn);

            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION)
                        {
                            mListener.OnSaveClick(position);
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
