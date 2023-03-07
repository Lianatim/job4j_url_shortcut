package ru.job4j.urlshortcut.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.repository.SiteRepository;

import java.util.List;

@Service
public class SiteService {
    private final SiteRepository siteRepository;
    private final PasswordEncoder encoder;

    public SiteService(SiteRepository siteRepository, PasswordEncoder encoder) {
        this.siteRepository = siteRepository;
        this.encoder = encoder;
    }

    public List<Site> findAll() {
        return siteRepository.findAll();
    }

    public void save(Site site) {
        siteRepository.save(site);
    }

}
