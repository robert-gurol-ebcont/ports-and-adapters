package com.schmeisky.apikata.infrastructure.trigger;

import com.schmeisky.apikata.business.Application;
import com.schmeisky.apikata.business.WeatherFetchTriggerPort;

/**
 * This is a very shallow wrapper, admittedly.
 *
 * A real-life implementation would be, for example, an HTTP endpoint (servlet / controller / handler) that will do some
 * work in providing (and securing) the interface and unwrapping parameters.
 */
public class SimpleTriggerAdapter implements WeatherFetchTriggerPort {

    private final Application applicationToTrigger;

    public SimpleTriggerAdapter(Application application) {
        this.applicationToTrigger = application;
    }
    @Override
    public void downloadTheWeather() {
        applicationToTrigger.downloadTheWeather();
    }
}
