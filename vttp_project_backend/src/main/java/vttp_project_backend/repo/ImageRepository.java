package vttp_project_backend.repo;

import java.io.InputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Repository
public class ImageRepository {
    @Autowired private AmazonS3 s3;

	public String upload(String contentType, InputStream is) {
		String key = "miniproject/%s".formatted(
			UUID.randomUUID().toString().substring(0, 8));
        
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType(contentType);

		PutObjectRequest putReq = new PutObjectRequest("vttp-davinia", key, is, metadata);
		putReq = putReq.withCannedAcl(CannedAccessControlList.PublicRead);

		s3.putObject(putReq);

		return key;
	}
}
