package com.walmart.iet.smartifyorchestrator.service;

import com.walmart.iet.smartifyorchestrator.dao.CountryRepository;
import com.walmart.iet.smartifyorchestrator.entity.Country;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService {
  @Autowired
  private CountryRepository countryRepository;

  public Country creteCountry(Country country){
    return countryRepository.save(country);
  }
  public List<Country> creteCountries(List<Country> countries){
    return countryRepository.saveAll(countries);
  }
  public Optional<Country> getCountryById(int id){
    return countryRepository.findById(id);
  }

  public List<Country> getCountries(){
    return countryRepository.findAll();
  }
  public String updateCountry(Country country){
    Optional<Country> existingCountry= countryRepository.findById(country.getId());
    if(existingCountry.isPresent()){
      Country existingCountryDetails=existingCountry.get();
      existingCountryDetails.setName(country.getName());
      countryRepository.save(existingCountryDetails);
      return "Record updated";
    }else{
      return "No match found to update";
    }
  }

  public String deleteCountryByid(int id){
    countryRepository.deleteById(id);
    return "Record Deleted " ;
  }
}
