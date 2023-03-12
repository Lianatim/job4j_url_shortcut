package ru.job4j.urlshortcut.service;

import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.dto.StatisticDTO;
import ru.job4j.urlshortcut.model.URL;
import ru.job4j.urlshortcut.repository.UrlRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UrlService {
    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public Optional<URL> findByCode(String code) {
        Optional<URL> url = urlRepository.findByCode(code);
        url.ifPresent(urlCode -> urlRepository.updateUrlCount(urlCode.getId()));
        return url;
    }

    public List<StatisticDTO> findAllStatisticBySiteId(int siteId) {
        List<StatisticDTO> statistic = new ArrayList<>();
        List<URL> urls = urlRepository.findAllBySiteId(siteId);
        urls.forEach(url -> statistic.add(new StatisticDTO(url.getUrl(), url.getCount())));
        return statistic;
    }

    public void save(URL url) {
        urlRepository.save(url);
    }
}
