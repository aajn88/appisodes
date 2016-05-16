package com.movile.communication.clients.trakt.deserializers;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.movile.common.model.common.StandardMedia;
import com.movile.common.model.shows.Movie;
import com.movile.common.model.shows.Show;
import com.movile.common.model.shows.Trending;
import com.movile.communication.constants.MediaConstants;

import java.lang.reflect.Type;

/**
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class MediaDeserializer implements JsonDeserializer<Trending> {

    private final static Gson GSON = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

    /**
     * Gson invokes this call-back method during deserialization when it encounters a field of the
     * specified type. <p>In the implementation of this call-back method, you should consider
     * invoking {@link JsonDeserializationContext#deserialize(JsonElement, Type)} method to create
     * objects for any non-trivial field of the returned object. However, you should never invoke it
     * on the the same type passing {@code json} since that will cause an infinite loop (Gson will
     * call your call-back method again).
     *
     * @param json
     *         The Json data being deserialized
     * @param typeOfT
     *         The type of the Object to deserialize to
     * @param context
     *
     * @return a deserialized object of the specified type typeOfT which is a subclass of {@code T}
     *
     * @throws JsonParseException
     *         if json is not in the expected format of {@code typeofT}
     */
    @Override
    public Trending deserialize(JsonElement json, Type typeOfT,
                                JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String property = null;
        Class<? extends StandardMedia> clazz = null;
        if (jsonObject.has(MediaConstants.SHOW.getName())) {
            property = MediaConstants.SHOW.getName();
            clazz = Show.class;
        } else if (jsonObject.has(MediaConstants.MOVIE.getName())) {
            property = MediaConstants.MOVIE.getName();
            clazz = Movie.class;
        }

        Trending trending = new Trending();
        trending.setWatchers(jsonObject.get(Trending.WATCHERS).getAsInt());

        if (property != null) {
            JsonElement data = jsonObject.get(property);
            JsonObject jo = data.getAsJsonObject();
            jo.add(StandardMedia.LOCAL_ID, jo.get("ids").getAsJsonObject().get("trakt"));
            trending.setMedia((StandardMedia) context.deserialize(data, clazz));
        }

        return trending;
    }
}
