/*
 * Copyright Vincent Blouin under the GPL License version 3
 */

package guru.bubl.module.model.json;

import com.google.gson.Gson;
import guru.bubl.module.model.User;
import guru.bubl.module.model.suggestion.SuggestionPojo;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class UserJson {
    private static Gson gson = new Gson();

    public static final String
            ID = "uri",
            USER_NAME = "user_name",
            EMAIL = "email",
            PASSWORD = "password",
            PREFERRED_LOCALES = "preferred_locales";

    public static JSONObject toJson(User user){
        try{
            return new JSONObject()
                    .put(
                            ID, user.id()
                    )
                    .put(
                            USER_NAME, user.username()
                    ).put(
                            EMAIL, user.email()
                    )
                    .put(
                            PREFERRED_LOCALES, user.getPreferredLocalesAsString()
                    );
        }catch(JSONException e){
            throw new RuntimeException(e);
        }
    }
    public static User fromJson(String json){
        return gson.fromJson(json, User.class);
    }
}
