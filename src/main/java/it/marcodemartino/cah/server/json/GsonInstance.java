package it.marcodemartino.cah.server.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;

public class GsonInstance {

    private static Gson gson;

    public static Gson get() {
        if (gson == null) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.disableHtmlEscaping();
            gsonBuilder.excludeFieldsWithModifiers(Modifier.TRANSIENT);
            gson = gsonBuilder.create();
        }
        return gson;
    }
}