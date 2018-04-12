package com.intech.labs.intechmusic.ui.player;

import com.intech.labs.intechmusic.ui.base.BaseView;

/**
 * Created by Anatoly Chernyshev on 12.04.2018.
 */
public interface IPlayerActivity extends BaseView {
    void initializePlayer(String previewUrl);

    void initSongFields(String artistName, String trackName, String artworkUrl100);
}
