package com.papps.freddy_lazo.redvet.internal.dagger.component;


import com.papps.freddy_lazo.redvet.internal.dagger.PerFragment;
import com.papps.freddy_lazo.redvet.internal.dagger.module.ActivityModule;
import com.papps.freddy_lazo.redvet.view.dialogFragment.ConfirmedAppointmentDialog;
import com.papps.freddy_lazo.redvet.view.dialogFragment.FinishedAppointmentDialog;

import dagger.Component;

@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface FinishedAppointmentDialogComponent {

    void inject(FinishedAppointmentDialog dialog);
}
