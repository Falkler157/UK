package service;

import model.Log;
import model.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class RestLogCollector implements IRestLogCollector {

    @Value("${log.url}")
    private String LOG_URL;

    public RestLogCollector() {
    }

    public List<Log> getLog() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.TEXT_HTML));
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<Message> responseEntity = restTemplate.exchange(
                    new URI(LOG_URL),
                    HttpMethod.GET,
                    httpEntity,
                    Message.class);

            return Objects.requireNonNull(responseEntity.getBody()).getLogs();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
