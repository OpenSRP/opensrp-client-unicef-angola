package org.smartregister.unicefangola.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.apache.commons.lang3.tuple.Triple;
import org.smartregister.AllConstants;
import org.smartregister.child.activity.BaseChildImmunizationActivity;
import org.smartregister.child.domain.RegisterClickables;
import org.smartregister.child.toolbar.LocationSwitcherToolbar;
import org.smartregister.child.util.Constants;
import org.smartregister.child.util.Utils;
import org.smartregister.commonregistry.CommonPersonObjectClient;
import org.smartregister.unicefangola.application.UnicefAngolaApplication;
import org.smartregister.unicefangola.util.AppUtils;

import java.util.Map;

public class ChildImmunizationActivity extends BaseChildImmunizationActivity {
    @Override
    protected void attachBaseContext(Context base) {
        // get language from prefs
        String lang = AppUtils.getLanguage(base.getApplicationContext());
        super.attachBaseContext(AppUtils.setAppLocale(base, lang));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        VaccineUtils.refreshImmunizationSchedules(childDetails.getCaseId());
        LocationSwitcherToolbar myToolbar = (LocationSwitcherToolbar) this.getToolbar();
        if (myToolbar != null) {
            myToolbar.setNavigationOnClickListener(v -> finish());
        }
    }

    @Override
    protected String constructChildName() {
        String firstName = Utils.getValue(this.childDetails.getColumnmaps(), "first_name", true);
        String lastName = Utils.getValue(this.childDetails.getColumnmaps(), "last_name", true);
        String middleName = Utils.getValue(this.childDetails.getColumnmaps(), "middle_name", true);
        if (middleName.isEmpty())
            return firstName + " " + lastName;
        return firstName + " " + middleName + " " + lastName;
    }

    @Override
    protected void goToRegisterPage() {
        Intent intent = new Intent(this, ChildRegisterActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();

    }

    @Override
    protected int getToolbarId() {
        return LocationSwitcherToolbar.TOOLBAR_ID;
    }

    @Override
    protected int getDrawerLayoutId() {
        return 0;
    }

    @Override
    public void launchDetailActivity(Context fromContext, CommonPersonObjectClient childDetails,
                                     RegisterClickables registerClickables) {

        Intent intent = new Intent(fromContext, ChildDetailTabbedActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.INTENT_KEY.LOCATION_ID,
                Utils.context().allSharedPreferences().getPreference(AllConstants.CURRENT_LOCATION_ID));
        bundle.putSerializable(Constants.INTENT_KEY.EXTRA_CHILD_DETAILS, childDetails);
        bundle.putSerializable(Constants.INTENT_KEY.BASE_ENTITY_ID, childDetails.getCaseId());
        bundle.putSerializable(Constants.INTENT_KEY.EXTRA_REGISTER_CLICKABLES, registerClickables);
        intent.putExtras(bundle);

        fromContext.startActivity(intent);
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    public boolean isLastModified() {
        UnicefAngolaApplication application = (UnicefAngolaApplication) getApplication();
        return application.isLastModified();
    }

    @Override
    public void setLastModified(boolean lastModified) {
        UnicefAngolaApplication application = (UnicefAngolaApplication) getApplication();
        if (lastModified != application.isLastModified()) {
            application.setLastModified(lastModified);
        }
    }

    @Override
    public void onClick(View view) {
        // Overridden
    }

    @Override
    public void onUniqueIdFetched(Triple<String, Map<String, String>, String> triple, String s) {
        // Overridden
    }

    @Override
    public void onNoUniqueId() {
        // Overridden
    }

    @Override
    public void onRegistrationSaved(boolean isEdit) {
        hideProgressDialog();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void updateScheduleDate() {
        super.updateScheduleDate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getServiceGroupCanvasLL().setVisibility(View.VISIBLE);
    }
}
