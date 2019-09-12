package com.infotech.batch;

import com.infotech.batch.controller.UploadingController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class SpringBootBatchProjectApplication {

    public static void main(String[] args) throws IOException {

        new File(UploadingController.uploadingDir).mkdirs();
        SpringApplication.run(SpringBootBatchProjectApplication.class, args);
    }
}