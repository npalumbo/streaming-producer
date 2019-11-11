package com.streamingproducer.model;

public class AnalysedSentence {
    private final String sentence;
    private final String sentiment;

    public AnalysedSentence(String sentence, String sentiment) {
        this.sentence = sentence;
        this.sentiment = sentiment;
    }

    public String getSentence() {
        return sentence;
    }

    public String getSentiment() {
        return sentiment;
    }
}
