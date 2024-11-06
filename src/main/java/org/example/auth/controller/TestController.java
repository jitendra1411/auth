package org.example.auth.controller;

import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.marker.LogstashMarker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static net.logstash.logback.marker.Markers.append;

/**
 * @author Jitendra (Jeetu)
 */

@Slf4j
@RestController
@RequestMapping("v1/test")
public class TestController {

    @GetMapping("{testString}")
    public String test(@PathVariable("testString") String testString){
        LogstashMarker marker = append("method", "test")
                .and(append("testString", testString));
        log.info(marker, "test request received");
        return testString;
    }
}
