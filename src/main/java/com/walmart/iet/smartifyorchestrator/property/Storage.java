package com.walmart.iet.smartifyorchestrator.property;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "storage")
public class Storage {
  private String uploadDir;
}
