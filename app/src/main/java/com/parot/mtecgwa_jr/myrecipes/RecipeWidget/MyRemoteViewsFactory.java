package com.parot.mtecgwa_jr.myrecipes.RecipeWidget;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.parot.mtecgwa_jr.myrecipes.Fragments.RecipeIngridientFragment;
import com.parot.mtecgwa_jr.myrecipes.R;
import com.parot.mtecgwa_jr.myrecipes.RecipeData.IngredientData;

import java.util.ArrayList;

/**
 * Created by mtecgwa-jr on 6/25/17.
 */

public class MyRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    Context context;
    private ArrayList<String> ingredientList = new ArrayList<>();
    private static final String TAG = MyRemoteViewsFactory.class.getName();



    public MyRemoteViewsFactory(Context context)
    {
        this.context = context;
    }

    @Override
    public void onCreate() {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        String ingredients = sharedPreferences.getString("key" , "");

        Log.v(TAG , "Retrieved string from preferences is :"+ingredients);

        String[] testArray = ingredients.split("/");

        for(int i = 0 ; i < testArray.length ; i++)
        {
            ingredientList.add(testArray[i]);
        }

    }

    @Override
    public void onDataSetChanged() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        String ingredients = sharedPreferences.getString("key" , "");

        Log.v(TAG , "List updated :"+ingredients);

        String[] testArray = ingredients.split("/");

        ingredientList.clear();

        for(int i = 0 ; i < testArray.length ; i++)
        {
            ingredientList.add(testArray[i]);
        }


    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return ingredientList.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {

        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_ingredient_list_item);
        rv.setTextViewText(R.id.name, ingredientList.get(position));

        Log.v(TAG , "Remote views is called to get view at : "+position );

        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}

