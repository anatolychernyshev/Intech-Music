package com.intech.labs.intechmusic.di.component;

import com.intech.labs.intechmusic.di.module.PlayerActivityModule;
import com.intech.labs.intechmusic.di.scope.ActivityScope;
import com.intech.labs.intechmusic.ui.player.PlayerActivity;

import dagger.Subcomponent;

/**
 * Created by Anatoly Chernyshev on 12.04.2018.
 */

@ActivityScope
@Subcomponent(modules = PlayerActivityModule.class)
public interface PlayerActivityComponent {

    void inject(PlayerActivity playerActivity);

}
