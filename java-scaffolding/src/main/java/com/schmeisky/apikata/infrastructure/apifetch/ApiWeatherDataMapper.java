package com.schmeisky.apikata.infrastructure.apifetch;

import com.schmeisky.apikata.business.WeatherData;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;

public class ApiWeatherDataMapper {

    private final ModelMapper modelMapper;

    public ApiWeatherDataMapper() {
        modelMapper = createAndConfigureModelMapper();
    }

    private ModelMapper createAndConfigureModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.typeMap(ApiWeatherData.class, WeatherData.class)
                .addMappings(mapper -> mapper.skip(WeatherData::setTime))
                .addMappings(mapper -> mapper.skip(WeatherData::setDate))
                .addMappings(mapper -> mapper.using(this::timeStampStringToDateStringConverter)
                        .map(ApiWeatherData::getDateTime, WeatherData::setDate))
                .addMappings(mapper -> mapper.using(this::timeStampStringToTimeStringConverter)
                        .map(ApiWeatherData::getDateTime, WeatherData::setTime));
        modelMapper.validate();
        return modelMapper;
    }

    private String timeStampStringToDateStringConverter(MappingContext<Object, Object> context) {
        String[] splitTimeStamp = context.getSource().toString().split(" ");
        return splitTimeStamp[0];
    }

    private String timeStampStringToTimeStringConverter(MappingContext<Object, Object> context) {
        String[] splitTimeStamp = context.getSource().toString().split(" ");
        if (splitTimeStamp.length > 1) {
            return splitTimeStamp[1];
        }
        return null;
    }

    public WeatherData fromApiWeatherData(ApiWeatherData input) {
        return modelMapper.map(input, WeatherData.class);
    }
}
