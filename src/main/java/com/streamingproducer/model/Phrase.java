package com.streamingproducer.model;

import java.util.List;

public class Phrase {

    private String originalText;
    private List<AnalysedSentence> analysedSentences;

    public Phrase(String originalText, List<AnalysedSentence> analysedSentences) {
        this.originalText = originalText;
        this.analysedSentences = analysedSentences;
    }

    public String getOriginalText() {
        return originalText;
    }

    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

    public List<AnalysedSentence> getAnalysedSentences() {
        return analysedSentences;
    }

    public void setAnalysedSentences(List<AnalysedSentence> analysedSentences) {
        this.analysedSentences = analysedSentences;
    }
}
