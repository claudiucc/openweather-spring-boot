package com.assignment.spring.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

@Component
@Slf4j
public class JsonJUnitHelperIOClass {

    @Autowired
    JsonHelperClass jsonHelper;

    public <T> T getJsonObject(String filename, final TypeReference<T> valueTypeRef) {
        T list = null;
        try {
            File jsonPayloadFile = getResponsePayload(filename);
            list = jsonHelper.jsonToObject(readFile(jsonPayloadFile), valueTypeRef);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("Done loading data from file: {}", filename.toString());
        return list;
    }

    private File getResponsePayload(String resource) throws Exception {
        try {
            URL fileUri = getClass().getClassLoader().getResource(resource);
            if(fileUri == null) {
                throw new IllegalArgumentException("File not found: ".concat(resource).concat("!!!"));
            } else {
                log.info("{} was found!!!", fileUri.getPath());
            }

            File jsonPayload = new File(fileUri.getPath());
            if (!jsonPayload.exists()) {
                log.error("Error!!! File {} not found!!!", jsonPayload.getAbsolutePath());
                throw new Exception("Error!!! File ".concat(resource).concat(" not found!!!"));
            }
            return jsonPayload;
        } catch (Exception e) {
            throw e;
        }
    }

    private String readFile(File file) {

        FileInputStream fis = null;
        byte[] buffer = new byte[10];
        StringBuilder sb = new StringBuilder();

        try {
            fis = new FileInputStream(file);
            while (fis.read(buffer) != -1) {
                sb.append(new String(buffer));
                buffer = new byte[10];
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }

    public String getValueAsJsonString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
