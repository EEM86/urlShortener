package com.bobocode.urlshortener.repository;

import com.bobocode.urlshortener.entity.ShortenedUrl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlShortenerRepository extends JpaRepository<ShortenedUrl, String> {

}
