package codes.dirty.sns.user.common.interceptor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AccessLogger {

    private static final Logger logger = LoggerFactory.getLogger(AccessLogger.class);

    private static final ThreadLocal<Long> UNIQUE_TIME = ThreadLocal.withInitial(System::currentTimeMillis);
    private static final ThreadLocal<Integer> UNIQUE_NUM = ThreadLocal.withInitial(() -> 0);
    private static final String HOSTNAME;

    static {
        String hostName = "localhost";
        try {
            Process p = Runtime.getRuntime().exec("hostname");
            hostName = (new BufferedReader(new InputStreamReader(p.getInputStream()))).readLine();
        } catch (Exception ignored) {
        }
        HOSTNAME = hostName;
    }

    private final ThreadLocal<Map<String, Object>> threadLocalData = new ThreadLocal<>();

    public void init(HttpServletRequest request) {
        final Map<String, Object> data = new HashMap<>();
        data.put("URL", getRequestUrl(request));
        data.put("elapsed", System.currentTimeMillis());
        data.put("trId", createTrId());
        threadLocalData.set(data);
    }

    public void addAccessLog(String key, Object value) {
        final Map<String, Object> data = threadLocalData.get();
        data.put(key, value);
    }

    public void log(HttpServletResponse response) {
        try {
            final Map<String, Object> data = threadLocalData.get();
            final long startTime = (long) data.get("elapsed");
            data.put("elapsed", System.currentTimeMillis() - startTime);
            data.put("status", response.getStatus());

            logger.info("ACCESS_LOG: " + threadLocalData.get().toString());
        } finally {
            threadLocalData.remove();
        }
    }

    private String getRequestUrl(HttpServletRequest request) {
        if (request == null) {
            return "";
        } else {
            String url = request.getRequestURI();
            if (request.getQueryString() != null) {
                url = url + "?" + request.getQueryString();
            }
            return url;
        }
    }

    private String createTrId() {
        int trIdNum = UNIQUE_NUM.get() + 1;
        if (trIdNum < 0) {
            trIdNum = 0;
            UNIQUE_TIME.set(System.currentTimeMillis());
        }
        UNIQUE_NUM.set(trIdNum);
        return HOSTNAME + "_" + Thread.currentThread().getId() + "_" + UNIQUE_TIME.get() + "_" + trIdNum;
    }
}
