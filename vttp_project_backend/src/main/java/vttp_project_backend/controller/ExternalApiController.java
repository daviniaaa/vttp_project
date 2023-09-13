package vttp_project_backend.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import vttp_project_backend.models.ExternalApi.DataObject;
import vttp_project_backend.models.ExternalApi.EventDetailListObject;
import vttp_project_backend.models.ExternalApi.Payload;
import vttp_project_backend.service.ExternalApiService;

@Controller
@RequestMapping("/api")
public class ExternalApiController {
    @Autowired ExternalApiService extApiService;
    
    @GetMapping(path = "/external", produces = MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<List<DataObject>> externalApi(@RequestParam String searchValues) {
    public ResponseEntity<List<DataObject>> externalApi() {
        List<DataObject> list = extApiService.getFromExternalApi("a");

        if (list.size() == 0)
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));

        return ResponseEntity.ok(list);
    }

    @GetMapping(path = "/external/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<List<DataObject>> externalApi(@RequestParam String searchValues) {
    public ResponseEntity<DataObject[]> externalApiByUuid(@PathVariable String uuid) {
        Optional<DataObject[]> opt = extApiService.getExternalByUuid(uuid);

        if (opt.isEmpty())
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));

        return ResponseEntity.ok(opt.get());
    }


    @GetMapping(path = "/external/image/{uuid}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<Resource> getImageByUuid(@PathVariable String uuid) throws IOException {
        byte[] image = extApiService.getImageByUuid(uuid);
        

        final ByteArrayResource inputStream = new ByteArrayResource(image);
            
            return ResponseEntity
                .status(HttpStatus.OK)
                .contentLength(inputStream.contentLength())
                .body(inputStream);
    }

    @GetMapping(path = "/external/imageLarge/{uuid}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<Resource> getImageByUuidLarge(@PathVariable String uuid) throws IOException {
        byte[] image = extApiService.getImageByUuidLarge(uuid);
        
        final ByteArrayResource inputStream = new ByteArrayResource(image);
            
            return ResponseEntity
                .status(HttpStatus.OK)
                .contentLength(inputStream.contentLength())
                .body(inputStream);
    }
}
