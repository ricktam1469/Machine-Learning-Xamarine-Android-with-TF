package org.tensorflow.demo;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.Locale;

public class AndroidTextToSpeech implements TextToSpeech.OnInitListener {

    private Context mContext;
    boolean initialized;
    String queuedText;
    private TextToSpeech tts;

    public AndroidTextToSpeech(Context context) {
        Log.e("TEXT TO SPEECH ", "controller");
        mContext = context;
        tts = new TextToSpeech(context, this);
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            initialized = true;
            tts.setLanguage(Locale.ENGLISH);

            if (queuedText != null) {
                speak(queuedText);
            }
        }
    }

    public void speak(String text) {
        // If not yet initialized, queue up the text.
        if (!initialized) {
            queuedText = text;
            return;
        }
        queuedText = null;
        // Before speaking the current text, stop any ongoing speech.
        tts.stop();
        // Speak the text.
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
}
