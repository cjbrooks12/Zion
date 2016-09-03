package com.caseyjbrooks.zion.app.dashboard;

import android.content.Context;

import com.caseyjbrooks.zion.R;
import com.caseyjbrooks.zion.app.activity.DrawerFeature;
import com.caseyjbrooks.zion.app.activity.FeatureConfiguration;
import com.caseyjbrooks.zion.app.fragment.FragmentConfiguration;

public class DashboardFeatureConfiguration extends FeatureConfiguration {
    public DashboardFeatureConfiguration(Context context) {
        super(context);
    }

    @Override
    public DrawerFeature getDrawerFeature() {
        return new DrawerFeature(DashboardFeatureConfiguration.class, "Dashboard", R.drawable.ic_home);
    }

    @Override
    public Class<? extends FragmentConfiguration> getFragmentConfigurationClass() {
        return DashboardFragment.DashboardFragmentConfiguration.class;
    }
}
