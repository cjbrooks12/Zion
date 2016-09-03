package com.caseyjbrooks.zion.app.notifications;

import android.support.annotation.NonNull;

public abstract class NotificationBase {
    public abstract @NonNull Class<? extends NotificationConfiguration> getNotificationConfigurationClass();
}
