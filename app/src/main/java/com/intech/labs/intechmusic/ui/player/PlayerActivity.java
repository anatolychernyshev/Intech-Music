package com.intech.labs.intechmusic.ui.player;

import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ads.AdsMediaSource;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.intech.labs.intechmusic.R;
import com.intech.labs.intechmusic.app.App;
import com.intech.labs.intechmusic.di.module.PlayerActivityModule;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.intech.labs.intechmusic.Constants.SONG_POSITION;

public class PlayerActivity extends AppCompatActivity implements IPlayerActivity {

    @BindView(R.id.player_song_art) ImageView mSongArt;
    @BindView(R.id.player_artist_name) TextView mArtistName;
    @BindView(R.id.player_song_name) TextView mSongName;
    @BindView(R.id.exo_player_view) SimpleExoPlayerView mExoPlayerView;

    @Inject PlayerPresenter<IPlayerActivity> mPlayerPresenter;
    @Inject SimpleExoPlayer mExoPlayer;
    @Inject ExtractorsFactory mExtractorsFactory;
    @Inject DataSource.Factory mDataSourceFactory;

    private MediaSource mMediaSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        init();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        int position = getIntent().getIntExtra(SONG_POSITION, 0);

        mPlayerPresenter.onCreate(position);

    }

    private void init() {
        ButterKnife.bind(this);
        App.getComponent().playerActivityComponent(new PlayerActivityModule(this)).inject(this);
        mPlayerPresenter.onAttach(this);
    }

    @Override
    public void initializePlayer(String previewUrl) {

        mMediaSource = new ExtractorMediaSource(Uri.parse(previewUrl), mDataSourceFactory, mExtractorsFactory,
                null, null);

        mExoPlayerView.setPlayer(mExoPlayer);
        mExoPlayer.setPlayWhenReady(true);
        mExoPlayer.prepare(mMediaSource);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                stopPlayer();
        }
        return true;
    }

    @Override
    public void initSongFields(String artistName, String trackName, String artworkUrl100) {
        mArtistName.setText(artistName);
        mSongName.setText(trackName);

        Picasso.with(this)
                .load(artworkUrl100)
                .centerInside()
                .resize(200, 200)
                .into(mSongArt);

    }

    private void stopPlayer(){
        mExoPlayer.setPlayWhenReady(false);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        stopPlayer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPlayerPresenter.onDetach();
        stopPlayer();
    }
}
