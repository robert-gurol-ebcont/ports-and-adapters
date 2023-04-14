package com.schmeisky.apikata.infrastructure.apifetch;

import com.schmeisky.apikata.business.WeatherData;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class ApiWeatherDataMapper {

    private final ModelMapper modelMapper;

    public ApiWeatherDataMapper() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.typeMap(ApiWeatherData.class, WeatherData.class)
                .addMappings(mapper -> mapper.skip(WeatherData::setTime))
                .addMappings(mapper -> mapper.skip(WeatherData::setDate))
        ;
        modelMapper.validate();
    }

    public WeatherData fromApiWeatherData(ApiWeatherData input) {
        WeatherData result = modelMapper.map(input, WeatherData.class);
        String[] splitTimeStamp = input.dateTime.split(" ");
        if (splitTimeStamp.length > 1) {
            result.setDate(splitTimeStamp[0]);
            result.setTime(splitTimeStamp[1]);
        }
        return result;
    }
}
