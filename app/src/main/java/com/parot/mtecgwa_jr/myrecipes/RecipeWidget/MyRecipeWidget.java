package com.parot.mtecgwa_jr.myrecipes.RecipeWidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.widget.RemoteViews;

import com.parot.mtecgwa_jr.myrecipes.MainActivity;
import com.parot.mtecgwa_jr.myrecipes.R;
import com.parot.mtecgwa_jr.myrecipes.Recipe;

import static com.google.android.exoplayer2.mediacodec.MediaCodecInfo.TAG;

/**
 * Implementation of App Widget functionality.
 */
public class MyRecipeWidget extends AppWidgetProvider {


    public static String UPDATE_WIDGET = "update widget";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            RemoteViews views = new RemoteViews(
                    context.getPackageName(),
                    R.layout.my_recipe_widget);

            Log.v(TAG , "Widget Id is : "+appWidgetId);


            Intent intent = new Intent(context, MyRemoteViewsService.class);
            views.setRemoteAdapter(R.id.list_container, intent);


            appWidgetManager.updateAppWidget(appWidgetId, views);        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        String action = intent.getAction();
        if(action.equals(UPDATE_WIDGET))
        {

//           MyRemoteViewsFactory myRemoteViewsFactory = new MyRemoteViewsFactory(context);
//
//            myRemoteViewsFactory.onDataSetChanged();

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            int [] ids = appWidgetManager.getAppWidgetIds(new ComponentName(context, this.getClass()));
            appWidgetManager.notifyAppWidgetViewDataChanged(ids, R.id.list_container);
        }



    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

