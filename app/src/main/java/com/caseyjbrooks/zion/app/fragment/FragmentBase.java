package com.caseyjbrooks.zion.app.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;

import com.caseyjbrooks.zion.app.activity.ActivityBase;

public abstract class FragmentBase extends Fragment {

    protected FragmentConfiguration instanceConfiguration;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

        if(getInstanceConfiguration().getMenuResourceId() != 0) {
            setHasOptionsMenu(true);
        }
        else {
            setHasOptionsMenu(false);
        }
    }

    public ActivityBase getActivityBase() {
        return (ActivityBase) super.getActivity();
    }

    public FragmentConfiguration getInstanceConfiguration() {
        return instanceConfiguration;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        if(getInstanceConfiguration().getMenuResourceId() != 0) {
            inflater.inflate(getInstanceConfiguration().getMenuResourceId(), menu);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(!(context instanceof ActivityBase)) {
            throw new ClassCastException("Parent context must be an instance of ActivityBase");
        }
    }

    public abstract @NonNull Class<? extends FragmentConfiguration> getFragmentConfigurationClass();

    public boolean onBackButtonPressed() {
        return false;
    }

    public boolean onBackArrowPressed() {
        return false;
    }

    public boolean onFABPressed() {
        return false;
    }

    public boolean onNetworkConnected() {
        return false;
    }

    public boolean onNetworkDisconnected() {
        return false;
    }
}
