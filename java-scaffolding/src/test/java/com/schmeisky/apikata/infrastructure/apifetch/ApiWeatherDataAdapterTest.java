package com.schmeisky.apikata.infrastructure.apifetch;

import com.schmeisky.apikata.business.WeatherData;
import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ApiFetchWeatherDataAdapterTest {

    private static final String FILE_PATH = "src/test/resources/test-json-response.json";

    @Test
    void test_getData_parsesJsonDataCorrectly() throws Exception {
        URL url = new File(FILE_PATH).toURI().toURL();
        ApiFetchWeatherDataAdapter sut = new ApiFetchWeatherDataAdapter(url);

        List<WeatherData> actual = sut.getData();

        assertThat(actual).hasSize(1);
        WeatherData dataSet = actual.get(0);
        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
            softly.assertThat(dataSet.stationId()).isEqualTo(1L);
            softly.assertThat(dataSet.stationName()).isEqualTo("Reykjavík");
            softly.assertThat(dataSet.date()).isEqualTo("2023-04-14");
            softly.assertThat(dataSet.time()).isEqualTo("09:00:00");
            softly.assertThat(dataSet.atmosphericPressure()).isEqualTo("1009");
            softly.assertThat(dataSet.airTemperature()).isEqualTo("4.1");
            softly.assertThat(dataSet.windDirection()).isEqualTo("E");
        }
    }
}
