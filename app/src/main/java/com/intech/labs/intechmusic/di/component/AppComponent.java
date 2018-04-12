package com.intech.labs.intechmusic.di.component;

import com.intech.labs.intechmusic.app.App;
import com.intech.labs.intechmusic.di.module.MusicActivityModule;
import com.intech.labs.intechmusic.di.module.AppModule;
import com.intech.labs.intechmusic.di.module.PlayerActivityModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Anatoly Chernyshev on 11.04.2018.
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    MusicActivityComponent musicActivityComponent(MusicActivityModule musicActivityModule);
    PlayerActivityComponent playerActivityComponent(PlayerActivityModule playerActivityModule);

    void inject(App app);

}
