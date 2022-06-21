package com.walmart.iet.smartifyorchestrator.dao;

import com.walmart.iet.smartifyorchestrator.entity.Country;
import com.walmart.iet.smartifyorchestrator.entity.Item;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
  //List<Item> findByName(String name);
}
