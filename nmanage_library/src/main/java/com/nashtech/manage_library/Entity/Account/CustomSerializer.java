package com.nashtech.manage_library.Entity.Account;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CustomSerializer extends JsonSerializer<UserAccount> {

    @Override
    public void serialize(UserAccount arg0, JsonGenerator arg1, SerializerProvider arg2) throws IOException {
        arg1.writeStartObject();
        arg1.writeNumberField("id", arg0.getId());
        arg1.writeStringField("username", arg0.getUsername());
        arg1.writeStringField("password", arg0.getPassword());
        arg1.writeStringField("role", arg0.getRole());
        arg1.writeStringField("status", arg0.getStatus());
        arg1.writeStringField("token", arg0.getToken());
        arg1.writeObjectField("user", arg0.getUser());
        arg1.writeEndObject();
    }

    

}