package com.caseyjbrooks.ziontest;

import android.os.Bundle;

import com.caseyjbrooks.zion.app.activity.ActivityBase;
import com.caseyjbrooks.zion.app.activity.FeatureProvider;
import com.caseyjbrooks.zion.app.dashboard.DashboardFeatureConfiguration;
import com.caseyjbrooks.ziontest.features.featureone.FeatureOneConfiguration;
import com.caseyjbrooks.ziontest.features.featurethree.FeatureThreeConfiguration;
import com.caseyjbrooks.ziontest.features.featuretwo.FeatureTwoConfiguration;

public class MainActivity extends ActivityBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initializeFeatures() {
        FeatureProvider provider = FeatureProvider.getInstance(this);

        provider.addFeature(new DashboardFeatureConfiguration(this));
        provider.addFeature(new FeatureOneConfiguration(this));
        provider.addFeature(new FeatureTwoConfiguration(this));
        provider.addFeature(new FeatureThreeConfiguration(this));
    }
}
