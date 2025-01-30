package net.abid.journalApp.apiResponse;


import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;

// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */
@Getter
@Setter
public class WeatherResponse{
    private Current current;

    @Getter
    @Setter
    public class Current{
        private int temperature;
        private ArrayList<String> weather_descriptions;
        private int feelslike;
    }

}



