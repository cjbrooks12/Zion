package com.caseyjbrooks.zion.app.dashboard;

import android.content.Context;
import android.support.annotation.NonNull;

import com.caseyjbrooks.zion.widget.CardView;

public abstract class DashboardCardBase extends CardView {
    public DashboardCardBase(Context context) {
        super(context);
    }

    public abstract @NonNull Class<? extends DashboardCardConfiguration> getDashboardCardConfigurationClass();
}
