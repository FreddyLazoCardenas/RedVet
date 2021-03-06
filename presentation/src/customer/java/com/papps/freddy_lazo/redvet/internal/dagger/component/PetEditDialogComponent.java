package com.papps.freddy_lazo.redvet.internal.dagger.component;


import com.papps.freddy_lazo.redvet.internal.dagger.PerActivity;
import com.papps.freddy_lazo.redvet.internal.dagger.PerFragment;
import com.papps.freddy_lazo.redvet.internal.dagger.module.ActivityModule;
import com.papps.freddy_lazo.redvet.view.activity.AppointmentActivity;
import com.papps.freddy_lazo.redvet.view.dialogFragment.PetEditDialog;

import dagger.Component;

@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
    public interface PetEditDialogComponent {

    void inject(PetEditDialog petEditDialog );

}
