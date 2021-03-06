package com.walmart.iet.smartifyorchestrator.entity;


import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
public class Item {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String itemDesc;
  private int itemNumber ;
  private int deptNumber;
  private String  deptDesc ;
  private float  price;
  @Embedded
  private Location location;
  private String itemImageURL;
  private int onHand ;

}