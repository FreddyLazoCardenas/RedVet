package com.papps.freddy_lazo.redvet.internal.dagger.component;

import com.papps.freddy_lazo.redvet.internal.dagger.PerFragment;
import com.papps.freddy_lazo.redvet.view.fragment.ForgotPasswordFragment;
import com.papps.freddy_lazo.redvet.view.fragment.LoginFragment;

import dagger.Component;

@PerFragment
@Component(dependencies = ApplicationComponent.class)
public interface ForgotPasswordFragmentComponent {

    void inject(ForgotPasswordFragment fragment);
}
