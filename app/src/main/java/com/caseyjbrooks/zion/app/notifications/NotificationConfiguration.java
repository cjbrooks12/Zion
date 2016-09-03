package com.caseyjbrooks.zion.app.notifications;

import android.content.Context;
import android.support.annotation.NonNull;

import com.caseyjbrooks.zion.app.activity.FeatureConfiguration;

public abstract class NotificationConfiguration {
    Context context;

    public NotificationConfiguration(Context context) {
        this.context = context.getApplicationContext();
    }

    public abstract @NonNull Class<? extends FeatureConfiguration> getFeatureConfigurationClass();
    public abstract @NonNull Class<? extends NotificationBase> getNotificationClass();
}
