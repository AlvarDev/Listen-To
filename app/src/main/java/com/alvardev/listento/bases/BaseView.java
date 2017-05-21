package com.alvardev.listento.bases;

/**
 * Created by alvardev on 21/05/17.
 * Base View to manage all views (Activities and fragments)
 */

public interface BaseView<T> {

    void setPresenter(T presenter);

}
