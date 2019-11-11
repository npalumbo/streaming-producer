package com.streamingproducer.services;

import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import com.streamingproducer.model.AnalysedSentence;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import org.springframework.stereotype.Component;

@Component
public class SentimentService {

    private final StanfordCoreNLP pipeline;

    public SentimentService() {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,parse,lemma,ner,sentiment");
        pipeline = new StanfordCoreNLP(props);
    }

    public List<AnalysedSentence> getAnalysedSentences(String text) {
        Annotation document = new Annotation(text);

        pipeline.annotate(document);

        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
        return sentences.stream().map(sentence -> {
            String sentiment = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
            return new AnalysedSentence(sentence.toString(), sentiment);
        }).collect(Collectors.toList());
    }
}
