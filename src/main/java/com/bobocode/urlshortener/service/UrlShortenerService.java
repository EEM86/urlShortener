package com.bobocode.urlshortener.service;

import com.bobocode.urlshortener.dto.RequestDto;

public interface UrlShortenerService {

  String shorten(RequestDto request);

  String redirect(String shortenedUrl);
}
