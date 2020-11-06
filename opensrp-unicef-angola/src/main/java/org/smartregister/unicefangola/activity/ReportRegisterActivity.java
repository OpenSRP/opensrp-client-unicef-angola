package org.smartregister.unicefangola.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.core.view.GravityCompat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.commons.lang3.tuple.Triple;
import org.smartregister.child.activity.BaseActivity;
import org.smartregister.child.toolbar.LocationSwitcherToolbar;
import org.smartregister.domain.FetchStatus;
import org.smartregister.unicefangola.R;
import org.smartregister.unicefangola.model.ReportGroupingModel;
import org.smartregister.unicefangola.util.AppConstants;
import org.smartregister.unicefangola.util.AppUtils;
import org.smartregister.unicefangola.view.NavigationMenu;

import java.util.ArrayList;
import java.util.Map;

public class ReportRegisterActivity extends BaseActivity {

    private ImageView reportSyncBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListView listView = findViewById(R.id.lv_reportRegister_groupings);
        TextView titleTv = findViewById(R.id.title);

//        if (titleTv != null) {
//            titleTv.setText(R.string.dhis2_reports);
//        }

        reportSyncBtn = findViewById(R.id.report_sync_btn);
        reportSyncBtn.setOnClickListener(v -> AppUtils.startReportJob(getApplicationContext()));

        final ArrayList<ReportGroupingModel.ReportGrouping> reportGroupings = getReportGroupings();
        listView.setAdapter(new ArrayAdapter<>(this, R.layout.report_grouping_list_item, reportGroupings));
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(ReportRegisterActivity.this, HIA2ReportsActivity.class);
            intent.putExtra(AppConstants.IntentKey.REPORT_GROUPING, reportGroupings.get(position).getGrouping());
            startActivity(intent);
        });

        if (AppUtils.getSyncStatus()) {
            reportSyncBtn.setVisibility(View.VISIBLE);
        } else {
            reportSyncBtn.setVisibility(View.GONE);
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_report_register;
    }


    @Override
    protected int getDrawerLayoutId() {
        return R.id.drawer_layout;
    }

    @Override
    protected int getToolbarId() {
        return LocationSwitcherToolbar.TOOLBAR_ID;
    }

    @Override
    protected Class onBackActivity() {
        return null;
    }

    protected ArrayList<ReportGroupingModel.ReportGrouping> getReportGroupings() {
        return (new ReportGroupingModel(this)).getReportGroupings();
    }

    public void onClickReport(View view) {
        if (view.getId() == R.id.btn_back_to_home) {
            NavigationMenu navigationMenu = NavigationMenu.getInstance(this);
            if (navigationMenu != null) {
                navigationMenu.getDrawer().openDrawer(GravityCompat.START);
            }
        }
    }

    @Override
    public void onUniqueIdFetched(Triple<String, Map<String, String>, String> triple, String s) {
        //Overridden - not required
    }

    @Override
    public void onNoUniqueId() {
        //Overridden - not required
    }

    @Override
    public void onRegistrationSaved(boolean b) {
        //Overridden - not required
    }

    @Override
    public void onSyncInProgress(FetchStatus fetchStatus) {
        super.onSyncInProgress(fetchStatus);
        toggleReportSyncButton(fetchStatus);
    }

    void toggleReportSyncButton(FetchStatus fetchStatus) {
        if (reportSyncBtn != null) {
            reportSyncBtn.setVisibility(View.GONE);
        }
        AppUtils.updateSyncStatus(false);
    }

    @Override
    public void onSyncComplete(FetchStatus fetchStatus) {
        super.onSyncComplete(fetchStatus);
        toggleReportSyncButton(fetchStatus);

    }


    @Override
    public void onSyncStart() {
        super.onSyncStart();
        if (reportSyncBtn != null) {
            reportSyncBtn.setVisibility(View.GONE);
        }
        AppUtils.updateSyncStatus(false);

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (reportSyncBtn == null)
            return;

        if (AppUtils.getSyncStatus()) {
            reportSyncBtn.setVisibility(View.VISIBLE);
        } else {
            reportSyncBtn.setVisibility(View.GONE);
        }
    }
}
