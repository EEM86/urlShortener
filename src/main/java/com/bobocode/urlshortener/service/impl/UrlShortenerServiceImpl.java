package com.bobocode.urlshortener.service.impl;

import com.bobocode.urlshortener.dto.RequestDto;
import com.bobocode.urlshortener.entity.ShortenedUrl;
import com.bobocode.urlshortener.repository.UrlShortenerRepository;
import com.bobocode.urlshortener.service.UrlShortenerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UrlShortenerServiceImpl implements UrlShortenerService {

  private final UrlShortenerRepository repository;

  @Override
  @Retryable
  @Transactional
  public String shorten(RequestDto request) {
    var savedEntity = repository.save(new ShortenedUrl(request.url(), request.title()));
    return savedEntity.getId();
  }

  @Override
  @Cacheable("urls")
  public String redirect(String shortenedUrl) {
    var original = repository.findById(shortenedUrl).orElseThrow();
    return original.getOriginalUrl();
  }
}
