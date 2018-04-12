package com.intech.labs.intechmusic.retrofit;

import com.intech.labs.intechmusic.retrofit.model.GetSongListResponse;
import com.intech.labs.intechmusic.retrofit.model.Song;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Anatoly Chernyshev on 11.04.2018.
 */
public interface MusicService {

    @GET("/search")
    Single<GetSongListResponse> getSongList(@Query("term") String searchQuery);

}
