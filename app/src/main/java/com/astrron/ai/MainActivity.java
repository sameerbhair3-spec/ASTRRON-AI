package com.astrron.ai;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends Activity {

    private TextToSpeech textToSpeech;
    private TextView responseText;
    private EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        responseText = findViewById(R.id.responseText);
        inputText = findViewById(R.id.inputText);
        Button askButton = findViewById(R.id.askButton);

        textToSpeech = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech.setLanguage(Locale.US);
            }
        });

        askButton.setOnClickListener(v -> {
            String question = inputText.getText().toString().trim();

            if (question.isEmpty()) {
                responseText.setText("ASTRRON: Please type something.");
                return;
            }

            String reply = "Hello! I am ASTRRON-AI. You said: " + question;
            responseText.setText(reply);

            textToSpeech.speak(
                    reply,
                    TextToSpeech.QUEUE_FLUSH,
                    null,
                    "ASTRRON_REPLY"
            );
        });
    }

    @Override
    protected void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
}