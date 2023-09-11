package vttp_project_backend.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import vttp_project_backend.models.ExternalApi.Payload;

@Controller
public class ExternalApiController {
    
    @Value("${tih.api.key}") private String apiKey;
    
    @GetMapping("/api/external")
    public ResponseEntity<Payload> externalApi(@RequestParam String searchValues) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", apiKey);
        HttpEntity<Void> entity = new HttpEntity<>( headers);

        String uri = UriComponentsBuilder
            .fromUriString("https://api.stb.gov.sg/content/events/v2/search")
            .queryParam("searchType", "keyword")
            .queryParam("searchValues", searchValues)
            .toUriString();

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<Payload> resp = restTemplate.exchange(
                uri, HttpMethod.GET, entity, Payload.class);
        

        return resp;
    }
}
