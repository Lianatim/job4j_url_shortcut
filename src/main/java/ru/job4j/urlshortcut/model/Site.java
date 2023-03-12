package ru.job4j.urlshortcut.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "site")
public class Site {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Site name must be not empty")
    private String name;
    private String login;
    private String password;
    private boolean enabled;
    @ManyToOne
    @JoinColumn(name = "authority_id")
    private Authority authority;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "site")
    private Set<URL> urlList = new HashSet<>();

    public void addUrlList(URL url) {
        this.urlList.add(url);
        url.setSite(this);
    }
}
