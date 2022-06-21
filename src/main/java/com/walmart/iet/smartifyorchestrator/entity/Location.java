package com.walmart.iet.smartifyorchestrator.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Embeddable
public class Location {

  private String  aisle;
  private String section ;
  private int  shelfRow ;
  private int  xaxisLoc ;
  private int yaxisLoc ;


}
