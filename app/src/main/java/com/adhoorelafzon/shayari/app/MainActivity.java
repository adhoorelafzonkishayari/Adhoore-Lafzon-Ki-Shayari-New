
package com.adhoorelafzon.shayari.app;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.speech.tts.TextToSpeech;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends Activity {

    private TextView shayariText;
    private EditText topicInput;
    private TextToSpeech hindiVoice;
    private boolean voiceReady = false;

    private final String sadShayari =
            "Kabhi kabhi khamoshi bhi bahut kuch keh jaati hai,\n" +
            "jo baat lafzon se na ho,\n" +
            "woh aankhon mein reh jaati hai.";

    private final String romanticShayari =
            "Teri muskurahat meri pehchaan ban gayi,\n" +
            "teri har baat meri jaan ban gayi,\n" +
            "tum mile to zindagi khoobsurat lagne lagi.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Scrollable screen
        ScrollView scrollView = new ScrollView(this);
        scrollView.setFillViewport(true);
        scrollView.setBackgroundColor(Color.rgb(253, 247, 253));

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
        root.setPadding(24, 28, 24, 32);
        root.setFocusable(true);
        root.setLayoutParams(new ScrollView.LayoutParams(
                ScrollView.LayoutParams.MATCH_PARENT,
                ScrollView.LayoutParams.MATCH_PARENT
        ));

        // App title
        TextView title = new TextView(this);
        title.setText("Adhoore Lafzon Ki Shayari");
        title.setTextSize(27);
        title.setTextColor(Color.rgb(65, 45, 80));
        title.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);
        root.addView(title, fullWidth());

        TextView subtitle = new TextView(this);
        subtitle.setText("Hindi Shayari Voice Creator");
        subtitle.setTextSize(17);
        subtitle.setTextColor(Color.DKGRAY);
        subtitle.setGravity(Gravity.CENTER);
        addSpace(root, subtitle, 8);

        // Topic field
        topicInput = new EditText(this);
        topicInput.setHint("Shayari ka topic (optional)");
        topicInput.setSingleLine(true);
        topicInput.setTextSize(16);
        topicInput.setPadding(16, 8, 16, 8);
        addSpace(root, topicInput, 22);

        // Sad shayari button
        Button sadButton = createButton("Sad Shayari");
        root.addView(sadButton);
        sadButton.setOnClickListener(v ->
                shayariText.setText(sadShayari));

        // Romantic shayari button
        Button romanticButton = createButton("Romantic Shayari");
        root.addView(romanticButton);
        romanticButton.setOnClickListener(v ->
                shayariText.setText(romanticShayari));

        // Shayari display
        shayariText = new TextView(this);
        shayariText.setText(sadShayari);
        shayariText.setTextSize(22);
        shayariText.setTextColor(Color.rgb(65, 45, 80));
        shayariText.setGravity(Gravity.CENTER);
        shayariText.setPadding(12, 24, 12, 24);
        addSpace(root, shayariText, 20);

        // Voice button
        Button voiceButton = createButton("Hindi Voice Sunaye");
        root.addView(voiceButton);
        voiceButton.setOnClickListener(v -> {
            String text = shayariText.getText().toString().trim();

            if (hindiVoice == null || !voiceReady) {
                Toast.makeText(this,
                        "Hindi voice taiyar ho rahi hai. Thodi der baad try karein.",
                        Toast.LENGTH_LONG).show();
                return;
            }

            if (!text.isEmpty()) {
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

        // Stop voice button
        Button stopButton = createButton("Awaaz Band Karein");
        root.addView(stopButton);
        stopButton.setOnClickListener(v -> {
            if (hindiVoice != null) {
                hindiVoice.stop();
            }
        });

        // Branding and status
        TextView status = new TextView(this);
        status.setText(
                "\nAdhoore Lafzon Ki Shayari\n\n" +
                "Aapki shayari, aapki awaaz.\n\n" +
                "Note: Abhi AI se nayi shayari banana aur MP4 video banana add nahi hua hai."
        );
        status.setTextSize(15);
        status.setTextColor(Color.DKGRAY);
        status.setGravity(Gravity.CENTER);
        addSpace(root, status, 26);

        scrollView.addView(root);
        setContentView(scrollView);

        // Initialize phone's Hindi text-to-speech engine
        hindiVoice = new TextToSpeech(this, result -> {
            if (result == TextToSpeech.SUCCESS && hindiVoice != null) {
                int languageResult =
                        hindiVoice.setLanguage(new Locale("hi", "IN"));

                voiceReady =
                        languageResult != TextToSpeech.LANG_MISSING_DATA
                        && languageResult != TextToSpeech.LANG_NOT_SUPPORTED;

                if (!voiceReady) {
                    Toast.makeText(this,
                            "Phone ki settings mein Hindi voice install karein.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private LinearLayout.LayoutParams fullWidth() {
        return new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
    }

    private void addSpace(LinearLayout parent, View view, int topMargin) {
        LinearLayout.LayoutParams params = fullWidth();
        params.topMargin = topMargin;
        parent.addView(view, params);
    }

    private Button createButton(String text) {
        Button button = new Button(this);
        button.setText(text);
        button.setTextSize(17);
        button.setTextColor(Color.WHITE);
        button.setAllCaps(false);
        button.setGravity(Gravity.CENTER);
        button.setBackgroundTintList(
                android.content.res.ColorStateList.valueOf(
                        Color.rgb(111, 79, 174)
                )
        );

        LinearLayout.LayoutParams params = fullWidth();
        params.height = 60;
        params.topMargin = 14;
        button.setLayoutParams(params);

        return button;
    }

    @Override
    protected void onDestroy() {
        if (hindiVoice != null) {
            hindiVoice.stop();
            hindiVoice.shutdown();
            hindiVoice = null;
        }
        super.onDestroy();
    }
}
