package com.caseyjbrooks.zion.app.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ProgressBar;

import com.caseyjbrooks.clog.Clog;
import com.caseyjbrooks.zion.R;
import com.caseyjbrooks.zion.app.AppSettings;
import com.caseyjbrooks.zion.app.ExpandableNavigationView;
import com.caseyjbrooks.zion.app.fragment.FragmentBase;
import com.caseyjbrooks.zion.app.fragment.FragmentConfiguration;
import com.caseyjbrooks.zion.util.Util;
import com.caseyjbrooks.zion.widget.SearchBox;

import java.lang.reflect.Method;
import java.util.Calendar;

public abstract class ActivityBase extends AppCompatActivity implements
        ExpandableNavigationView.OnExpandableNavigationItemSelectedListener,
        FragmentManager.OnBackStackChangedListener {

    public String TAG = getClass().getSimpleName();

    private CoordinatorLayout coordinatorLayout;
    private Toolbar toolbar;
    private ExpandableNavigationView navView;
    private SearchBox searchbox;

    AppBarLayout appBarLayout;

    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;

    private FragmentConfiguration selectedFragment;

    private ProgressBar progressBar;

    private boolean wasDisconnected = false;
    private NetworkConnectionReceiver connectionReceiver;
    private FloatingActionButton floatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        toolbar                 = (Toolbar)                     findViewById(R.id.toolbar);
        searchbox               = (SearchBox)                   findViewById(R.id.searchbox);
        coordinatorLayout       = (CoordinatorLayout)           findViewById(R.id.coordinatorLayout);
        navView                 = (ExpandableNavigationView)    findViewById(R.id.expandableNavigationView);
        appBarLayout            = (AppBarLayout)                findViewById(R.id.appBarLayout);
        progressBar             = (ProgressBar)                 findViewById(R.id.progress);
        drawer                  = (DrawerLayout)                findViewById(R.id.drawer_layout);
        floatingActionButton    = (FloatingActionButton)        findViewById(R.id.floatingActionButton);

        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        ) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    int statusBarColor = getWindow().getStatusBarColor();
                    int translucentstatusBarColor = Color.argb(
                            (int) (255 - (slideOffset*255)),
                            Color.red(statusBarColor),
                            Color.green(statusBarColor),
                            Color.blue(statusBarColor));

                    getWindow().setStatusBarColor(translucentstatusBarColor);
                }
            }
        };
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navView.setExpandableNavigationItemSelectedListener(this);
        getSupportFragmentManager().addOnBackStackChangedListener(this);

        Calendar time1 = Calendar.getInstance();
        Calendar time2 = Calendar.getInstance();

        FeatureProvider.getInstance(this).clearFeatures();
        initializeFeatures();
        Calendar time3 = Calendar.getInstance();

        setupNavigationView();
        Calendar time4 = Calendar.getInstance();

        selectDefaultFeature();
        Calendar time5 = Calendar.getInstance();

        long diff_1_2 = time2.getTimeInMillis() - time1.getTimeInMillis();
        long diff_2_3 = time3.getTimeInMillis() - time2.getTimeInMillis();
        long diff_3_4 = time4.getTimeInMillis() - time3.getTimeInMillis();
        long diff_4_5 = time5.getTimeInMillis() - time4.getTimeInMillis();

        Clog.i("setup activitybase", "Setup ABT [{{ $1 }}ms], initialize features [{{ $2 }}ms], setup navigation view [{{ $3 }}ms], select default feature [{{ $4 }}ms] ", diff_1_2, diff_2_3, diff_3_4, diff_4_5);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFABPressed();
            }
        });
    }


