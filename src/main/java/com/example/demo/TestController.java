package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/no-result")
    public void noResult() {
        LOGGER.info("noResult()");
    }

    @GetMapping(value = "/result", produces = MediaType.TEXT_PLAIN_VALUE)
    String result() {
        LOGGER.info("result()");

        return "foo";
    }
}
