package com.caseyjbrooks.ziontest.features.featureone;

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


public class FeatureOneFragment extends FragmentBase {

    public static FragmentBase newInstance(Bundle args) {
        FeatureOneFragment fragment = new FeatureOneFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @NonNull
    @Override
    public Class<? extends FragmentConfiguration> getFragmentConfigurationClass() {
        return FeatureOneFragmentConfiguraion.class;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        instanceConfiguration = new FeatureOneFragmentConfiguraion(getContext());
        if(getArguments() != null) {
            instanceConfiguration.setDecorColor(getArguments().getInt("color"));
            instanceConfiguration.setTitle("Feature One");
            instanceConfiguration.setSubtitle(null);
        }
    }

    public static class FeatureOneFragmentConfiguraion extends FragmentConfiguration {
        public FeatureOneFragmentConfiguraion(Context context) {
            super(context);
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
        TextView view = new TextView(getContext());
        view.setText("Feature One");

        return view;
    }
}
