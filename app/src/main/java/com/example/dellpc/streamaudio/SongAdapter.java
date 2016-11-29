package com.example.dellpc.streamaudio;

import android.content.Context;
import android.content.Intent;

import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Dell Pc on 27-11-2016.
 */
public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongHolder> {

    ArrayList<SongDetails> songlist = new ArrayList<>();
    Context ctx;



    public SongAdapter(ArrayList<SongDetails> songlist, Context ctx) {
        this.songlist = songlist;
        this.ctx = ctx;
    }

    @Override
    public SongHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.songcards,parent,false);
        SongHolder sh = new SongHolder(view,ctx);

        return sh;
    }

    @Override
    public void onBindViewHolder(SongHolder holder, int position) {
        SongDetails sd = songlist.get(position);
       holder.st.setText(sd.getSongtitle());
        holder.sa.setText(sd.getSongartist());

    }

    @Override
    public int getItemCount() {
        return songlist.size();
    }
    public static class SongHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView st,sa;
        Context ctx;

        public SongHolder(View itemView, Context ctx) {
            super(itemView);

            this.ctx = ctx;
            st = (TextView) itemView.findViewById(R.id.songtitle);
            sa = (TextView) itemView.findViewById(R.id.songartist);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            Intent i3 = new Intent(this.ctx,Play.class);
            i3.putExtra("pos",position);
            ctx.startActivity(i3);
        }

    }

}
