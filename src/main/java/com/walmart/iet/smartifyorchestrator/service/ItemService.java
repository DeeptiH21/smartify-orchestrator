package com.walmart.iet.smartifyorchestrator.service;

import com.walmart.iet.smartifyorchestrator.dao.ItemRepository;
import com.walmart.iet.smartifyorchestrator.entity.Country;
import com.walmart.iet.smartifyorchestrator.entity.Item;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
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

  public List<Item> findItemByDesc(List<String> items) {
    List<Item> finalItemList = new ArrayList<Item>();
    if(items!=null && items.size()>0) {
       ListIterator<String> itemsIterator = items.listIterator();
       while (itemsIterator.hasNext()) {
         String requestedItemDesc = itemsIterator.next();
         //System.out.println("requestedItemDesc::"+requestedItemDesc + items.size());
         Item searchedItem = itemRepository.findByItemDescContainingIgnoreCase(requestedItemDesc);
          //System.out.println("searchedItem"+searchedItem);
         finalItemList.add(searchedItem);
       }
     }
   return finalItemList;
  }

  public String updateItem(Item item){
    Optional<Item> existingItem= itemRepository.findById(item.getId());
    if(existingItem.isPresent()){
      Item existingItemDetails=existingItem.get();
      BeanUtils.copyProperties(item, existingItem);
      itemRepository.save(existingItemDetails);
      return "Record updated";
    }else{
      return "No match found to update";
    }
  }

  public String deleteItem(int itemNumber){
    itemRepository.deleteByItemNumber(itemNumber);
    return "Record Deleted " ;
  }

  public Item createItem(Item item){
    return itemRepository.save(item);
  }

}
