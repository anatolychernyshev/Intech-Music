package com.intech.labs.intechmusic.data;

import com.intech.labs.intechmusic.retrofit.model.GetSongListResponse;
import com.intech.labs.intechmusic.retrofit.model.Song;
import com.intech.labs.intechmusic.retrofit.MusicService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by Anatoly Chernyshev on 11.04.2018.
 */
public class MusicApiRepositoryImpl implements MusicRepository {

    private MusicService mMusicService;
    private List<Song> mSongList;

    @Inject
    public MusicApiRepositoryImpl(MusicService musicService) {
        mMusicService = musicService;
    }

    @Override
    public Single<GetSongListResponse> getSongList(String query) {
        return mMusicService.getSongList(query);
    }

    @Override
    public List<Song> getSongList() {
        return mSongList;
    }

    @Override
    public void setSongList(List<Song> songList) {
        mSongList = songList;
    }
}
