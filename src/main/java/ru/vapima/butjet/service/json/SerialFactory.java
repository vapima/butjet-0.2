package ru.vapima.butjet.service.json;


import com.google.gson.GsonBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SerialFactory {

    public Serial createJsonSer() {
        return new JsonSerial(getGsonBuilder());
    }

    public Deserial createJsonDeser() {
        return new JsonDeserial(getGsonBuilder());
    }

    private static GsonBuilder getGsonBuilder() {
        GsonBuilder gsonBuilder;
        gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
        return gsonBuilder;
    }

}
