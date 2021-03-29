package com.kg.intern_assignment.Room;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class LanguageConverters {


    static Gson gson = new Gson();

    @TypeConverter
    public static List<LanguageEntity> stringToList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<LanguageEntity>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String listToString(List<LanguageEntity> someObjects) {
        return gson.toJson(someObjects);
    }
}
