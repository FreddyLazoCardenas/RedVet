package com.papps.freddy_lazo.redvet.internal.dagger.component;

import com.papps.freddy_lazo.redvet.internal.dagger.PerFragment;
import com.papps.freddy_lazo.redvet.view.fragment.NewsFragment;
import com.papps.freddy_lazo.redvet.view.fragment.RegisterFragment;

import dagger.Component;

@PerFragment
@Component(dependencies = ApplicationComponent.class)
public interface NewsFragmentComponent {

    void inject(NewsFragment fragment);
}