package com.streamingproducer.controller;

import java.util.stream.Stream;

import com.streamingproducer.model.Phrase;
import com.streamingproducer.services.SentimentService;
import com.streamingproducer.services.TextReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class StreamingController {

    private final SentimentService sentimentService;
    private final TextReaderService textReaderService;

    @Autowired
    public StreamingController(SentimentService sentimentService, TextReaderService textReaderService) {
        this.sentimentService = sentimentService;
        this.textReaderService = textReaderService;
    }

    @GetMapping(value = "/streaming", produces =  MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Phrase> getPhrases(@RequestParam(defaultValue = "10000000", required = false) Integer numPhrases) {
        Stream<String> fileStream = textReaderService.textStream(numPhrases);
        Flux<String> fileFlux = Flux.fromStream(fileStream);

        return fileFlux
                .map(phrase -> new Phrase(phrase, sentimentService.getAnalysedSentences(phrase)));
    }
}
