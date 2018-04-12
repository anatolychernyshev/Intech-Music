package com.intech.labs.intechmusic.di.module;

import android.content.Context;
import android.net.Uri;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.intech.labs.intechmusic.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import static com.intech.labs.intechmusic.Constants.ITUNES_PREVIEW_HEADER;

/**
 * Created by Anatoly Chernyshev on 12.04.2018.
 */

@Module
public class PlayerActivityModule {

    private Context mContext;

    public PlayerActivityModule(Context context) {
        mContext = context;
    }

    @Provides
    @ActivityScope
    Context provideContext(){
        return mContext;
    }

    @Provides
    @ActivityScope
    BandwidthMeter provideBandwidthMeter(){
        return new DefaultBandwidthMeter();
    }

    @Provides
    @ActivityScope
    ExtractorsFactory provideExtractorsFactory(){
        return new DefaultExtractorsFactory();
    }

    @Provides
    @ActivityScope
    DefaultBandwidthMeter provideDefaultBandwidthMeter(){
        return new DefaultBandwidthMeter();
    }

    @Provides
    @ActivityScope
    TrackSelection.Factory provideTrackSelectionFactory(BandwidthMeter bandwidthMeter){
        return new AdaptiveTrackSelection.Factory(bandwidthMeter);
    }

    @Provides
    @ActivityScope
    TrackSelector provideTrackSelector(TrackSelection.Factory trackSelectionFactory){
        return new DefaultTrackSelector(trackSelectionFactory);
    }

    @Provides
    @ActivityScope
    DataSource.Factory provideDataSourceFactory(Context context, DefaultBandwidthMeter defaultBandwidthMeter){
        return new DefaultDataSourceFactory(context, ITUNES_PREVIEW_HEADER, defaultBandwidthMeter);
    }

    @Provides
    @ActivityScope
    SimpleExoPlayer provideSimpleExoPlayer(Context context, TrackSelector trackSelector){
        return ExoPlayerFactory.newSimpleInstance(context, trackSelector);
    }

}
