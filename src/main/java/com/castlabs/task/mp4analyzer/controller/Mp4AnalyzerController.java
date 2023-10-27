package com.castlabs.task.mp4analyzer.controller;


import com.castlabs.task.mp4analyzer.model.Box;
import com.castlabs.task.mp4analyzer.service.Mp4AnalyzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * REST controller for analyzing MP4 files.
 */
@RestController
public class Mp4AnalyzerController {
    @Autowired
    private Mp4AnalyzerService service;

    /**
     * Analyzes an MP4 file from a given URL.
     *
     * @param url The URL of the MP4 file to be analyzed.
     * @return A list of boxes found within the MP4 file.
     * @throws IOException If there's an error reading from the URL.
     */
    @GetMapping("/analyze")
    public List<Box> analyze(@RequestParam String url) throws IOException {
        return service.analyzeMp4(url);
    }
    /**
     * Exception handler for IOExceptions.
     *
     * @param ex The IOException encountered.
     * @return A response entity with the exception's message and a bad request status.
     */
    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handleIOException(IOException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
