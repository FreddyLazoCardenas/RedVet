package com.papps.freddy_lazo.redvet.internal.dagger.component;


import com.papps.freddy_lazo.redvet.internal.dagger.PerActivity;
import com.papps.freddy_lazo.redvet.internal.dagger.module.ActivityModule;
import com.papps.freddy_lazo.redvet.view.activity.CancelAppointmentActivity;
import com.papps.freddy_lazo.redvet.view.activity.DiagnoseAppointmentActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface DiagnoseAppointmentComponent {

    void inject(DiagnoseAppointmentActivity activity);

}
