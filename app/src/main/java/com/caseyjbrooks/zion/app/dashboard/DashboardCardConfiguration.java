package com.caseyjbrooks.zion.app.dashboard;

import android.support.annotation.NonNull;

import com.caseyjbrooks.zion.app.activity.FeatureConfiguration;

public abstract class DashboardCardConfiguration {
    public abstract @NonNull Class<? extends FeatureConfiguration> getFeatureConfigurationClass();
    public abstract @NonNull Class<? extends DashboardCardBase> getDashboardCardClass();
}
