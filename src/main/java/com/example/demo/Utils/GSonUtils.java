package com.example.demo.Utils;

import com.google.gson.Gson;

public class GSonUtils {

    private static class LoaderGSon {
        private static final Gson GSON_INSTANCE = new Gson();
    }

    public static Gson getGSonInstance() {
        return LoaderGSon.GSON_INSTANCE;
    }
}
