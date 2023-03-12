package ru.job4j.urlshortcut.service;

import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.model.Authority;
import ru.job4j.urlshortcut.repository.AuthorityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorityService {

    private final AuthorityRepository authorityRepository;

    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public void save(Authority authority) {
        authorityRepository.save(authority);
    }


    public List<Authority> findAll() {
        return authorityRepository.findAll();
    }

    public Optional<Authority> findById(int id) {
        return authorityRepository.findById(id);
    }

    public Authority findByAuthority(String authority) {
        return authorityRepository.findByAuthority(authority);
    }

}
