package com.walmart.iet.smartifyorchestrator.Controller;

import com.walmart.iet.smartifyorchestrator.entity.Country;
import com.walmart.iet.smartifyorchestrator.service.CountryService;
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
public class CountryController {
  @Autowired
  private CountryService countryService;

  @PostMapping("/addCountry")
  public Country addCountry(@RequestBody Country country){
    return countryService.creteCountry(country);
  }
  @PostMapping("/addCountries")
  public List<Country> addCountries(List<Country> countryList){
    return countryService.creteCountries(countryList);
  }
  @GetMapping("/country/{id}")
  public Optional<Country> getCountrById(@PathVariable int id){
    return countryService.getCountryById(id);
  }

  @GetMapping("/countries")
  public List<Country> getAllCountries(){
    return countryService.getCountries();
  }

  @PutMapping("/updateCountry")
  public String updateCountry(Country country){
    return countryService.updateCountry(country);
  }

  @DeleteMapping("/delete/{id}")
  public String deleteCountry(@PathVariable int id){
    return countryService.deleteCountryByid(id);
  }
}
