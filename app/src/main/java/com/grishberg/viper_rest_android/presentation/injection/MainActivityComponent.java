package com.grishberg.viper_rest_android.presentation.injection;

import com.grishberg.viper_rest_android.presentation.main.fragments.ShopsFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by grishberg on 09.06.16.
 */
@Singleton
@Component(modules = {DomainModule.class, DataModule.class})
public interface MainActivityComponent {
    void inject(ShopsFragment chatsFragment);

    //void inject(MessagesFragment messagesFragment);

    //void inject(ContactsFragment contactsFragment);
}
