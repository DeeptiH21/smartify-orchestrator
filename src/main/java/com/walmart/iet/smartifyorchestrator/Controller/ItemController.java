package com.walmart.iet.smartifyorchestrator.Controller;

import com.walmart.iet.smartifyorchestrator.entity.Country;
import com.walmart.iet.smartifyorchestrator.entity.Item;
import com.walmart.iet.smartifyorchestrator.service.CountryService;
import com.walmart.iet.smartifyorchestrator.service.ItemService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
  public List<Item> getItemList(@RequestParam("items") String items){
    List<String> itemList=null;
    //System.out.println("items :: "+items);
    if(items!=null && items.length()>0)
      itemList = Arrays.asList(items.split(",", -1));
  return itemservice.findItemByDesc(itemList);
  }
  @RequestMapping(value = "/pin/image/{itemNumber}",
      method = RequestMethod.GET,
      produces = MediaType.IMAGE_JPEG_VALUE)
  public void getImage(@PathVariable("itemNumber") int itemNumber,
                       HttpServletResponse response) throws IOException {
    String imageNumber=String.valueOf(itemNumber);
    var imgFile = new ClassPathResource("images/"+imageNumber+".jpg");
    response.setContentType(MediaType.IMAGE_JPEG_VALUE);
    StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
  }

}
