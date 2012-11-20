package org.triple_brain.module.model.json;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.triple_brain.module.model.suggestion.Suggestion;

/*
* Copyright Mozilla Public License 1.1
*/
public class SuggestionJsonFields {
    public static final String TYPE_URI = "type_uri";
    public static final String DOMAIN_URI = "domain_uri";
    public static final String LABEL = "label";
    public static final String ORIGIN = "origin";

    public static JSONObject toJson(Suggestion suggestion) {
        try {
            return new JSONObject()
                    .put(TYPE_URI, suggestion.sameAsUri())
                    .put(DOMAIN_URI, suggestion.domainUri())
                    .put(LABEL, suggestion.label());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}