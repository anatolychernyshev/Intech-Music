package com.intech.labs.intechmusic.di.module;

import android.content.Context;

import com.intech.labs.intechmusic.data.MusicApiRepositoryImpl;
import com.intech.labs.intechmusic.data.MusicRepository;
import com.intech.labs.intechmusic.retrofit.MusicService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.intech.labs.intechmusic.Constants.BASE_API_URL;

/**
 * Created by Anatoly Chernyshev on 11.04.2018.
 */

@Module
public class AppModule {

    private Context mContext;

    public AppModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(BASE_API_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    MusicService provideMusicService(Retrofit retrofit){
        return retrofit.create(MusicService.class);
    }

    @Provides
    @Singleton
    MusicRepository provideMusicRepository(MusicService musicService){
        return new MusicApiRepositoryImpl(musicService);
    }

}
