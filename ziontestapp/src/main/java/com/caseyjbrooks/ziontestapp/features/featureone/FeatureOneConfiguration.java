package com.caseyjbrooks.ziontestapp.features.featureone;

import android.content.Context;

import com.caseyjbrooks.zion.app.activity.DrawerFeature;
import com.caseyjbrooks.zion.app.activity.FeatureConfiguration;
import com.caseyjbrooks.zion.app.fragment.FragmentConfiguration;
import com.caseyjbrooks.ziontestapp.R;

public class FeatureOneConfiguration extends FeatureConfiguration {

    public FeatureOneConfiguration(Context context) {
        super(context);
    }

    @Override
    public DrawerFeature getDrawerFeature() {
        DrawerFeature feature = new DrawerFeature(FeatureOneConfiguration.class, "Feature One", R.drawable.ic_book_open);
        return feature;
    }

    @Override
    public Class<? extends FragmentConfiguration> getFragmentConfigurationClass() {
        return FeatureOneFragment.FragmentOneFragmentConfiguration.class;
    }
}
