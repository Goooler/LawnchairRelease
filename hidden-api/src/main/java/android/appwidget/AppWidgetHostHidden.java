package android.appwidget;

import android.widget.RemoteViewsHidden;

import dev.rikka.tools.refine.RefineAs;

@RefineAs(AppWidgetHost.class)
public class AppWidgetHostHidden {

    public void setInteractionHandler(RemoteViewsHidden.InteractionHandler interactionHandler) {
        throw new RuntimeException("Stub!");
    }
}
