package org.smartregister.unicefangola.fragment;

import android.os.Bundle;

import com.vijay.jsonwizard.constants.JsonFormConstants;
import com.vijay.jsonwizard.fragments.JsonFormFragment;

import org.jetbrains.annotations.NotNull;
import org.smartregister.child.fragment.ChildFormFragment;
import org.smartregister.child.presenter.ChildFormFragmentPresenter;
import org.smartregister.unicefangola.interactor.ChildFormInteractor;
import org.smartregister.unicefangola.presenter.AppChildFormFragmentPresenter;
import org.smartregister.unicefangola.util.AppConstants;

import java.lang.ref.WeakReference;
import java.util.HashMap;

public class AppChildFormFragment extends ChildFormFragment {

    private OnReactionVaccineSelected OnReactionVaccineSelected;

    public static AppChildFormFragment getFormFragment(String stepName) {
        AppChildFormFragment jsonFormFragment = new AppChildFormFragment();
        Bundle bundle = new Bundle();
        bundle.putString(JsonFormConstants.JSON_FORM_KEY.STEPNAME, stepName);
        jsonFormFragment.setArguments(bundle);
        return jsonFormFragment;
    }

    public OnReactionVaccineSelected getOnReactionVaccineSelected() {
        return OnReactionVaccineSelected;
    }

    public void setOnReactionVaccineSelected(OnReactionVaccineSelected onReactionVaccineSelected) {
        this.OnReactionVaccineSelected = onReactionVaccineSelected;
    }

    @Override
    protected ChildFormFragmentPresenter createPresenter() {
        WeakReference<JsonFormFragment> weakReference = new WeakReference<>(this);
        return new AppChildFormFragmentPresenter((JsonFormFragment) weakReference.get(),
                ChildFormInteractor.getInstance());
    }

    public interface OnReactionVaccineSelected {
        void updateDatePicker(String date);
    }

    @Override
    public void onDestroy() {
        setOnReactionVaccineSelected(null);
        super.onDestroy();
    }

    @Override
    protected @NotNull HashMap<String, String> getKeyAliasMap() {
        return new HashMap<String, String>() {
            {
                put("mother_guardian_last_name", AppConstants.KeyConstants.LAST_NAME);
                put("mother_guardian_first_name", AppConstants.KeyConstants.FIRST_NAME);
                put("mother_guardian_date_birth", AppConstants.KeyConstants.DOB);
            }
        };
    }
}
