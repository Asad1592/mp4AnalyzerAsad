package com.castlabs.task.mp4analyzer.service;

import com.castlabs.task.mp4analyzer.model.Box;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Mp4AnalyzerServiceTest {

    private Mp4AnalyzerService service;

    @BeforeEach
    public void setUp() {
        service = new Mp4AnalyzerService();
    }

    @Test
    public void testAnalyzeMp4() throws IOException {
        // Given the way the code reads boxes, we need to provide the size (as an integer)
        // before the box type. The size should account for the total bytes of the box
        // including the size and type.
        // For simplicity, let's assume MOOF and TRAF boxes are each 8 bytes long
        // (4 for size and 4 for type).
        byte[] mockData = new byte[] {
                0, 0, 0, 8, 'M', 'O', 'O', 'F',
                0, 0, 0, 8, 'T', 'R', 'A', 'F'
        };
        ByteArrayInputStream bais = new ByteArrayInputStream(mockData);
        DataInputStream dis = new DataInputStream(bais);

        List<Box> boxes = service.parseBoxes(dis);

        assertEquals(2, boxes.size());
        assertEquals("MOOF", boxes.get(0).getType());
        assertEquals("TRAF", boxes.get(1).getType());
    }
}