//Setup activity's theme and features
//--------------------------------------------------------------------------------------------------
    public void setTheme() {
        if(AppSettings.getAppTheme(this).equals("Light"))
            setTheme(R.style.ThemeBase_Light_NoActionBar);
        else
            setTheme(R.style.ThemeBase_Dark_NoActionBar);
    }

    protected abstract void initializeFeatures();

    private void setupNavigationView() {
        navView.setDrawerFeatures(FeatureProvider.getInstance(this).getDrawerFeatures());
    }

    private void selectDefaultFeature() {
        selectFragment(FeatureProvider.getInstance(this).getDefaultFeature());
    }

    public CoordinatorLayout getCoordinatorLayout() {
        return coordinatorLayout;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

//Select app features
//--------------------------------------------------------------------------------------------------

    @Override
    public void selectFragment(DrawerFeature feature) {
        FeatureConfiguration targetFeature = FeatureProvider.getInstance(this).findFeatureConfiguration(feature.getFeatureClass());
        if(targetFeature == null) {
            Clog.i("Target FeatureConfiguration is null");
            return;
        }

        FragmentConfiguration targetFragment = FeatureProvider.getInstance(this).findFragmentConfiguration(targetFeature.getFragmentConfigurationClass());
        if(targetFragment == null) {
            Clog.i("Target FragmentConfiguration is null");
            return;
        }

        Bundle args = new Bundle();
        args.putLong("id", feature.getId());
        args.putInt("color", feature.getColor());

        selectFragment(targetFragment, args);
    }

    public void selectFragment(FeatureConfiguration feature) {
        selectFragment(feature, new Bundle());
    }

    public void selectFragment(FeatureConfiguration feature, Bundle args) {
        if(feature != null && feature.getFragmentConfigurationClass() != null) {
            selectFragment(FeatureProvider.getInstance(this).findFragmentConfiguration(feature.getFragmentConfigurationClass()), args);
        }
    }

    public final void selectFragment(FragmentConfiguration feature) {
        selectFragment(feature, new Bundle());
    }

    public final void selectFragment(FragmentConfiguration feature, Bundle args) {
        drawer.closeDrawer(GravityCompat.START);
        Clog.i("activitybase", "Instantiating Fragment[{{$1}}]", feature.getFragmentClass());

        if(feature.equals(selectedFragment)) {
            Clog.i("activitybase", "Feature is already selected");
            return;
        }
        else {
            Clog.i("activitybase", "Feature is not already selected");
            selectedFragment = feature;
        }

        Fragment fragment;

        Class<? extends FragmentBase> fragmentClass = feature.getFragmentClass();
        try {
            Method method = fragmentClass.getMethod("newInstance", Bundle.class);
            fragment = (Fragment) method.invoke(null, args);
        }
        catch (Exception e) {
            e.printStackTrace();
            return;
        }

        if(fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment, TAG)
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void onBackStackChanged() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(TAG);
        if (fragment != null && fragment instanceof FragmentBase) {
            FragmentConfiguration fragmentConfiguration = ((FragmentBase) fragment).getInstanceConfiguration();

            if (fragmentConfiguration == null) {
                Clog.i("Attempted to instantiate a fragment with a null fragment configuration.");
                return;
            }

//            if(fragmentConfiguration.isTopLevel()) {
//                drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, GravityCompat.START);
//                toggle.setDrawerIndicatorEnabled(true);
//            }
//            else {
//                drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, GravityCompat.START);
//                toggle.setDrawerIndicatorEnabled(false);
//            }

//            if(searchbox.isRevealed()) {
//                searchbox.hideInstant(this);
//            }

            setupDecor(fragmentConfiguration);
        }
    }

    public void setupDecor(final FragmentConfiguration fragmentConfiguration) {
        if(fragmentConfiguration == null) {
            Clog.i("Attempted to setup decor with a null fragment configuration.");
            return;
        }

        int themeColor = fragmentConfiguration.getDecorColor();
        if(themeColor == 0) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = getTheme();
            theme.resolveAttribute(R.attr.colorPrimary, typedValue, true);
            themeColor = typedValue.data;
        }
        final int color = themeColor;

        //setup toolbar
        getToolbar().setBackgroundColor(color);
        getToolbar().setTitle(fragmentConfiguration.getTitle());
        getToolbar().setSubtitle(fragmentConfiguration.getSubtitle());

        //setup statusbar
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int statusBarColor = Util.lighten(color, 0.7f);
            getWindow().setStatusBarColor(statusBarColor);
        }

        //color progressbar
        progressBar.getIndeterminateDrawable().setColorFilter(new PorterDuffColorFilter(themeColor, PorterDuff.Mode.SRC_IN));
        progressBar.getProgressDrawable().setColorFilter(new PorterDuffColorFilter(themeColor, PorterDuff.Mode.SRC_IN));
        setActivityProgress(0);

        floatingActionButton.setVisibility(View.VISIBLE);
        floatingActionButton.hide(new FloatingActionButton.OnVisibilityChangedListener() {
            @Override
            public void onHidden(FloatingActionButton fab) {
                super.onHidden(fab);
                Clog.i("FAB hidden");

                if(fragmentConfiguration.usesFab()) {
                    floatingActionButton.setImageResource(fragmentConfiguration.getFabIconId());
                    floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(color));
                    floatingActionButton.setRippleColor(Util.lighten(color, 1.5f));
                    floatingActionButton.show();
                }
            }
        });

    }

    public void setActivityProgress(int progress) {
        if(progress == 0) {
            progressBar.setVisibility(View.GONE);
        }
        else {
            progressBar.setVisibility(View.VISIBLE);
        }

        if(progress < 0) {
            progressBar.setIndeterminate(true);
        }
        else {
            progressBar.setIndeterminate(false);
        }

        progressBar.setProgress(Math.min(progress, 100));
    }

    public int getActivityProgress() {
        return progressBar.getProgress();
    }

    public void openSearch(String hint, int menuResourceId, SearchBox.SearchBoxListener listener) {
        searchbox.setHint(hint);
        searchbox.setMenuResource(menuResourceId);
        searchbox.setSearchBoxListener(listener);

        searchbox.reveal(this);
    }

    public void closeSearch() {
        searchbox.hide(this);
    }

    public SearchBox getSearchbox() {
        return searchbox;
    }

    @Override
    protected void onResume() {
        super.onResume();
        connectionReceiver = new NetworkConnectionReceiver();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(connectionReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(connectionReceiver);
    }

    public void setDrawerEnabled(boolean drawerEnabled) {
        if(drawerEnabled) {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, GravityCompat.START);
            toggle.setDrawerIndicatorEnabled(true);

            DrawerArrowDrawable arrowDrawable = new DrawerArrowDrawable(this);
            arrowDrawable.setProgress(1.0f);
            arrowDrawable.setColor(Color.WHITE);
            toggle.setHomeAsUpIndicator(arrowDrawable);
            toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackArrowPressed();
                }
            });
        }
        else {
            drawer.closeDrawer(GravityCompat.START);
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, GravityCompat.START);
            toggle.setDrawerIndicatorEnabled(false);
        }
    }

    public class NetworkConnectionReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Fragment fragment = getSupportFragmentManager().findFragmentByTag("ActivityBase");

            if(fragment != null && fragment instanceof FragmentBase) {
                if(isNetworkAvailable()) {
                    ((FragmentBase) fragment).onNetworkConnected();
                }
                else {
                    ((FragmentBase) fragment).onNetworkDisconnected();
                }
            }

            if(isNetworkAvailable()) {
                if(wasDisconnected) {
                    onNetworkConnected();
                    wasDisconnected = false;
                }
            }
            else {
                onNetworkDisconnected();
                wasDisconnected = true;
            }
        }
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }

    void onNetworkConnected() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(TAG);
        if(fragment instanceof FragmentBase) {
            boolean isFinished = ((FragmentBase) fragment).onNetworkConnected();
            if(isFinished)
                return;
        }

        Snackbar.make(coordinatorLayout, "Network connectivity restored", Snackbar.LENGTH_LONG).show();
    }

    void onNetworkDisconnected() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(TAG);
        if(fragment instanceof FragmentBase) {
            boolean isFinished = ((FragmentBase) fragment).onNetworkDisconnected();
            if(isFinished)
                return;
        }

        final Snackbar snackbar = Snackbar.make(coordinatorLayout, "No Network Connection", Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("Connect", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
            }
        });
        snackbar.show();
    }

    @Override
    public void onBackPressed() {
        if(searchbox.isRevealed()) {
            if(searchbox.isOpen()) {
                searchbox.setIsOpen(false);
            }
            else {
                closeSearch();
            }
            return;
        }

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(TAG);
        if(fragment instanceof FragmentBase) {
            boolean isFinished = ((FragmentBase) fragment).onBackButtonPressed();
            if(isFinished) {
                return;
            }
            else {
                Snackbar.make(coordinatorLayout, "Back Button Pressed", Snackbar.LENGTH_LONG).show();
            }
        }

        super.onBackPressed();
    }

    void onBackArrowPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(TAG);
        if(fragment instanceof FragmentBase) {
            boolean isFinished = ((FragmentBase) fragment).onBackArrowPressed();
            if(isFinished) {
                return;
            }
            else {
                Snackbar.make(coordinatorLayout, "Back Arrow Pressed", Snackbar.LENGTH_LONG).show();
            }
        }

        getSupportFragmentManager().popBackStack();
    }

    void onFABPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(TAG);
        if(fragment instanceof FragmentBase) {
            boolean isFinished = ((FragmentBase) fragment).onFABPressed();
            if(isFinished) {
                return;
            }
            else {
                Snackbar.make(coordinatorLayout, "FAB Pressed", Snackbar.LENGTH_LONG).show();
            }
        }
    }
}
