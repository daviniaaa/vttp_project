package vttp_project_backend.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import vttp_project_backend.models.ExternalApi.DataObject;
import vttp_project_backend.models.ExternalApi.Payload;

@Service
public class ExternalApiService {
    
    @Value("${tih.api.key}") private String apiKey;

    public List<DataObject> getFromExternalApi(String searchValues) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", apiKey);
        HttpEntity<Void> entity = new HttpEntity<>( headers);

        String uri = UriComponentsBuilder
            .fromUriString("https://api.stb.gov.sg/content/events/v2/search")
            .queryParam("searchType", "keyword")
            .queryParam("searchValues", searchValues)
            .queryParam("limit", 50)
            .toUriString();

            RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Payload> resp = restTemplate.exchange(
            uri, HttpMethod.GET, entity, Payload.class);
        
        DataObject[] data = resp.getBody().data();
        System.out.println("service method dataobject[] size >> " + data.length);

        List<DataObject> doList = new LinkedList<>();
        for (DataObject d : data) {
            doList.add(d); 
        }
        
        return doList;
    }

    public Optional<DataObject[]> getExternalByUuid(String uuid) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", apiKey);
        HttpEntity<Void> entity = new HttpEntity<>( headers);

        String uri = UriComponentsBuilder
            .fromUriString("https://tih-api.stb.gov.sg/content/v1/event")
            .queryParam("uuid", uuid)
            .toUriString();

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Payload> resp = restTemplate.exchange(
            uri, HttpMethod.GET, entity, Payload.class);
        
        DataObject[] data = resp.getBody().data();
        // System.out.println(">> getByUuid size " + data.length);

        if (data.length == 0) {
            return Optional.empty();
        }

        return Optional.of(data);
    }

    public byte[] getImageByUuid(String uuid) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", apiKey);
        HttpEntity<Void> entity = new HttpEntity<>( headers);

        String uri = UriComponentsBuilder
            .fromUriString("https://api.stb.gov.sg/media/download/v2/")
            .path(uuid)
            // .queryParam("fileType", "Medium Thumbnail")
            .toUriString();
        
        uri += "?fileType=Small Thumbnail";
        // System.out.println(uri);

        RestTemplate restTemplate = new RestTemplate();

        try {ResponseEntity<byte[]> resp = restTemplate.exchange(
            uri, HttpMethod.GET, entity, byte[].class);
        
        return resp.getBody();} catch (Exception e) {}

        return null;
    }

    public byte[] getImageByUuidLarge(String uuid) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", apiKey);
        HttpEntity<Void> entity = new HttpEntity<>( headers);

        String uri = UriComponentsBuilder
            .fromUriString("https://api.stb.gov.sg/media/download/v2/")
            .path(uuid)
            // .queryParam("fileType", "Medium Thumbnail")
            .toUriString();
        
        uri += "?fileType=Default";
        // System.out.println(uri);

        RestTemplate restTemplate = new RestTemplate();

        try {ResponseEntity<byte[]> resp = restTemplate.exchange(
            uri, HttpMethod.GET, entity, byte[].class);
        
        return resp.getBody();} catch (Exception e) {}

        return null;
    }
}
