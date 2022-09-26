package com.bobocode.urlshortener.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Table(name = "shortened_urls")
@NoArgsConstructor
public class ShortenedUrl {
  @Id
  @GeneratedValue(generator = "custom_generator")
  @GenericGenerator(
      name = "custom_generator",
      strategy = "com.bobocode.urlshortener.generator.GenerateStringId")
  private String id;

  @Column(name = "original_url", nullable = false)
  @JsonProperty(value = "original_url")
  private String originalUrl;

  @Column(nullable = false)
  private String title;

  @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
  @JsonProperty(value = "created_at")
  private LocalDateTime createdAt;

  public ShortenedUrl(String originalUrl, String title) {
    this.originalUrl = originalUrl;
    this.title = title;
  }
}
