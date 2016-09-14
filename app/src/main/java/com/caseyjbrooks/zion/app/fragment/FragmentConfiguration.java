package com.caseyjbrooks.zion.app.fragment;

import android.content.Context;
import android.support.annotation.NonNull;

import com.caseyjbrooks.zion.app.activity.FeatureConfiguration;

public abstract class FragmentConfiguration {
    private Context context;

    private int decorColor = 0;
    private long id = 0;
    private int icon = 0;
    private String title = "";
    private String subtitle = "";

    private int menuResourceId = 0;

    private boolean usesSearchbox = false;
    private String searchboxHint = "";
    private int searchboxMenuId = 0;

    private boolean usesFab = false;
    private int fabIconId = 0;

    private boolean shouldAddToBackstatck = true;
    private boolean isTopLevel = true;

    public FragmentConfiguration(Context context) {
        this.context = context.getApplicationContext();
    }

    public abstract @NonNull Class<? extends FeatureConfiguration> getFeatureConfigurationClass();
    public abstract @NonNull Class<? extends FragmentBase> getFragmentClass();

    public int getDecorColor() {
        return decorColor;
    }

    public void setDecorColor(int decorColor) {
        this.decorColor = decorColor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public int getMenuResourceId() {
        return menuResourceId;
    }

    public void setMenuResourceId(int menuResourceId) {
        this.menuResourceId = menuResourceId;
    }

    public boolean usesSearchbox() {
        return usesSearchbox;
    }

    public void setUsesSearchbox(boolean usesSearchbox) {
        this.usesSearchbox = usesSearchbox;
    }

    public String getSearchboxHint() {
        return searchboxHint;
    }

    public void setSearchboxHint(String searchboxHint) {
        this.searchboxHint = searchboxHint;
    }

    public int getSearchboxMenuId() {
        return searchboxMenuId;
    }

    public void setSearchboxMenuId(int searchboxMenuId) {
        this.searchboxMenuId = searchboxMenuId;
    }

    public boolean shouldAddToBackstatck() {
        return shouldAddToBackstatck;
    }

    public void setShouldAddToBackstatck(boolean shouldAddToBackstatck) {
        this.shouldAddToBackstatck = shouldAddToBackstatck;
    }

    public boolean usesFab() {
        return usesFab;
    }

    public void setUsesFab(boolean usesFab) {
        this.usesFab = usesFab;
    }

    public int getFabIconId() {
        return fabIconId;
    }

    public void setFabIconId(int fabIconId) {
        this.fabIconId = fabIconId;
    }

    public boolean isTopLevel() {
        return isTopLevel;
    }

    public void setTopLevel(boolean topLevel) {
        isTopLevel = topLevel;
    }
}
