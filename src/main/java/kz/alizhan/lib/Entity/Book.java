package kz.alizhan.lib.Entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "book")
@javax.persistence.Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer yearOfIssue;
    private String author;
    @OneToOne
    @JoinColumn(table = "location", name = "id")
    private Location location;

}
