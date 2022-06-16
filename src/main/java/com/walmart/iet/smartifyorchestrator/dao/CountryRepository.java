package com.walmart.iet.smartifyorchestrator.dao;

import com.walmart.iet.smartifyorchestrator.entity.Country;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {
  List<Country> findByName(String name);
}
