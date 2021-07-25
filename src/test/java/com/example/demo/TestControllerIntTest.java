package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestControllerIntTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void noResult() {
        ResponseEntity<Void> response = testRestTemplate.getForEntity("/no-result", Void.class);

        assertThat(response.getHeaders()).containsKey("x-date");
    }

    @Test
    void result() {
        ResponseEntity<String> response = testRestTemplate.getForEntity("/result", String.class);

        assertThat(response.getHeaders()).containsKey("x-date");
        assertThat(response.getBody()).isEqualTo("foo");
    }
}