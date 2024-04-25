package br.com.frwk.course.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class MapperUtil {

    private MapperUtil(){}

    private static final ObjectMapper mapper = createObjectMapper();

    private static final Logger LOGGER = LoggerFactory.getLogger(MapperUtil.class);

    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        return mapper.writeValueAsBytes(object);
    }

    public static <T extends Object> T toObject(byte[] json, Class<T> javaClass) {
        try {
            return mapper.readValue(json, javaClass);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }


}
