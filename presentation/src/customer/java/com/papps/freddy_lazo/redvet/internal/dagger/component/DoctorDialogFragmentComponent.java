package com.papps.freddy_lazo.redvet.internal.dagger.component;

import com.papps.freddy_lazo.redvet.internal.dagger.PerFragment;
import com.papps.freddy_lazo.redvet.view.dialogFragment.DoctorDialog;

import dagger.Component;

@PerFragment
@Component(dependencies = ApplicationComponent.class)
public interface DoctorDialogFragmentComponent {

    void inject(DoctorDialog fragment);
}
