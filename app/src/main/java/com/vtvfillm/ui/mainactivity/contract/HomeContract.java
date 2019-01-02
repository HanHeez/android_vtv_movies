package com.vtvfillm.ui.mainactivity.contract;

import com.vtvfillm.base.BaseContract;
import com.vtvfillm.model.Category;

import java.util.List;

public interface HomeContract {

    interface View extends BaseContract.BaseView {
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
    }
}
