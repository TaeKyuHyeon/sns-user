package codes.dirty.sns.user.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class JsonUtils {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    private JsonUtils() {
    }

    public static String toJson(Object value) {
        String string;
        try {
            string = objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            log.error("Mapping failed. [{}]", e.getMessage());
            throw new RuntimeException("Mapping failed.", e);
        }
        return string;
    }
}
