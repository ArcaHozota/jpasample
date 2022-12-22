package jp.co.sony.ppog.utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * The common class of JSON-data response.
 *
 * @author Administrator
 */
@Data
public class RestDto {

    /**
     * status code
     */
    private Integer code;

    /**
     * the message of status
     */
    private String message;

    /**
     * data returned to browsers
     */
    private final Map<String, Object> extend = new HashMap<>();

    /**
     * retrieve successfully
     *
     * @return result including data
     */
    public static RestDto success() {
        final RestDto result = new RestDto();
        result.setCode(200);
        result.setMessage("Retrieve success.");
        return result;
    }

    /**
     * retrieve failed
     *
     * @return result including error message
     */
    public static RestDto failure() {
        final RestDto result = new RestDto();
        result.setCode(400);
        result.setMessage("Retrieve failed.");
        return result;
    }

    /**
     * no args constructor
     */
    public RestDto() {
    }

    /**
     * add values with messages
     *
     * @param key   the name pattern of value
     * @param value value
     * @return RestMsg
     */
    public RestDto add(final String key, final Object value) {
        this.getExtend().put(key, value);
        return this;
    }
}
