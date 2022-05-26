package com.imdach.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Demo1Application {

    public static void main(String[] args) throws IOException {
        List<FileInputStream> root = new ArrayList<>();
        for (int i = 0; i < 800; i++) {
            File f = new File("/tmp/test" + i + ".txt");
            if (!f.exists()) f.createNewFile();
            FileInputStream fileInputStream = new FileInputStream(f);
            root.add(fileInputStream);
        }
        SpringApplication.run(Demo1Application.class, args);
    }

}
