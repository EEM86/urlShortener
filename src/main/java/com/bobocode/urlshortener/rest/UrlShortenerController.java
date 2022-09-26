package com.bobocode.urlshortener.rest;

import com.bobocode.urlshortener.dto.RequestDto;
import com.bobocode.urlshortener.service.UrlShortenerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/short")
public class UrlShortenerController {

  private final UrlShortenerService urlShortenerService;

  @PostMapping("/urls")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<?> shortUrl(@Validated @RequestBody RequestDto request) {
    var shortUrl = urlShortenerService.shorten(request);
    var location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(shortUrl).toUri();
    return ResponseEntity.created(location).build();
  }

  @GetMapping(path = "/{shortenedUrl}")
  public ResponseEntity<?> redirect(@PathVariable String shortenedUrl) {
    var originalUrl = urlShortenerService.redirect(shortenedUrl);
    return ResponseEntity
        .status(HttpStatus.MOVED_PERMANENTLY)
        .location(URI.create(originalUrl)).build();
  }

}
