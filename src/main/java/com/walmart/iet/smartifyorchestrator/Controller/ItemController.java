package com.walmart.iet.smartifyorchestrator.Controller;

import com.walmart.iet.smartifyorchestrator.entity.Country;
import com.walmart.iet.smartifyorchestrator.entity.Item;
import com.walmart.iet.smartifyorchestrator.service.CountryService;
import com.walmart.iet.smartifyorchestrator.service.ItemService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {
  @Autowired
  private ItemService itemservice;

  @GetMapping("/items")
  public List<Item> getAllItems(){
    return itemservice.getItems();
  }

  @GetMapping("/itemList")
  public List<Item> getItemList(){
    return itemservice.getItems();
  }
}
