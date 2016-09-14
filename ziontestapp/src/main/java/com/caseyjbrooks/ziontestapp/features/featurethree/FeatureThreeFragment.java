package com.caseyjbrooks.ziontestapp.features.featurethree;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.caseyjbrooks.zion.app.activity.FeatureConfiguration;
import com.caseyjbrooks.zion.app.fragment.FragmentBase;
import com.caseyjbrooks.zion.app.fragment.FragmentConfiguration;


public class FeatureThreeFragment extends FragmentBase {

    public static FragmentBase newInstance(Bundle args) {
        FeatureThreeFragment fragment = new FeatureThreeFragment();
        fragment.setArguments(args);

        return fragment;
    }

// Data Mambers
//--------------------------------------------------------------------------------------------------

    @NonNull
    @Override
    public Class<? extends FragmentConfiguration> getFragmentConfigurationClass() {
        return FragmentThreeFragmentConfiguration.class;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        instanceConfiguration = new FragmentThreeFragmentConfiguration(getContext());
        if(getArguments() != null) {
            instanceConfiguration.setTitle("Feature Three");
        }
    }

    public static class FragmentThreeFragmentConfiguration extends FragmentConfiguration {
        public FragmentThreeFragmentConfiguration(Context context) {
            super(context);
            setUsesFab(false);
        }

        @NonNull
        @Override
        public Class<? extends FeatureConfiguration> getFeatureConfigurationClass() {
            return FeatureThreeConfiguration.class;
        }

        @NonNull
        @Override
        public Class<? extends FragmentBase> getFragmentClass() {
            return FeatureThreeFragment.class;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView view = new TextView(getActivityBase());
        view.setText("Feature Three");
        return view;
    }
}
