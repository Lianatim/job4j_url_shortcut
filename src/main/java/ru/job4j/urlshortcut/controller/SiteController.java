package ru.job4j.urlshortcut.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.urlshortcut.dto.SiteDto;
import ru.job4j.urlshortcut.exception.ResourceNotFoundException;
import ru.job4j.urlshortcut.exception.UserAlreadyExistsException;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.model.URL;
import ru.job4j.urlshortcut.service.AuthorityService;
import ru.job4j.urlshortcut.service.SiteService;

import javax.validation.Valid;
import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
public class SiteController {

    private final SiteService siteService;
    private final PasswordEncoder encoder;
    private final AuthorityService authorityService;

    public SiteController(PasswordEncoder encoder, SiteService siteService, AuthorityService authorityService) {
        this.encoder = encoder;
        this.siteService = siteService;
        this.authorityService = authorityService;
    }

    @PostMapping("/reg")
    public ResponseEntity<SiteDto> regSave(@Valid @RequestBody Site site) {
        site.setEnabled(true);
        site.setAuthority(authorityService.findByAuthority("ROLE_USER"));
        String login = generateRandomString();
        String password = generateRandomString();
        site.setLogin(login);
        site.setPassword(encoder.encode(password));
        try {
            siteService.save(site);
        } catch (DataIntegrityViolationException e) {
            throw new UserAlreadyExistsException("Person with this id already exists");
        }
        return new ResponseEntity<>(new SiteDto(true, site.getLogin(), password), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Site>> findAll() {
        List<Site> sites = siteService.findAll();
        return sites.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(sites, HttpStatus.OK);
    }

    private String generateRandomString() {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        return IntStream.range(0, 10)
                .map(i -> random.nextInt(chars.length()))
                .mapToObj(randomInd -> String.valueOf(chars.charAt(randomInd)))
                .collect(Collectors.joining());
    }

    @PostMapping("/convert")
    public ResponseEntity<String> convert(@Valid @RequestBody URL url) {
        String code = generateRandomString();
        url.setCode(code);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Site site = siteService.findByLogin(auth.getName()).orElseThrow(() -> new ResourceNotFoundException("User not exist with login: " + auth.getName()));
        site.addUrlList(url);
        String json = "{"
                + "\"code\" : \"" + code + "\""
                + "}";
        return new ResponseEntity<>(json, HttpStatus.CREATED);
    }
}
