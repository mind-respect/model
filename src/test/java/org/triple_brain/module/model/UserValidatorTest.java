/*
 * Copyright Vincent Blouin under the Mozilla Public License 1.1
 */

package org.triple_brain.module.model;

import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

import java.util.Map;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.triple_brain.module.model.json.UserJson.*;
import static org.triple_brain.module.model.validator.UserValidator.*;

public class UserValidatorTest {

    @Test
    public void email_is_mandatory() throws Exception{
        assertTrue(
                validationWithUserReturnsFieldWithMessage(
                        validUserButWithFieldValue(EMAIL, ""),
                        EMAIL,
                        MANDATORY_EMAIL)
        );
    }

    @Test
    public void email_has_to_be_valid() throws Exception{
        assertTrue(
                validationWithUserReturnsFieldWithMessage(
                        validUserButWithFieldValue(
                                EMAIL,
                                "roger.lamothe$example.org"
                        ),
                        EMAIL,
                        INVALID_EMAIL)
        );
    }

    @Test
    public void password_is_mandatory() throws Exception{
        assertTrue(
                validationWithUserReturnsFieldWithMessage(
                        validUserButWithFieldValue(
                                PASSWORD,
                                ""
                        ),
                        PASSWORD,
                        MANDATORY_PASSWORD)
        );
    }

    @Test
    public void password_cant_be_too_short() throws Exception{
        assertTrue(
                validationWithUserReturnsFieldWithMessage(
                        validUser()
                                .put(PASSWORD, "pass"),
                        PASSWORD,
                        PASSWORD_TOO_SHORT)
        );
    }

    private boolean validationWithUserReturnsFieldWithMessage(JSONObject user, String field, String message){
        Map<String, String> errors ;
        errors = errorsForUserAsJson(user);
        assertThat(errors.get(field), is(notNullValue()));
        assertThat(errors.get(field), is(message));
        return true;
    }

    private JSONObject validUserButWithFieldValue(String fieldName, String value) throws Exception{
        return validUser().put(fieldName, value);
    }
    
    private JSONObject validUser()throws Exception{
        JSONObject user = new JSONObject();
        user.put(EMAIL, "generated_email@example.org");
        user.put(PASSWORD, "generated password");
        return user;
    }
}
