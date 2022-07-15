package com.walmart.iet.smartifyorchestrator.Controller;

import com.walmart.iet.smartifyorchestrator.entity.Item;
import com.walmart.iet.smartifyorchestrator.service.ItemService;
import com.walmart.iet.smartifyorchestrator.service.OCRService;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
  @Autowired
  private OCRService ocrService;

  @GetMapping("/items")
  public List<Item> getAllItems() {
    return itemservice.getItems();
  }

  @GetMapping("/itemList")
  public List<Item> getItemList(@RequestParam("items") String items) {
    List<String> itemList = null;
    //System.out.println("items :: "+items);
    if (items != null && items.length() > 0)
      itemList = Arrays.asList(items.split(",", -1));
    return itemservice.findItemByDesc(itemList);
  }

  @RequestMapping(value = "/pin/image/{itemNumber}",
      method = RequestMethod.GET,
      produces = MediaType.IMAGE_JPEG_VALUE)
  public void getImage(@PathVariable("itemNumber") int itemNumber,
                       HttpServletResponse response) throws IOException {
    String imageNumber = String.valueOf(itemNumber);
    var imgFile = new ClassPathResource("images/" + imageNumber + ".png");
    response.setContentType(MediaType.IMAGE_JPEG_VALUE);
    StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
  }

  @PutMapping("/update")
  public String updateItem(Item item) {
    return itemservice.updateItem(item);
  }

  @DeleteMapping("/delete/{itemNumber}")
  public String deleteCountry(@PathVariable int itemNumber) {
    return itemservice.deleteItem(itemNumber);
  }

  @PostMapping("/addItem")
  public Item addItem(@RequestBody Item item) {
    return itemservice.createItem(item);
  }

  @PostMapping(value = "/ocrImage")
  public  List<Item> ocrImage(@RequestParam("file") String fileName)
      throws URISyntaxException, InterruptedException, IOException {
    System.out.println("fileName :: "+fileName);
    // passing the filepath to the service method
    String response = ocrService.postOcrImage(fileName);
    List<String> itemList=ocrService.formatResponsetoCSV(response);
    return itemservice.findItemByDesc(itemList);
  }
}


