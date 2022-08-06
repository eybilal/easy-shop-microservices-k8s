package io.coodle.apigateway.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CircuitBreakerRestController {

    @GetMapping(value = "/countries/default")
    public Map<String, String> defaultCountries() {
        // In this case we can also use cache to return
        // the content of the last request
        Map<String, String> map = new HashMap<>();
        map.put("message", "Default countries");
        map.put("countries", "Germany, Luxembourg");

        return map;
    }
}
