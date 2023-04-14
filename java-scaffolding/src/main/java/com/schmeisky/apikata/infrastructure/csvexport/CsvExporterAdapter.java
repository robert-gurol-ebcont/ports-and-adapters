package com.schmeisky.apikata.infrastructure.csvexport;

import com.schmeisky.apikata.business.ExporterPort;
import com.schmeisky.apikata.business.WeatherData;
import com.schmeisky.apikata.config.GlobalConfig;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Clock;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CsvExporterAdapter implements ExporterPort {

    private static final CSVFormat CSV_FORMAT = CSVFormat.DEFAULT
            .builder()
            .setHeader("id", "name", "date", "time", "temperature", "pressure", "wind_direction")
            .setRecordSeparator("\n")
            .build();

    private static final String PATTERN_FORMAT = "yyyy-MM-dd_HH:mm:ss";

    private final Path outputDir;
    private final Clock clock;
    private final DateTimeFormatter timeStampFormatter;

    public CsvExporterAdapter() {
        this(GlobalConfig.APP_OUTPUT_DIR, Clock.systemDefaultZone());
    }

    public CsvExporterAdapter(Path outputDir, Clock clock) {
        this.outputDir = outputDir;
        this.clock = clock;
        timeStampFormatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT)
                .withZone(clock.getZone());
    }

    @Override
    public void exportToNewFile(List<WeatherData> data) {
        String timePart = timeStampFormatter.format(Instant.now(clock));
        String fileName = "weather_data_" + timePart + ".csv";
        try {
            Path destination = Files.createFile(outputDir.resolve(fileName));
            exportThrowing(data, destination);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void exportThrowing(List<WeatherData> data, Path destination) throws IOException {
        StringWriter writer = new StringWriter();
        try (CSVPrinter printer = new CSVPrinter(writer, CSV_FORMAT)) {
            data.forEach(entity -> print(printer, entity));
        }

        try (OutputStream outputStream = Files.newOutputStream(destination)) {
            IOUtils.write(writer.toString(), outputStream, StandardCharsets.UTF_8);
        }
    }

    private static void print(CSVPrinter printer, WeatherData entity) {
        try {
            printer.printRecord(entity.getStationId(), entity.getStationName(), entity.getDate(), entity.getTime(), entity.getAirTemperature(), entity.getAtmosphericPressure(), entity.getWindDirection());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
