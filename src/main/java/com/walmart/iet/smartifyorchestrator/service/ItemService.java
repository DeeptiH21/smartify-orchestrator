package com.walmart.iet.smartifyorchestrator.service;

import com.walmart.iet.smartifyorchestrator.dao.CountryRepository;
import com.walmart.iet.smartifyorchestrator.dao.ItemRepository;
import com.walmart.iet.smartifyorchestrator.entity.Country;
import com.walmart.iet.smartifyorchestrator.entity.Item;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
  @Autowired
  private ItemRepository itemRepository;

   public List<Item> getItems(){
    return itemRepository.findAll();
  }

  public List<Item> getItemList(){
    return itemRepository.findAll();
  }

  public Item save(Item item) {
    return itemRepository.save(item);
  }

  public void save(List<Item> items) {
    itemRepository.saveAll(items);
  }
}
