package com.walmart.iet.smartifyorchestrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@RestController
public class SmartifyOrchestratorApplication {

  public static void main(String[] args) {
    SpringApplication.run(SmartifyOrchestratorApplication.class, args);
  }
  /*@GetMapping("/hello")
  public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
    return String.format("Hello %s!", name);
  }
  @GetMapping("/getLocations")
  public String fetchLocations(@RequestParam(value = "item", defaultValue = "item1") String item) {
    return String.format("Location %s!", "0,0");
  }*/
}
