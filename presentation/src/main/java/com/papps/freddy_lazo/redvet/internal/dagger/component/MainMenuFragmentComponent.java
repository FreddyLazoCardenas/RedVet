package com.papps.freddy_lazo.redvet.internal.dagger.component;

import com.papps.freddy_lazo.redvet.internal.dagger.module.PerFragment;
import com.papps.freddy_lazo.redvet.view.fragment.LoginFragment;
import com.papps.freddy_lazo.redvet.view.fragment.MainMenuFragment;

import dagger.Component;

@PerFragment
@Component(dependencies = ApplicationComponent.class)
public interface MainMenuFragmentComponent {

    void inject(MainMenuFragment fragment);
}
