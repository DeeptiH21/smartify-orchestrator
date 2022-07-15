package com.walmart.iet.smartifyorchestrator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.walmart.iet.smartifyorchestrator.dao.OCRRepository;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.http.Header;
import org.json.JSONObject;


@Service
public class OCRService implements OCRRepository {
  private String API_KEY ="15fa8b14bf3d47619efb109ad17dec1d";

  @Override
  public String postOcrImage(String path)
      throws URISyntaxException, IOException, InterruptedException {
    String jsonString=null;
    String EXTERNAL_API_ENDPOINT = "https://samrtifycognitiveservices.cognitiveservices.azure.com";
    String uriBase = EXTERNAL_API_ENDPOINT + "/vision/v3.2/read/analyze";
    String imageToAnalyze =path;


    CloseableHttpClient httpTextClient = HttpClientBuilder.create().build();
    CloseableHttpClient httpResultClient = HttpClientBuilder.create().build();;
    URIBuilder builder = new URIBuilder(uriBase);
    URI uri = builder.build();
    HttpPost request = new HttpPost(uri);

    // Request headers.
    request.setHeader("Content-Type", "application/json");
    request.setHeader("Ocp-Apim-Subscription-Key", API_KEY);

    // Request body.
    StringEntity requestEntity =
        new StringEntity("{\"url\":\"" + imageToAnalyze + "\"}");
    request.setEntity(requestEntity);
    HttpResponse response = httpTextClient.execute(request);
    if (response.getStatusLine().getStatusCode() != 202) {
      // Format and display the JSON error message.
      HttpEntity entity = response.getEntity();
      jsonString = EntityUtils.toString(entity);
      JSONObject json = new JSONObject(jsonString);
      System.out.println("Error:\n");
      System.out.println(json.toString(2));
      return "";
    }

    String operationLocation = null;

    Header[] responseHeaders = response.getAllHeaders();
    for (Header header : responseHeaders) {
      if (header.getName().equals("Operation-Location")) {
        operationLocation = header.getValue();
        break;
      }
    }

    if (operationLocation == null) {
      System.out.println("\nError retrieving Operation-Location.\nExiting.");
      System.exit(1);
    }

    System.out.println("\nText submitted.\n" +
        "Waiting 2 seconds to retrieve the recognized text.\n");
    Thread.sleep(2000);

    // Call the second REST API method and get the response.
    HttpGet resultRequest = new HttpGet(operationLocation);
    resultRequest.setHeader("Ocp-Apim-Subscription-Key", API_KEY);

    HttpResponse resultResponse = httpResultClient.execute(resultRequest);
    HttpEntity responseEntity = resultResponse.getEntity();

    if (responseEntity != null) {
      // Format and display the JSON response.
     jsonString = EntityUtils.toString(responseEntity);
      JSONObject json = new JSONObject(jsonString);
     // System.out.println("Text recognition result response: \n");
     // System.out.println(json.toString(2));
    }

       return jsonString;
  }



  @Override
  public List<String> formatResponsetoCSV(String inputResponse) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    List<String> shoppingListCSV= new ArrayList<String>();
    JsonNode node = objectMapper.readValue(inputResponse, JsonNode.class);
    JsonNode readResultsNodes = node.get("analyzeResult").get("readResults");
    for(JsonNode readResult : readResultsNodes){
      JsonNode lineNodes=readResult.get("lines");
      for(JsonNode line : lineNodes){
        String textNode=line.get("text").asText();
        if(!textNode.isEmpty()){
          shoppingListCSV.add(textNode);
        }
      }
      return shoppingListCSV;
    }

    return null;
  }
  }
