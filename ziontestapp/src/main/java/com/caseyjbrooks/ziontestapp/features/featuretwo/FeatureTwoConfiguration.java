package com.caseyjbrooks.ziontestapp.features.featuretwo;

import android.content.Context;

import com.caseyjbrooks.zion.app.activity.DrawerFeature;
import com.caseyjbrooks.zion.app.activity.FeatureConfiguration;
import com.caseyjbrooks.zion.app.fragment.FragmentConfiguration;
import com.caseyjbrooks.ziontestapp.R;

public class FeatureTwoConfiguration extends FeatureConfiguration {

    public FeatureTwoConfiguration(Context context) {
        super(context);
    }

    @Override
    public DrawerFeature getDrawerFeature() {
        DrawerFeature feature = new DrawerFeature(FeatureTwoConfiguration.class, "Feature Two", R.drawable.ic_book_open);
        return feature;
    }

    @Override
    public Class<? extends FragmentConfiguration> getFragmentConfigurationClass() {
        return FeatureTwoFragment.FragmentTwoFragmentConfiguration.class;
    }
}
