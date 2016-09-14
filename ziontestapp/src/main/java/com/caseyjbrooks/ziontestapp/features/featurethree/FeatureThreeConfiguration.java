package com.caseyjbrooks.ziontestapp.features.featurethree;

import android.content.Context;

import com.caseyjbrooks.zion.app.activity.DrawerFeature;
import com.caseyjbrooks.zion.app.activity.FeatureConfiguration;
import com.caseyjbrooks.zion.app.fragment.FragmentConfiguration;
import com.caseyjbrooks.ziontestapp.R;

public class FeatureThreeConfiguration extends FeatureConfiguration {

    public FeatureThreeConfiguration(Context context) {
        super(context);
    }

    @Override
    public DrawerFeature getDrawerFeature() {
        DrawerFeature feature = new DrawerFeature(FeatureThreeConfiguration.class, "Feature Three", R.drawable.ic_book_open);
        return feature;
    }

    @Override
    public Class<? extends FragmentConfiguration> getFragmentConfigurationClass() {
        return FeatureThreeFragment.FragmentThreeFragmentConfiguration.class;
    }
}
