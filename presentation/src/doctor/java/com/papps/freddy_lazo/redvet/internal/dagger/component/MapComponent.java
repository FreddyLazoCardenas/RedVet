package com.papps.freddy_lazo.redvet.internal.dagger.component;


import com.papps.freddy_lazo.redvet.internal.dagger.PerActivity;
import com.papps.freddy_lazo.redvet.internal.dagger.module.ActivityModule;
import com.papps.freddy_lazo.redvet.view.activity.MapActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface MapComponent {

    void inject(MapActivity activity);

}