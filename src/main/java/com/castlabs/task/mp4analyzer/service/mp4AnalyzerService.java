package com.castlabs.task.mp4analyzer.service;

import com.castlabs.task.mp4analyzer.model.Box;
import com.castlabs.task.mp4analyzer.model.BoxType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class mp4AnalyzerService {

    private static final Logger logger = LoggerFactory.getLogger(mp4AnalyzerService.class);

    public List<Box> analyzeMp4(String urlString) throws IOException {
        logger.info("Analyzing MP4 from URL: {}", urlString);
        URL url = new URL(urlString);
        try (InputStream is = url.openStream(); DataInputStream dis = new DataInputStream(is)) {
            return parseBoxes(dis);
        }
    }

    private List<Box> parseBoxes(DataInputStream dis) throws IOException {
        List<Box> boxes = new ArrayList<>();
        while (dis.available() > 0) {
            int size = dis.readInt();
            byte[] typeBytes = new byte[4];
            dis.readFully(typeBytes);
            String type = new String(typeBytes);
            BoxType boxType = BoxType.fromString(type);

            if (boxType == null) {
                logger.warn("Unsupported box type encountered: {}", type);
                continue; // Skip this box and continue to the next one
            }

            Box box = new Box();
            box.setSize(size);
            box.setType(type);

            if (type.equals("MOOF") || type.equals("TRAF")) {
                box.setSubBoxes(parseBoxes(new DataInputStream(new TruncatedInputStream(dis, size - 8))));
            } else {
                // skip the data for this box
                dis.skipBytes(size - 8);
            }
            boxes.add(box);
        }
        return boxes;
    }

    private static class TruncatedInputStream extends InputStream {
        private final DataInputStream source;
        private int remaining;

        public TruncatedInputStream(DataInputStream source, int size) {
            this.source = source;
            this.remaining = size;
        }

        @Override
        public int read() throws IOException {
            if (remaining-- > 0) {
                return source.read();
            }
            return -1;
        }
    }
}
