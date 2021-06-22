package moli.ExoEvooq.infrastructure.persistance;

import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.context.properties.ConstructorBinding;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "CLIENTS")
@Data
public class ClientEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private Set<AccountEntity> accounts = new HashSet<>();

    public ClientEntity() {
    }

    public ClientEntity(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ClientEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}
