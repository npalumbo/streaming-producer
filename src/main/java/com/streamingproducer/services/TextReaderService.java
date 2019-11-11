package com.streamingproducer.services;

import java.io.File;
import java.util.Scanner;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TextReaderService {

    private static final String DELIMITER = "\n\n";
    private static final String FILE_NAME = "extract.txt";
    private static final Logger LOGGER = LoggerFactory.getLogger(TextReaderService.class);

    public Stream<String> textStream(int limit) {

        try {
            File source = new File(ClassLoader.getSystemResource(FILE_NAME).toURI());
            Scanner read = new Scanner(source);
            read.useDelimiter(DELIMITER);

            final Spliterator<String> splt = Spliterators.spliterator(read, Long.MAX_VALUE, Spliterator.ORDERED | Spliterator.NONNULL);
            return StreamSupport.stream(splt, false).limit(limit).onClose(read::close);
        }
        catch (Exception ex) {
            LOGGER.warn("Error reading file", ex);
            return Stream.empty();
        }
    }
}
