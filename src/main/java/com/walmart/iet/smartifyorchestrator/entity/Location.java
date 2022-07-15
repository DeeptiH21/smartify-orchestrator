package com.walmart.iet.smartifyorchestrator.entity;

import javax.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Embeddable
public class Location {

  private String  aisle;
  private String section ;
  private int  shelfRow ;
  private float  xaxisLoc ;
  private float yaxisLoc ;


}
