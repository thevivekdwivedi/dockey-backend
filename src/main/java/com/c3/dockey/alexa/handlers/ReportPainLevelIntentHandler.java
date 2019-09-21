package com.c3.dockey.alexa.handlers;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.*;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.SimpleCard;

public class ReportPainLevelIntentHandler implements Speechlet {
    @Override
    public void onSessionStarted(SessionStartedRequest sessionStartedRequest, Session session) throws SpeechletException {

    }

    @Override
    public SpeechletResponse onLaunch(LaunchRequest launchRequest, Session session) throws SpeechletException {
        return null;
    }

    @Override
    public SpeechletResponse onIntent(IntentRequest intentRequest, Session session) throws SpeechletException {
        Intent intent = intentRequest.getIntent();

        if (intent == null) {
            throw new SpeechletException("Unrecognized intent");
        }

        String intentName = intent.getName();

        if (intentName.equals("ReportPainLevel")) {
            Slot slot = intent.getSlot("pain");
            int painLevel = Integer.parseInt(slot.getValue());

            //TODO: Transfer this value to the Database.

            String speechText = null;
            if (painLevel >= 8) {
                speechText = "Hold tight. I'm calling the doctor.";
            } else {
                speechText = "The pain is expected sometimes. Don't worry about it.";
            }

            SimpleCard card = new SimpleCard();
            card.setContent(speechText);

            PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
            speech.setText(speechText);

            SpeechletResponse response = SpeechletResponse.newTellResponse(speech, card);
            return response;
        } else {
            throw new SpeechletException("I didn't quite get that.");
        }
    }

    @Override
    public void onSessionEnded(SessionEndedRequest sessionEndedRequest, Session session) throws SpeechletException {

    }
}
