package com.caseyjbrooks.ziontestapp;

import com.caseyjbrooks.zion.app.activity.ActivityBase;
import com.caseyjbrooks.zion.app.activity.FeatureProvider;
import com.caseyjbrooks.ziontestapp.features.featureone.FeatureOneConfiguration;
import com.caseyjbrooks.ziontestapp.features.featurethree.FeatureThreeConfiguration;
import com.caseyjbrooks.ziontestapp.features.featuretwo.FeatureTwoConfiguration;

public class MainActivity extends ActivityBase {
    @Override
    protected void initializeFeatures() {
        FeatureProvider provider = FeatureProvider.getInstance(this);

        provider.addFeature(new FeatureOneConfiguration(this));
        provider.addFeature(new FeatureTwoConfiguration(this));
        provider.addFeature(new FeatureThreeConfiguration(this));
    }
}
