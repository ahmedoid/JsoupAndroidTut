package com.tatbigy.jsoupandroidtut.adapter;

import android.content.Context;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.florent37.glidepalette.GlidePalette;
import com.tatbigy.jsoupandroidtut.R;
import com.tatbigy.jsoupandroidtut.model.News;

import java.util.List;

public class RVNewsAdapter extends RecyclerView.Adapter<RVNewsAdapter.CityViewHolder> {
    List<News> items;
    Context ctx;

    public RVNewsAdapter(Context ctx, List<News> items) {
        this.items = items;
        this.ctx = ctx;
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_card, parent, false);
        return new CityViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final CityViewHolder holder, int position) {
        holder.tname.setText(items.get(position).getTitle());
        Glide.with(ctx)
                .load(items.get(position).getImage())
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .listener(GlidePalette.with(items.get(position).getImage())

                        .intoCallBack(new GlidePalette.CallBack() {

                            @Override
                            public void onPaletteLoaded(Palette palette) {


                                if (palette != null) {
                                    Palette.Swatch s = palette.getVibrantSwatch();
                                    if (s == null) {
                                        s = palette.getDarkVibrantSwatch();
                                    }
                                    if (s == null) {
                                        s = palette.getLightVibrantSwatch();
                                    }
                                    if (s == null) {
                                        s = palette.getMutedSwatch();
                                    }
                                    if (s != null){
                                        holder.tname.setTextColor(s.getTitleTextColor());
                                        holder.tdesc.setTextColor(s.getTitleTextColor());
                                        holder.tname.setBackgroundColor(s.getRgb());
                                        holder.card.setCardBackgroundColor(s.getRgb());
                                    }

                                }
                            }
                        }))
                .into(holder.imgthumbnail);

        holder.tdesc.setText(items.get(position).getDesc());
        Log.w("name : ", items.get(position).getTitle());


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class CityViewHolder extends RecyclerView.ViewHolder {

        TextView tdesc;
        TextView tname;
        ImageView imgthumbnail;
        CardView card;

        CityViewHolder(View itemView) {
            super(itemView);
            tdesc = (TextView) itemView.findViewById(R.id.t_desc);
            tname = (TextView) itemView.findViewById(R.id.t_name);
            card = (CardView) itemView.findViewById(R.id.card);
            imgthumbnail = (ImageView) itemView.findViewById(R.id.img_thumbnail);


        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}