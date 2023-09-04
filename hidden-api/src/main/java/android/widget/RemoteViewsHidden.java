package android.widget;

import android.app.PendingIntent;
import android.view.View;

import dev.rikka.tools.refine.RefineAs;

@RefineAs(RemoteViews.class)
public class RemoteViewsHidden {

    public interface InteractionHandler {
        boolean onInteraction(
            View view,
            PendingIntent pendingIntent,
            RemoteViews.RemoteResponse response);
    }
}
