package com.papps.freddy_lazo.redvet.internal.dagger.module;

import android.app.Activity;

import com.papps.freddy_lazo.redvet.internal.dagger.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * A module to wrap the Activity state and expose it to the graph.
 */
@Module
public class ActivityModule {

    private final Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    /**
     * Expose the mActivity to dependents in the graph.
     */
    @Provides
    @PerActivity
    Activity activity() {
        return this.mActivity;
    }
}