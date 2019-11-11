package com.streamingproducer.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.streamingproducer.model.Phrase;
import com.streamingproducer.services.SentimentService;
import com.streamingproducer.services.TextReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlockingController {

    private final SentimentService sentimentService;
    private final TextReaderService textReaderService;

    @Autowired
    public BlockingController(SentimentService sentimentService, TextReaderService textReaderService) {
        this.sentimentService = sentimentService;
        this.textReaderService = textReaderService;
    }

    @RequestMapping("/blocking")
    public List<Phrase> getPhrases(@RequestParam(defaultValue = "10000000", required = false) Integer numPhrases) {
        Stream<String> fileStream = textReaderService.textStream(numPhrases);

        return fileStream
                .map(phrase -> new Phrase(phrase, sentimentService.getAnalysedSentences(phrase)))
                .collect(Collectors.toList());
    }

}
