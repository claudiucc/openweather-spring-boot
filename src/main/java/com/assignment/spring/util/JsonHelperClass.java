package com.assignment.spring.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JsonHelperClass {

    @Autowired
    private ObjectMapper objectMapper;

    public <T> T jsonToObject(final String jsonContentString, final TypeReference<T> valueTypeRef) throws Exception {
        try {
            return objectMapper.readValue(jsonContentString, valueTypeRef);
        } catch (Exception e) {
            log.info("JsonHelperClass.jsonToObject: An exception of type: {} with message: {} occured while trying to parse json string {}!!!",
                    e.getClass().getCanonicalName(),
                    e.getMessage(),
                    jsonContentString);
            throw e;
        }
    }
}
