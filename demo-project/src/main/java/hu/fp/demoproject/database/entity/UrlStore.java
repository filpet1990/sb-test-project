package hu.fp.demoproject.database.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "URL_STORE")
@Getter
@Setter
@NoArgsConstructor
public class UrlStore {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Id_Seq")
    @SequenceGenerator(name="Id_Seq", sequenceName ="SEQ_URL_STORE", allocationSize = 1)
    @Column(name = "URL_STORE_ID")
    private Long id;

    @Column(name = "ORIGINAL", nullable = false, unique = true)
    private String original;

    @Column(name = "SHORTENED", unique = true)
    private String shortened;

    public UrlStore(String original) {
        this.original = original;
    }
}
