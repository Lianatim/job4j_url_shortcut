package ru.job4j.urlshortcut.repository;

import org.springframework.data.repository.CrudRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.job4j.urlshortcut.model.URL;

import java.util.List;
import java.util.Optional;

public interface UrlRepository extends CrudRepository<URL, Integer> {
    Optional<URL> findByCode(String code);

    @Query("update url u set u.count = u.count + 1 where u.id = :id")
    void updateUrlCounter(@Param("id") int id);

    List<URL> findAllBySiteId(int id);
}
