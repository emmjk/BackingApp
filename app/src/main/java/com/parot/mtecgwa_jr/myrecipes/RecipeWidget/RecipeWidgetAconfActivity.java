package com.parot.mtecgwa_jr.myrecipes.RecipeWidget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.parot.mtecgwa_jr.myrecipes.Adapter.GridAdapter;
import com.parot.mtecgwa_jr.myrecipes.R;

import java.util.ArrayList;
import java.util.logging.Handler;

public class RecipeWidgetAconfActivity extends AppCompatActivity {

    private GridView gridView ;
    private GridAdapter gridAdapter;
    private ArrayList<String> recipeList = new ArrayList<>();
    private int mAppWidgetId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_widget_aconf);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }



        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());

        RemoteViews views = new RemoteViews(getApplicationContext().getPackageName(),
                R.layout.my_recipe_widget);
        appWidgetManager.updateAppWidget(mAppWidgetId, views);




                MyRecipeWidget myRecipeWidget = new MyRecipeWidget();
                int[] ids = {mAppWidgetId};
                myRecipeWidget.onUpdate(getApplicationContext() , AppWidgetManager.getInstance(getApplicationContext()) , ids);

                Intent resultValue = new Intent();
                resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
                setResult(RESULT_OK, resultValue);
                finish();

    }



}
