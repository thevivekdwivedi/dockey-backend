package com.c3.dockey.alexa.handlers;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.*;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.SimpleCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InitPainReportIntentHandler implements Speechlet {
    protected Logger logger = LoggerFactory.getLogger(InitPainReportIntentHandler.class);

    @Override
    public void onSessionStarted(SessionStartedRequest sessionStartedRequest, Session session) throws SpeechletException {

    }

    @Override
    public SpeechletResponse onLaunch(LaunchRequest launchRequest, Session session) throws SpeechletException {
        return null;
    }

    @Override
    public SpeechletResponse onIntent(IntentRequest request, Session session) throws SpeechletException {
        Intent intent = request.getIntent();

        if (intent == null) {
            throw new SpeechletException("Unrecognized intent");
        }

        String intentName = intent.getName();

        if (intentName.equals("InitPainReport")) {
            String speechText = "Oh, I'm so sorry. On a scale of one to ten, how much pain are you in?";

            SimpleCard card = new SimpleCard();
            card.setContent(speechText);

            PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
            speech.setText(speechText);

            SpeechletResponse response = SpeechletResponse.newTellResponse(speech, card);
            return response;
        } else {
            throw new SpeechletException("I don't quite understand that intent.");
        }
    }

    @Override
    public void onSessionEnded(SessionEndedRequest sessionEndedRequest, Session session) throws SpeechletException {

    }
}
