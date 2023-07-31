package project.departmentservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope // This annotation is used to refresh the properties without restarting the application.
@RestController
public class MessageController {

    @Value("${spring.boot.message}")
    private String message;

    @GetMapping("/message")
    public String message() {
        return message;
    }
}
