package com.schmeisky.apikata.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Simple class with static state in lieu of config files etc
 */
public class GlobalConfig {
    public static final Path APP_OUTPUT_DIR = Path.of("target", "outputs");
    static {
        try {
            Files.createDirectories(APP_OUTPUT_DIR);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final String APP_URL_TO_QUERY = "https://apis.is/weather/observations/en?stations=1,422";
}
