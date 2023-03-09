package ru.job4j.urlshortcut.service;

import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.model.URL;
import ru.job4j.urlshortcut.repository.UrlRepository;

import java.util.Optional;

@Service
public class UrlService {
    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public Optional<URL> findByCode(String code) {
        Optional<URL> url = urlRepository.findByCode(code);
        url.ifPresent(urlCode -> urlRepository.updateUrlCounter(urlCode.getId()));
        return url;
    }
}
