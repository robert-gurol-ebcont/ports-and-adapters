package com.schmeisky.apikata.business;

/**
 * Driving side (system-inbound) port to trigger the functionality.
 *
 * As it's an inbound port, it is to be implemented by the application itself (rather than consumed as a delegate).
 */
public interface WeatherFetchTriggerPort {
    void downloadTheWeather();
}
