package com.imdach.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * Created By Arthur Zhang at 2022/2/15
 */
@RestController
public class MyController {
    @GetMapping("/")
    public String index() throws InterruptedException {
//        TimeUnit.SECONDS.sleep(100);
        return "hello";
    }

    @GetMapping("/route2")
    public String delay() {
        return "hello";
    }

    @GetMapping("/hello")
    public String hello() {
        MyController testClass = new MyController();
        ClassLoader classLoader = testClass.getClass().getClassLoader();
        URL[] urls = ((URLClassLoader) classLoader).getURLs();
        for(URL url :urls) {
            System.out.println(url);
        }
        return "hello";
    }

    @GetMapping("/hello/world")
    public String helloworld(HttpServletRequest request) {

        return "hello world, url: "+ request.getRequestURI();
    }

    @GetMapping("/api/v2/subCourses/{subCourseId}/info")
    public String foo_v2(@PathVariable(value = "subCourseId") String subCourseId) {
        return "hello";
    }

    @GetMapping("/easicare/v1/subCourses/{subCourseId}/comments/create")
    public String foo_easicare(@PathVariable(value = "subCourseId") String subCourseId) {
        return "hello";
    }

    @GetMapping("/api/v1/subCourses/{subCourseId}/info")
    public String foo(@PathVariable(value = "subCourseId") String subCourseId) {
        return "hello";
    }

    @GetMapping("/test.json")
    public Map testApi(@RequestParam(name = "id") String id, APIContext apiContext) {
        System.out.println(apiContext);
        Map<String, String> map = new HashMap<String, String>() {
            {
                put("id", id);
                put("ts", "" + System.currentTimeMillis());
            }
        };
        return map;
    }
}
