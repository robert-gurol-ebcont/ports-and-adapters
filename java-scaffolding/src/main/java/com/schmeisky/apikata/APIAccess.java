package com.schmeisky.apikata;

import com.schmeisky.apikata.business.Application;
import com.schmeisky.apikata.business.ExporterPort;
import com.schmeisky.apikata.business.FetchWeatherDataPort;
import com.schmeisky.apikata.infrastructure.apifetch.ApiFetchWeatherDataAdapter;
import com.schmeisky.apikata.infrastructure.csvexport.CsvExporterAdapter;
import com.schmeisky.apikata.infrastructure.trigger.SimpleTriggerAdapter;

public class APIAccess {

    public static void main(final String[] args) {
        // system-outbound / driven adapters
        FetchWeatherDataPort dataFetchAdapter = new ApiFetchWeatherDataAdapter();
        ExporterPort csvExporter = new CsvExporterAdapter();

        // our system - depends on the above adapters to implement the driven ports
        Application hexagonalArchitectureApplication = new Application(dataFetchAdapter, csvExporter);

        // system-inbound / driving adapter -> the system implements the port!
        SimpleTriggerAdapter trigger = new SimpleTriggerAdapter(hexagonalArchitectureApplication);

        // to trigger the application to do any work, we need to go through the driving adapter
        trigger.downloadTheWeather();
    }
}
