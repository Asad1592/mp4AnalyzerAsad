package com.castlabs.task.mp4analyzer.controller;

import com.castlabs.task.mp4analyzer.model.Box;
import com.castlabs.task.mp4analyzer.service.Mp4AnalyzerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestExecutionListeners(MockitoTestExecutionListener.class)
public class Mp4AnalyzerControllerTest {

    @InjectMocks
    private Mp4AnalyzerController controller;

    @Mock
    private Mp4AnalyzerService service;

    @Test
    public void testAnalyze() throws IOException {
        Box mockBox = new Box();
        mockBox.setType("MOOF");
        when(service.analyzeMp4("test_url")).thenReturn(Collections.singletonList(mockBox));

        List<Box> result = controller.analyze("test_url");
        assertEquals(1, result.size());
        assertEquals("MOOF", result.get(0).getType());
    }
}
