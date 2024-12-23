package ar.edu.utn.frc.tup.lc.iv.domain.dummy.entities;

import ar.edu.utn.frc.tup.lc.iv.domain.dummy.models.DummyEnum;
import ar.edu.utn.frc.tup.lc.iv.domain.user.entities.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dummys")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DummyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String dummy;

    @Enumerated(EnumType.STRING)
    private DummyEnum dummyEnum;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
  