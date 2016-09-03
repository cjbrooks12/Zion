package com.caseyjbrooks.ziontest.features.featuretwo;

import android.content.Context;

import com.caseyjbrooks.zion.app.activity.DrawerFeature;
import com.caseyjbrooks.zion.app.activity.FeatureConfiguration;
import com.caseyjbrooks.zion.app.fragment.FragmentConfiguration;
import com.caseyjbrooks.ziontest.R;

public class FeatureTwoConfiguration extends FeatureConfiguration {

    public FeatureTwoConfiguration(Context context) {
        super(context);
    }

    @Override
    public DrawerFeature getDrawerFeature() {
        DrawerFeature feature = new DrawerFeature(
                FeatureTwoConfiguration.class,
                "Feature Two",
                R.drawable.ic_chevron_right);
        return feature;
    }

    @Override
    public Class<? extends FragmentConfiguration> getFragmentConfigurationClass() {
        return FeatureTwoFragment.FeatureTwoFragmentConfiguraion.class;
    }
}
