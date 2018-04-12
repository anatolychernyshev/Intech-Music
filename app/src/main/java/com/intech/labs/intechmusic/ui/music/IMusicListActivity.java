package com.intech.labs.intechmusic.ui.music;

import com.intech.labs.intechmusic.retrofit.model.Song;
import com.intech.labs.intechmusic.ui.ILoading;
import com.intech.labs.intechmusic.ui.base.BaseView;

import java.util.List;

/**
 * Created by Anatoly Chernyshev on 11.04.2018.
 */
public interface IMusicListActivity extends BaseView, ILoading {

    void setupRecyclerView();

    void updateList(List<Song> songs);

    void showIncorrectLengthError();

    void openPlayerActivity(int position);
}
