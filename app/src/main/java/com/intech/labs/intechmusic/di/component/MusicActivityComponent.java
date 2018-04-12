package com.intech.labs.intechmusic.di.component;

import com.intech.labs.intechmusic.di.module.MusicActivityModule;
import com.intech.labs.intechmusic.di.scope.ActivityScope;
import com.intech.labs.intechmusic.ui.music.MusicListActivity;

import dagger.Subcomponent;

/**
 * Created by Anatoly Chernyshev on 11.04.2018.
 */

@ActivityScope
@Subcomponent(modules = MusicActivityModule.class)
public interface MusicActivityComponent {

    void inject(MusicListActivity musicListActivity);

}
