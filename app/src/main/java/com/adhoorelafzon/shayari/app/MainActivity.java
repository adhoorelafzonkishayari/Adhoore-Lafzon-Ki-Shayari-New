package com.adhoorelafzon.shayari.app;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends Activity {

    private TextView shayariText;
    private EditText topicInput;
    private TextToSpeech hindiVoice;

    private final String sadShayari =
            "Kabhi kabhi khamoshi bhi bahut kuch keh jaati hai,\n" +
            "jo baat lafzon se na ho,\n" +
            "woh aankhon se reh jaati hai.";

    private final String romanticShayari =
            "Teri muskurahat meri pehchaan ban gayi,\n" +
            "teri har baat meri jaan ban gayi,\n" +
            "tum mile to zindagi khoobsurat lagne lagi.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        hindiVoice = new TextToSpeech(this, result -> {
            if (result == TextToSpeech.SUCCESS) {
                int languageResult =
                        hindiVoice.setLanguage(new Locale("hi", "IN"));

                if (languageResult == TextToSpeech.LANG_MISSING_DATA ||
                    languageResult == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(this,
                            "Phone mein Hindi voice install karein.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER_HORIZONTAL);
        root.setPadding(32, 35, 32, 32);
        root.setBackgroundColor(Color.rgb(253, 247, 253));

        TextView title = new TextView(this);
        title.setText("Adhoore Lafzon Ki Shayari");
        title.setTextSize(27);
        title.setTextColor(Color.rgb(65, 45, 80));
        title.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);
        root.addView(title, new LinearLayout.LayoutParams(-1, -2));

        TextView subtitle = new TextView(this);
        subtitle.setText("Hindi Shayari Voice Creator");
        subtitle.setTextSize(18);
        subtitle.setGravity(Gravity.CENTER);
        subtitle.setTextColor(Color.DKGRAY);
        root.addView(subtitle);

        topicInput = new EditText(this);
        topicInput.setHint("Shayari ka topic (optional)");
        topicInput.setSingleLine(true);

        LinearLayout.LayoutParams inputParams =
                new LinearLayout.LayoutParams(-1, -2);
        inputParams.topMargin = 24;
        root.addView(topicInput, inputParams);

        Button sadButton = createButton("Sad Shayari");
        root.addView(sadButton);
        sadButton.setOnClickListener(v ->
                shayariText.setText(sadShayari));

        Button romanticButton = createButton("Romantic Shayari");
        root.addView(romanticButton);
        romanticButton.setOnClickListener(v ->
                shayariText.setText(romanticShayari));

        shayariText = new TextView(this);
        shayariText.setText(sadShayari);
        shayariText.setTextSize(22);
        shayariText.setTextColor(Color.rgb(65, 45, 80));
        shayariText.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams shayariParams =
                new LinearLayout.LayoutParams(-1, -2);
        shayariParams.topMargin = 28;
        shayariParams.bottomMargin = 20;
        root.addView(shayariText, shayariParams);

        Button voiceButton = createButton("Hindi Voice Sunaye");
        root.addView(voiceButton);
        voiceButton.setOnClickListener(v -> {
            String text = shayariText.getText().toString().trim();

            if (hindiVoice != null && !text.isEmpty()) {
                hindiVoice.setSpeechRate(0.85f);
                hindiVoice.setPitch(0.9f);
                hindiVoice.speak(
                        text,
                        TextToSpeech.QUEUE_FLUSH,
                        null,
                        "shayari_voice"
                );
            }
        });

        TextView status = new TextView(this);
        status.setText(
                "Branding: Adhoore Lafzon Ki Shayari\n" +
                "Voice preview: phone ke Hindi voice engine se\n" +
                "MP4 video generation: abhi add karna baaki hai."
        );
        status.setTextSize(15);
        status.setTextColor(Color.DKGRAY);
        status.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams statusParams =
                new LinearLayout.LayoutParams(-1, -2);
        statusParams.topMargin = 28;
        root.addView(status, statusParams);

        setContentView(root);
    }

    private Button createButton(String text) {
        Button button = new Button(this);
        button.setText(text);
        button.setTextSize(16);
        button.setTextColor(Color.WHITE);
        button.setAllCaps(false);
        button.setBackgroundColor(Color.rgb(111, 79, 174));

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(-1, 58);
        params.topMargin = 12;
        button.setLayoutParams(params);

        return button;
    }

    @Override
    protected void onDestroy() {
        if (hindiVoice != null) {
            hindiVoice.stop();
            hindiVoice.shutdown();
        }
        super.onDestroy();
    }
}
