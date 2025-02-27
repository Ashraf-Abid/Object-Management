package net.abid.journalApp.service;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import net.abid.journalApp.apiResponse.WeatherResponse;
import net.abid.journalApp.cache.AppCache;
import net.abid.journalApp.entity.ConfigJournalEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class WeatherService {
    @Value("${weather.api.key}")
    private String KEY;
    private  String API;
    @Autowired
    private AppCache appCache;
    @Autowired
    private RestTemplate restTemplate;
    public WeatherResponse getWeather(String city){

        API = appCache.cache.get("weather_api");
        String finalAPI = API.replace("<city>", city).replace("<apiKey>", KEY);
        ResponseEntity<WeatherResponse> exchange = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = exchange.getBody();
        return body;
    }
}
