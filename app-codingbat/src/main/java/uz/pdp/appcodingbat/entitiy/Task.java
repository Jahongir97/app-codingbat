package uz.pdp.appcodingbat.entitiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private String solution;

    private String hint;

    @Column(nullable = false)
    private String method;

    @Column(nullable = false)
    private boolean hasStar;

    @ManyToOne
    private Category category;

}
