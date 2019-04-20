package com.papps.freddy_lazo.redvet.internal.dagger.component;

import com.papps.freddy_lazo.redvet.internal.dagger.PerFragment;
import com.papps.freddy_lazo.redvet.view.dialogFragment.DoctorDialog;
import com.papps.freddy_lazo.redvet.view.dialogFragment.DoctorNotificationFinishedDialog;

import dagger.Component;

@PerFragment
@Component(dependencies = ApplicationComponent.class)
public interface DoctorNotificationFinishedDialogFragmentComponent {

    void inject(DoctorNotificationFinishedDialog fragment);
}
