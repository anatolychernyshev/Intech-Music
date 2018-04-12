package com.intech.labs.intechmusic.di.module;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import com.intech.labs.intechmusic.adapter.ItemClickListener;
import com.intech.labs.intechmusic.adapter.MusicListAdapter;
import com.intech.labs.intechmusic.data.MusicRepository;
import com.intech.labs.intechmusic.di.scope.ActivityScope;
import com.intech.labs.intechmusic.ui.music.IMusicListActivity;
import com.intech.labs.intechmusic.ui.music.MusicListPresenter;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Anatoly Chernyshev on 11.04.2018.
 */

@Module
public class MusicActivityModule {

    private Context mContext;
    private ItemClickListener mListener;

    public MusicActivityModule(Context context, ItemClickListener listener) {
        mContext = context;
        mListener = listener;
    }

    @Provides
    @ActivityScope
    ItemClickListener provideItemClickListener(){
        return mListener;
    }

    @Provides
    @ActivityScope
    Context provideContext(){
        return mContext;
    }

    @Provides
    @ActivityScope
    MusicListAdapter provideMusicListAdapter(Context context, ItemClickListener itemClickListener){
        return new MusicListAdapter(new ArrayList<>(), context, itemClickListener);
    }

    @Provides
    @ActivityScope
    LinearLayoutManager provideLinearLayoutManager(Context context){
        return new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
    }

}
