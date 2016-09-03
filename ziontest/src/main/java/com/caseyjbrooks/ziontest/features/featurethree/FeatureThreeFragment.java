package com.caseyjbrooks.ziontest.features.featurethree;

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

    @NonNull
    @Override
    public Class<? extends FragmentConfiguration> getFragmentConfigurationClass() {
        return FeatureThreeFragmentConfiguraion.class;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        instanceConfiguration = new FeatureThreeFragmentConfiguraion(getContext());
        if(getArguments() != null) {
            instanceConfiguration.setDecorColor(getArguments().getInt("color"));
            instanceConfiguration.setTitle("Feature Three");
            instanceConfiguration.setSubtitle(null);
        }
    }

    public static class FeatureThreeFragmentConfiguraion extends FragmentConfiguration {
        public FeatureThreeFragmentConfiguraion(Context context) {
            super(context);
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
        TextView view = new TextView(getContext());
        view.setText("Feature Three");

        return view;
    }
}
