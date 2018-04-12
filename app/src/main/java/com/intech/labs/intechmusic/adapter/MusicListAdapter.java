package com.intech.labs.intechmusic.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.intech.labs.intechmusic.R;
import com.intech.labs.intechmusic.retrofit.model.Song;
import com.intech.labs.intechmusic.ui.music.IMusicListActivity;
import com.intech.labs.intechmusic.ui.music.MusicListPresenter;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Anatoly Chernyshev on 11.04.2018.
 */
public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.MusicListViewHolder> {

    private List<Song> mSongList;
    private Context mContext;
    private ItemClickListener mItemClickListener;

    public MusicListAdapter(List<Song> songList,
                            Context context, ItemClickListener itemClickListener) {
        mSongList = songList;
        mContext = context;
        mItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public MusicListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_item, parent,
                false);
        return new MusicListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicListViewHolder holder, int position) {

        final Song item = mSongList.get(position);

        holder.itemView.setOnClickListener(v -> mItemClickListener.onItemClicked(position));

        holder.mArtistName.setText(item.getArtistName());
        holder.mSongName.setText(item.getTrackName());

        Picasso.with(mContext)
                .load(item.getArtworkUrl100())
                .into(holder.mSongArt);
    }

    @Override
    public int getItemCount() {
        return mSongList.size();
    }

    public void updateList(List<Song> songs) {
        mSongList.clear();
        mSongList.addAll(songs);
        this.notifyDataSetChanged();
    }

    public class MusicListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.song_art) ImageView mSongArt;
        @BindView(R.id.artist_name) TextView mArtistName;
        @BindView(R.id.song_name) TextView mSongName;

        public MusicListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
