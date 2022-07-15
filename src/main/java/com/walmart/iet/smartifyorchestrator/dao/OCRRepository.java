package com.walmart.iet.smartifyorchestrator.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.List;

public interface OCRRepository {
  public String postOcrImage(String path)
      throws URISyntaxException, IOException, InterruptedException;
  public List<String> formatResponsetoCSV(String inputRespnose) throws JsonProcessingException;
}
