package uz.napa.clinic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.annotation.security.DenyAll;
import javax.persistence.*;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "section")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Type(type = "jsonb")
    @Column(name = "title", columnDefinition = "jsonb")
    private HashMap<String, String> title;

    @Type(type = "jsonb")
    @Column(name = "description", columnDefinition = "jsonb")
    private HashMap<String, String> description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "section")
    @JsonIgnore
    private List<User> users;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "section")
    @JsonIgnore
    private List<Application> applications;
}
