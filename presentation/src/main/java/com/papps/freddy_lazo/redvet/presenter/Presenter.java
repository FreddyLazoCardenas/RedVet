package com.papps.freddy_lazo.redvet.presenter;

import com.papps.freddy_lazo.redvet.interfaces.BaseView;

public interface Presenter<T extends BaseView> {

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onResume() method.
     */
    void resume();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onPause() method.
     */
    void pause();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onDestroy() method.
     */
    void destroy();

    /**
     * Setter of the view.
     */
    void setView(T view);

}
