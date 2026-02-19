package com.relatosdepapel.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EvidenceManager {

    private static final String EVIDENCE_FOLDER = "target/evidencias/";

    public static void saveEvidence(String testName, String content) {
        try {
            java.io.File folder = new java.io.File(EVIDENCE_FOLDER);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

            String fileName = EVIDENCE_FOLDER +
                    testName.replace(" ", "_") +
                    "_" + timestamp + ".txt";

            FileWriter writer = new FileWriter(fileName);
            writer.write(content);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}