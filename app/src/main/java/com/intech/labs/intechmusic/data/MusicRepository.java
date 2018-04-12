package com.intech.labs.intechmusic.data;

import com.intech.labs.intechmusic.retrofit.model.GetSongListResponse;
import com.intech.labs.intechmusic.retrofit.model.Song;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Anatoly Chernyshev on 11.04.2018.
 */
public interface MusicRepository {

    Single<GetSongListResponse> getSongList(String query);

    List<Song> getSongList();
    void setSongList(List<Song> songList);

}
