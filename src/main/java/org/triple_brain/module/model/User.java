package org.triple_brain.module.model;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.UUID;

/**
 * @author Vincent Blouin
 */
public class User {

    // this field is completely hidden and only used for SQL-relative persistence
    private Long internalId;

    private final String id = UUID.randomUUID().toString();
    private String username;
    private String email;
    private String passwordHash;
    private String salt;

    public String passwordHash() {
        return passwordHash;
    }

    public boolean hasPassword() {
        return passwordHash != null;
    }

    public boolean hasPassword(String password) {
        return passwordHash != null && encode(password).equals(passwordHash);
    }

    public User password(String password) {
        if (password != null) {

            salt = UUID.randomUUID().toString();
            passwordHash = encode(password);
        }
        return this;
    }

    public void cleanPassword() {
        passwordHash = null;
    }

    private String encode(String password) {
        if (password == null)
            throw new IllegalArgumentException("Missing password");

        // the salt is composed of the email and id. This way, two member objects with
        // the same email and same password can have two different hash
        return Base64.encodeBase64URLSafeString(DigestUtils.sha512(password + salt));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }

    @Override
    public String toString() {
        return email;
    }

    public String salt() {
        return salt;
    }

    public static User withUsernameAndEmail(String username, String email) {
        return new User(username, email);
    }

    private User(String username, String email) {
        this.username = username;
        this.email = email.toLowerCase().trim();
    }

    public String username(){
        return username;
    }

    public String email(){
        return email;
    }

    public String id(){
        return id;
    }
}