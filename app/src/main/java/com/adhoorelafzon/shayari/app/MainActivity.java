package com.adhoorelafzon.shayari.app;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    TextView shayariText;
    EditText topicInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER_HORIZONTAL);
        root.setPadding(32, 35, 32, 32);
        root.setBackgroundColor(Color.rgb(253, 247, 253));

        // Title
        TextView title = new TextView(this);
        title.setText("Adhoore Lafzon Ki Shayari");
        title.setTextSize(28);
        title.setTextColor(Color.rgb(65, 61, 67));
        title.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);

        root.addView(title,
                new LinearLayout.LayoutParams(
                        -1, -2
                ));

        // Subtitle
        TextView subtitle = new TextView(this);
        subtitle.setText("AI Shayari Video Creator");
        subtitle.setTextSize(18);
        subtitle.setTextColor(Color.rgb(75, 70, 78));
        subtitle.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams subParams =
                new LinearLayout.LayoutParams(-1, -2);
        subParams.topMargin = 10;
        root.addView(subtitle, subParams);

        // Topic input
        topicInput = new EditText(this);
        topicInput.setHint("Shayari ka topic (optional)");
        topicInput.setTextSize(17);
        topicInput.setSingleLine(true);

        LinearLayout.LayoutParams inputParams =
                new LinearLayout.LayoutParams(-1, -2);
        inputParams.topMargin = 35;
        root.addView(topicInput, inputParams);

        // Sad button
        Button sadButton = createButton("Sad Shayari");
        root.addView(sadButton);

        sadButton.setOnClickListener(v -> {
            shayariText.setText(
                    "Kabhi kabhi khamoshi bhi bahut kuch keh jaati hai,\n" +
                    "jo baat lafzon se na ho,\n" +
                    "woh aankhon se reh jaati hai."
            );
        });

        // Romantic button
        Button romanticButton = createButton("Romantic Shayari");
        root.addView(romanticButton);

        romanticButton.setOnClickListener(v -> {
            shayariText.setText(
                    "Teri muskurahat meri pehchaan ban gayi,\n" +
                    "teri har baat meri jaan ban gayi,\n" +
                    "tum mile to laga zindagi khoobsurat hai,\n" +
                    "warna zindagi bas ek kahani thi."
            );
        });

        // Shayari display
        shayariText = new TextView(this);
        shayariText.setText(
                "Kabhi kabhi khamoshi bhi bahut kuch keh jaati hai,\n" +
                "jo baat lafzon se na ho,\n" +
                "woh aankhon se reh jaati hai."
        );
        shayariText.setTextSize(23);
        shayariText.setTextColor(Color.rgb(65, 61, 67));
        shayariText.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams shayariParams =
                new LinearLayout.LayoutParams(-1, -2);
        shayariParams.topMargin = 45;
        shayariParams.bottomMargin = 45;

        root.addView(shayariText, shayariParams);

        // Video button
        Button videoButton = createButton("Video Banaye");
        root.addView(videoButton);

        videoButton.setOnClickListener(v -> {
            String topic = topicInput.getText().toString().trim();

            if (topic.isEmpty()) {
                Toast.makeText(
                        MainActivity.this,
                        "Shayari select ho gayi. Video workflow start hoga.",
                        Toast.LENGTH_LONG
                ).show();
            } else {
                Toast.makeText(
                        MainActivity.this,
                        "Topic: " + topic + "\nVideo workflow start hoga.",
                        Toast.LENGTH_LONG
                ).show();
            }
        });

        // Status
        TextView status = new TextView(this);
        status.setText(
                "Video workflow ready:\n" +
                "AI voice + music + branding + social publishing integration."
        );
        status.setTextSize(16);
        status.setTextColor(Color.rgb(75, 70, 78));
        status.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams statusParams =
                new LinearLayout.LayoutParams(-1, -2);
        statusParams.topMargin = 40;

        root.addView(status, statusParams);

        setContentView(root);
    }

    private Button createButton(String text) {

        Button button = new Button(this);
        button.setText(text);
        button.setTextSize(17);
        button.setTextColor(Color.WHITE);
        button.setAllCaps(false);
        button.setBackgroundColor(Color.rgb(111, 79, 174));

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(-1, 60);

        params.topMargin = 14;

        button.setLayoutParams(params);

        return button;
    }
}
