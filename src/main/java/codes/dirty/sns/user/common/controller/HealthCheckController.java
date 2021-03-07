package codes.dirty.sns.user.common.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> isRunning() {
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("status", "UP");

        return ResponseEntity.ok(resultMap);
    }
}
