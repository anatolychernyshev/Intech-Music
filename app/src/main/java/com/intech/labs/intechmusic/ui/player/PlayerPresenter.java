package com.intech.labs.intechmusic.ui.player;

import android.util.Log;

import com.intech.labs.intechmusic.data.MusicRepository;
import com.intech.labs.intechmusic.retrofit.model.Song;
import com.intech.labs.intechmusic.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by Anatoly Chernyshev on 12.04.2018.
 */
public class PlayerPresenter<V extends IPlayerActivity> extends BasePresenter<V> {

    private static final String TAG = PlayerPresenter.class.getSimpleName();

    private MusicRepository mMusicRepository;

    @Inject
    public PlayerPresenter(MusicRepository musicRepository) {
        mMusicRepository = musicRepository;
    }


    public void onCreate(int position) {
        final Song item = mMusicRepository.getSongList().get(position);

        Log.i(TAG, item.getKind());

        mView.initSongFields(item.getArtistName(), item.getTrackName(), item.getArtworkUrl100());
        mView.initializePlayer(item.getPreviewUrl());
    }
}
