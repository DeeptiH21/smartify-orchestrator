package com.walmart.iet.smartifyorchestrator.dao;

import com.walmart.iet.smartifyorchestrator.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
  Item findByItemDescContainingIgnoreCase(String itemDesc);
  void deleteByItemNumber(int itemNumebr);

}
