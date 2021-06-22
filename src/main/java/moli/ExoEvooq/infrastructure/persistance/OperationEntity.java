package moli.ExoEvooq.infrastructure.persistance;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "OPERATIONS")
@Setter
@Getter
public class OperationEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String operationType;

    @JoinColumn(name="account_id")
    @ManyToOne
    private AccountEntity account;

    private String montant;

    public OperationEntity() {
    }

    public OperationEntity(String id, String operationType, String montant) {
        this.id = id;
        this.operationType = operationType;
        this.montant = montant;
    }

    public OperationEntity(String operationType, String montant) {
        this.operationType = operationType;
        this.montant = montant;
    }
}
