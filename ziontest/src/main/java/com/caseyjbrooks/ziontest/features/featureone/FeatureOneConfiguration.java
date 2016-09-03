package com.caseyjbrooks.ziontest.features.featureone;

import android.content.Context;

import com.caseyjbrooks.zion.app.activity.DrawerFeature;
import com.caseyjbrooks.zion.app.activity.FeatureConfiguration;
import com.caseyjbrooks.zion.app.fragment.FragmentConfiguration;
import com.caseyjbrooks.ziontest.R;

public class FeatureOneConfiguration extends FeatureConfiguration {

    public FeatureOneConfiguration(Context context) {
        super(context);
    }

    @Override
    public DrawerFeature getDrawerFeature() {
        DrawerFeature feature = new DrawerFeature(FeatureOneConfiguration.class, "Feature One", R.drawable.ic_chevron_down);
        return feature;
    }

    @Override
    public Class<? extends FragmentConfiguration> getFragmentConfigurationClass() {
        return FeatureOneFragment.FeatureOneFragmentConfiguraion.class;
    }
}
