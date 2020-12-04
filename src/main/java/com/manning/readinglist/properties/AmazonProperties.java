package com.manning.readinglist.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * AmazonProperties 专门读取amazon为前缀的属性值
 */

@Component
@ConfigurationProperties("amazon")
@Data
public class AmazonProperties {
  private String associateId;
}
