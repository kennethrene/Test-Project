package aws;

import com.amazonaws.auth.*;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class S3Bucket {
    private BasicSessionCredentials credentials;
    private AmazonS3 s3client;

    public S3Bucket() {
        this.config();
    }

    private void config() {
        this.credentials = new BasicSessionCredentials(
                "ASIAV6BDS6PTRH2EZXMH",
                "2hlWYMsTSbsH0Ki+23sJptE16XzRCxuuS7DKeKGj",
                "FwoGZXIvYXdzEAQaDNRho4cuQTrYpQTi1iL+AXL+FBjzbNlLtUZID9nI7l6rvCggkamuYtokjDkK3HBXBWhCJOBrbzuum7GrRaNlEurVmCcMP6C40pUMCXxfPris/BDKMlQhzKTWNQRXC7dxI7nrlbuTmThSXj18dq1cgj4GAQ5DPy9f5tmVghyHTObA8gYyabi4flH25G7qw/rAK0tlcPEJ1MBlobkbBc9e//ULG5YN0oEf07iDcIDHTvoFScMrjAoJc9miel6SEqXIC/YM+JDWQ5o1Ui3p5qnvCo0fdMH5lLpf+RTnz+nP0SMrM1YDbhlwAg30AdqQ0S+Gyx+2rTmhjQHyduM5SQVd3iKzny7blMbVhAlIcLjKKKftw5gGMiuaSE9d9mXul3ccMUxLdkMVD3WRfn+8OV8nFmH+UKsLBvuQtySL3xI6Bh/V"
        );

        this.s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(this.credentials))
                .withRegion(Regions.US_WEST_2)
                .build();
    }

    private void listBuckets() {
        this.s3client.listBuckets().forEach(b -> System.out.println(b.getName()));
    }

    private void listObjects(String bucketName) {
        this.s3client.listObjects(bucketName).getObjectSummaries().forEach(o -> System.out.println(o.getKey()));
    }

    public static void main(String ... args) {
        S3Bucket s3Bucket = new S3Bucket();

        //s3Bucket.listBuckets();
        s3Bucket.listObjects("abacus-experiment-api-service-int");
    }
}
