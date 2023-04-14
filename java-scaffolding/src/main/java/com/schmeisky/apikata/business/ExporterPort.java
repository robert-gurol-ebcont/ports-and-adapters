package com.schmeisky.apikata.business;

import com.schmeisky.apikata.business.WeatherData;

import java.io.File;
import java.util.List;

public interface ExporterPort {

    void exportToNewFile(List<WeatherData> data);
}
