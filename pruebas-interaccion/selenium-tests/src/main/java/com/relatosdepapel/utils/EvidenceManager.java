package com.relatosdepapel.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

public class EvidenceManager {

    public static void saveEvidence(String testName, String content) {

        try {
            Path folder = Path.of("target/api-evidences");
            Files.createDirectories(folder);

            String safeName = testName.replaceAll("[^a-zA-Z0-9]", "_");

            Path file = folder.resolve(safeName + ".txt");

            String finalContent =
                    "=== TEST EVIDENCE ===\n" +
                    "Test: " + testName + "\n" +
                    "Date: " + LocalDateTime.now() + "\n\n" +
                    content;

            Files.writeString(file, finalContent);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}