package com.nikhil.journal_app.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WeatherResponse {
    private Current current;

    @Data
    public class Current{

        @JsonProperty("temperature")
        private int temperature;

        @JsonProperty("feelslike")
        private int feelsLike;

        @JsonProperty("is_day")
        private String isDay;

    }
}