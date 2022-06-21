package com.walmart.iet.smartifyorchestrator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.walmart.iet.smartifyorchestrator.entity.Item;
import com.walmart.iet.smartifyorchestrator.service.ItemService;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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

  @Bean
  CommandLineRunner runner(ItemService itemService){
    return args -> {
      // read JSON and load json
      ObjectMapper mapper = new ObjectMapper();
      TypeReference<List<Item>> typeReference = new TypeReference<List<Item>>(){};
      InputStream inputStream = TypeReference.class.getResourceAsStream("/json/items.json");
      try {
        List<Item> items = mapper.readValue(inputStream,typeReference);
        itemService.save(items);
        System.out.println("items Saved!");
      } catch (IOException e){
        System.out.println("Unable to save items: " + e.getMessage());
      }
    };
  }
}
