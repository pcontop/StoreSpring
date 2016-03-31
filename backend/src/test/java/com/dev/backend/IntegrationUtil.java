package com.dev.backend;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.http.MediaType;

import com.google.gson.Gson;
 
public class IntegrationUtil {
 
	private static Gson gson = new Gson();
	
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
 
    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        String json = gson.toJson(object);        
        return json.getBytes();
    }
}
