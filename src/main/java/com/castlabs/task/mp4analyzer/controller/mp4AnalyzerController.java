package com.castlabs.task.mp4analyzer.controller;


import com.castlabs.task.mp4analyzer.model.Box;
import com.castlabs.task.mp4analyzer.service.mp4AnalyzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class mp4AnalyzerController {
    @Autowired
    private mp4AnalyzerService service;

    @GetMapping("/analyze")
    public List<Box> analyze(@RequestParam String url) throws IOException {
        return service.analyzeMp4(url);
    }
    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handleIOException(IOException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
