package com.grishberg.viper_rest_android.presentation.injection;

import com.grishberg.viper_rest_android.data.ApiService;
import com.grishberg.viper_rest_android.presentation.main.fragments.AuthFragment;
import com.grishberg.viper_rest_android.presentation.main.fragments.ShopsFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by grishberg on 09.06.16.
 */
@Singleton
@Component(modules = {DomainModule.class, DataModule.class, AuthStorageModule.class})
public interface MainActivityComponent {
    void inject(ShopsFragment chatsFragment);
    void inject(AuthFragment authFragment);
    //void inject(ApiService apiService);

    //void inject(MessagesFragment messagesFragment);

    //void inject(ContactsFragment contactsFragment);
}
