package com.example.uasmcs_2301865842;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FavoritesDetailAdapter extends RecyclerView.Adapter<FavoritesDetailAdapter.ViewHolder> {

    Context ctx;
    ArrayList<Definitions> defList;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {}

    public void setOnItemClickListener(OnItemClickListener listener) { mListener = listener; }

    public FavoritesDetailAdapter(ArrayList<Definitions> mDefList) { defList = mDefList; }

    @Override
    public FavoritesDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_definitions_list, parent, false);
        FavoritesDetailAdapter.ViewHolder fdvh = new FavoritesDetailAdapter.ViewHolder(v, mListener);
        return fdvh;
    }

    @Override
    public void onBindViewHolder(FavoritesDetailAdapter.ViewHolder holder, int position) {
        Definitions currentItem = defList.get(position);

        holder.wType.setText("Type: " + currentItem.getType());

        holder.noImg.setVisibility(View.VISIBLE);

        String url = currentItem.getImgURL();

        if (url.contains("null"))
        {
            holder.noImg.setVisibility(View.VISIBLE);
            holder.tNo.setVisibility(View.VISIBLE);
            holder.tImg.setVisibility(View.VISIBLE);
        }
        else {
            Picasso.get().load(url).fit().centerInside().into(holder.wPict);
            holder.noImg.setVisibility(View.INVISIBLE);
            holder.tNo.setVisibility(View.INVISIBLE);
            holder.tImg.setVisibility(View.INVISIBLE);
        }

        holder.wDef.setText(currentItem.getDefinition());
    }

    @Override
    public int getItemCount() {
        if (defList != null) {
            return defList.size();
        }
        return  0;
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView wType;
        ImageView wPict;
        TextView wDef;

        RelativeLayout noImg;
        TextView tNo;
        TextView tImg;

        public ViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);

            wType = itemView.findViewById(R.id.tvType1);
            wPict = itemView.findViewById(R.id.ivPict1);
            wDef = itemView.findViewById(R.id.tvDefini1);

            noImg = itemView.findViewById(R.id.relNoImage1);
            tNo = itemView.findViewById(R.id.tNO1);
            tImg = itemView.findViewById(R.id.tImg1);
        }
    }
}