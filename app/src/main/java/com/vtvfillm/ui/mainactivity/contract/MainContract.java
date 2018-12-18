package com.vtvfillm.ui.mainactivity.contract;

import com.vtvfillm.base.BaseContract;
import com.vtvfillm.model.Category;
import com.vtvfillm.model.CategoryDetail;

import java.util.List;

public interface MainContract {

    interface View extends BaseContract.BaseView {
        void showCategoryList(List<Category> categories);
        void showSubCategoryList(List<Category> subCategoryList);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getCategoryList();
        void getSubCategoryList(String category_id);
    }
}