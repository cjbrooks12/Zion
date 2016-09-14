package com.caseyjbrooks.ziontestapp.features.featuretwo;

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


public class FeatureTwoFragment extends FragmentBase {

    public static FragmentBase newInstance(Bundle args) {
        FeatureTwoFragment fragment = new FeatureTwoFragment();
        fragment.setArguments(args);

        return fragment;
    }

// Data Mambers
//--------------------------------------------------------------------------------------------------

    @NonNull
    @Override
    public Class<? extends FragmentConfiguration> getFragmentConfigurationClass() {
        return FragmentTwoFragmentConfiguration.class;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        instanceConfiguration = new FragmentTwoFragmentConfiguration(getContext());
        if(getArguments() != null) {
            instanceConfiguration.setTitle("Feature Two");
        }
    }

    public static class FragmentTwoFragmentConfiguration extends FragmentConfiguration {
        public FragmentTwoFragmentConfiguration(Context context) {
            super(context);
            setUsesFab(true);
            setFabIconId(R.drawable.ic_search);
        }

        @NonNull
        @Override
        public Class<? extends FeatureConfiguration> getFeatureConfigurationClass() {
            return FeatureTwoConfiguration.class;
        }

        @NonNull
        @Override
        public Class<? extends FragmentBase> getFragmentClass() {
            return FeatureTwoFragment.class;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView view = new TextView(getActivityBase());
        view.setText("Feature Two");
        return view;
    }
}
