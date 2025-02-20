package com.anemon.ykssayac.Widgets;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import com.anemon.ykssayac.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget3 extends AppWidgetProvider {
    private static String gun;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT_DATE = "date";
    SharedPreferences sharedPreferences ;



    public void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        sharedPreferences= context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        String data=sharedPreferences.getString(TEXT_DATE,"2020-06-27 10:00");
        int yil=Integer.parseInt(data.substring(0,4));
        int ay=Integer.parseInt(data.substring(5,7));
        int gunp=Integer.parseInt(data.substring(8,10));
        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, yil);
        c.set(Calendar.MONTH, ay-1);
        c.set(Calendar.DAY_OF_MONTH, gunp);
        c.set(Calendar.HOUR_OF_DAY, 10);

        Date tyt = c.getTime();
        Date bugun = new Date();
        long Tsaat = tyt.getTime() - bugun.getTime();
        Tsaat = Tsaat / (1000L*60L*60L);
        int gune = (int)  Tsaat/24;
        int saat = (int) Tsaat/24;
        gun=String.valueOf(gune);


        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget3);
        views.setTextViewText(R.id.widget3, gun);
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
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

