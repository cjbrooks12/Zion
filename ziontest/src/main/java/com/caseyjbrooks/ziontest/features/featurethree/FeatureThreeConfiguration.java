package com.caseyjbrooks.ziontest.features.featurethree;

import android.content.Context;
import android.graphics.Color;

import com.caseyjbrooks.zion.app.activity.DrawerFeature;
import com.caseyjbrooks.zion.app.activity.FeatureConfiguration;
import com.caseyjbrooks.zion.app.fragment.FragmentConfiguration;
import com.caseyjbrooks.ziontest.R;

import java.util.ArrayList;

public class FeatureThreeConfiguration extends FeatureConfiguration {

    public FeatureThreeConfiguration(Context context) {
        super(context);
    }

    @Override
    public DrawerFeature getDrawerFeature() {
        DrawerFeature feature = new DrawerFeature(
                FeatureThreeConfiguration.class,
                "Feature Three",
                R.drawable.ic_discover);

        ArrayList<DrawerFeature> children = new ArrayList<>();

        children.add(new DrawerFeature(
                FeatureThreeConfiguration.class,
                "Feature Three",
                R.drawable.ic_chevron_up,
                1,
                Color.parseColor("#FFEB3B")));

        children.add(new DrawerFeature(
                FeatureThreeConfiguration.class,
                "Feature Three",
                R.drawable.ic_chevron_right,
                2,
                Color.parseColor("#FFC107")));

        children.add(new DrawerFeature(
                FeatureThreeConfiguration.class,
                "Feature Three",
                R.drawable.ic_chevron_down,
                3,
                Color.parseColor("#FF9800")));

        children.add(new DrawerFeature(
                FeatureThreeConfiguration.class,
                "Feature Three",
                R.drawable.ic_chevron_left,
                4,
                Color.parseColor("#FF5722")));

        feature.setChildren(children);

        return feature;
    }

    @Override
    public Class<? extends FragmentConfiguration> getFragmentConfigurationClass() {
        return FeatureThreeFragment.FeatureThreeFragmentConfiguraion.class;
    }
}
