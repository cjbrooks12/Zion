package com.caseyjbrooks.ziontestapp.features.featureone;

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
import com.caseyjbrooks.ziontestapp.R;


public class FeatureOneFragment extends FragmentBase {

    public static FragmentBase newInstance(Bundle args) {
        FeatureOneFragment fragment = new FeatureOneFragment();
        fragment.setArguments(args);

        return fragment;
    }

// Data Mambers
//--------------------------------------------------------------------------------------------------

    @NonNull
    @Override
    public Class<? extends FragmentConfiguration> getFragmentConfigurationClass() {
        return FragmentOneFragmentConfiguration.class;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        instanceConfiguration = new FragmentOneFragmentConfiguration(getContext());
        if(getArguments() != null) {
            instanceConfiguration.setTitle("Feature One");
        }
    }

    public static class FragmentOneFragmentConfiguration extends FragmentConfiguration {
        public FragmentOneFragmentConfiguration(Context context) {
            super(context);
            setUsesFab(true);
            setFabIconId(R.drawable.ic_settings);
        }

        @NonNull
        @Override
        public Class<? extends FeatureConfiguration> getFeatureConfigurationClass() {
            return FeatureOneConfiguration.class;
        }

        @NonNull
        @Override
        public Class<? extends FragmentBase> getFragmentClass() {
            return FeatureOneFragment.class;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView view = new TextView(getActivityBase());
        view.setText("Feature One");
        return view;
    }
}
