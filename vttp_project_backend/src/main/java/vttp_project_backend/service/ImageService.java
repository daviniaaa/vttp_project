package vttp_project_backend.service;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import vttp_project_backend.repo.ImageRepository;

@Service
public class ImageService {
    @Autowired ImageRepository imageRepo;

    @Value("${s3.bucket.endpoint}") private String bucketEndpoint;

    
    public String uploadPicture(MultipartFile file)	throws IOException {
		String key = "";
		String url = "";
		try {
            String contentType = file.getContentType();
            InputStream is = file.getInputStream();
            key = imageRepo.upload(contentType, is);
            JsonObject resp = Json.createObjectBuilder()
                .add("key", key)
                .build();
			System.out.println(resp);

		} finally {
			url = "https://vttp-davinia." + bucketEndpoint + "/" + key;
		}

		return url;
	}
    
}
