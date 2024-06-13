import com.amazonaws.services.s3.model.*;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.SdkClientException;
import com.amazonaws.AmazonServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class S3Service {

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    private static final long PART_SIZE = 5 * 1024 * 1024; // 5 MB

    public String uploadFile(MultipartFile file) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        if (file.getSize() > 10 * 1024 * 1024) { // Giới hạn kích thước 10MB
            return multipartUpload(file, fileName);
        } else {
            // Upload tệp tin nhỏ theo cách thông thường
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());

            try (InputStream inputStream = file.getInputStream()) {
                amazonS3.putObject(new PutObjectRequest(bucketName, fileName, inputStream, metadata));
                return amazonS3.getUrl(bucketName, fileName).toString();
            } catch (AmazonServiceException e) {
                throw new IOException("Failed to upload file to S3", e);
            } catch (SdkClientException e) {
                throw new IOException("AWS SDK client error", e);
            }
        }
    }

    private String multipartUpload(MultipartFile file, String fileName) throws IOException {
        InitiateMultipartUploadRequest initRequest = new InitiateMultipartUploadRequest(bucketName, fileName);
        InitiateMultipartUploadResult initResponse = amazonS3.initiateMultipartUpload(initRequest);
        List<PartETag> partETags = new ArrayList<>();
        long contentLength = file.getSize();
        long partSize = PART_SIZE; 

        try {
            long filePosition = 0;
            for (int i = 1; filePosition < contentLength; i++) {
                partSize = Math.min(partSize, (contentLength - filePosition));
                
                try (InputStream inputStream = file.getInputStream()) {
                    inputStream.skip(filePosition);
                    UploadPartRequest uploadRequest = new UploadPartRequest()
                            .withBucketName(bucketName)
                            .withKey(fileName)
                            .withUploadId(initResponse.getUploadId())
                            .withPartNumber(i)
                            .withInputStream(inputStream)
                            .withPartSize(partSize);
                    
                    partETags.add(amazonS3.uploadPart(uploadRequest).getPartETag());
                }

                filePosition += partSize;
            }

            CompleteMultipartUploadRequest compRequest = new CompleteMultipartUploadRequest(
                    bucketName, fileName, initResponse.getUploadId(), partETags);

            amazonS3.completeMultipartUpload(compRequest);

            return amazonS3.getUrl(bucketName, fileName).toString();
        } catch (Exception e) {
            amazonS3.abortMultipartUpload(new AbortMultipartUploadRequest(
                    bucketName, fileName, initResponse.getUploadId()));
            throw new IOException("Failed to complete multipart upload", e);
        }
    }
}