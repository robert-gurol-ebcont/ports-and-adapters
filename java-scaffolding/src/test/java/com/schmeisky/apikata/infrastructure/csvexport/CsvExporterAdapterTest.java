package com.schmeisky.apikata.infrastructure.csvexport;

import com.schmeisky.apikata.business.ExporterPort;
import com.schmeisky.apikata.business.WeatherData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CsvExporterAdapterTest {

    private static final Instant TEST_TIME = Instant.parse("2023-04-14T22:23:24.00+02:00");
    private static final Clock TEST_CLOCK = Clock.fixed(TEST_TIME, ZoneId.of("Europe/Berlin"));

    @Test
    public void export_writesDataAsCsv(@TempDir Path tempDir) throws Exception {
        List<WeatherData> inputData = aWeatherDataList();
        ExporterPort csvExporter = new CsvExporterAdapter(tempDir, TEST_CLOCK);

        csvExporter.exportToNewFile(inputData);
        Path outputPath = tempDir.resolve("weather_data_2023-04-14_22:23:24.csv");
        assertThat(outputPath).exists();
        assertThat(outputPath).content().isEqualTo(
                "id,name,date,time,temperature,pressure,wind_direction\n" +
                        "1,Vienna,2023-04-14,13:37:00,20,1000,N\n" +
                        "2,Potsdam,2023-04-13,00:23:42,\"-11,7\",999,S\n" +
                        "3,Prague,2023-04-13,00:23:42,\"-11,7\",999,\n");
    }

    private List<WeatherData> aWeatherDataList() {
        WeatherData item1 = new WeatherData(1, "Vienna", "2023-04-14", "13:37:00", "20", "1000", "N");
        WeatherData item2 = new WeatherData(2, "Potsdam", "2023-04-13", "00:23:42", "-11,7", "999", "S");
        WeatherData item3 = new WeatherData(3, "Prague", "2023-04-13", "00:23:42", "-11,7", "999", null);
        return List.of(item1, item2, item3);
    }

}