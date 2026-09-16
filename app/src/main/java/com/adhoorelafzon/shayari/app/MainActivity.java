package com.adhoorelafzon.shayari.app;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(32, 32, 32, 32);
        root.setBackgroundColor(Color.rgb(20, 15, 25));

        TextView title = new TextView(this);
        title.setText("Adhoore Lafzon Ki Shayari");
        title.setTextColor(Color.WHITE);
        title.setTextSize(26);
        title.setGravity(Gravity.CENTER);

        root.addView(title,
                new LinearLayout.LayoutParams(-1, -2));

        TextView sub = new TextView(this);
        sub.setText("Sad • Romantic • AI Shayari");
        sub.setTextColor(Color.LTGRAY);
        sub.setTextSize(16);
        sub.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams subParams =
                new LinearLayout.LayoutParams(-1, -2);
        subParams.topMargin = 24;

        root.addView(sub, subParams);

        TextView status = new TextView(this);
        status.setText("App setup ready ❤️");
        status.setTextColor(Color.WHITE);
        status.setTextSize(18);
        status.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams statusParams =
                new LinearLayout.LayoutParams(-1, -2);
        statusParams.topMargin = 40;

        root.addView(status, statusParams);

        setContentView(root);
    }
}
