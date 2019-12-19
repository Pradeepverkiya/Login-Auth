package com.pradeep.loginauth;

public class JsonRequest {
    String user;
    String secret;

    public JsonRequest(String user, String secret) {
        this.user = user;
        this.secret = secret;
    }
}
