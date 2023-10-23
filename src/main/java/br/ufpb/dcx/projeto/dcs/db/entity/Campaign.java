package br.ufpb.dcx.projeto.dcs.db.entity;

import br.ufpb.dcx.projeto.dcs.db.enums.StatesTypes;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private StatesTypes state = StatesTypes.ACTIVE;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 1000, nullable = false)
    private String description;

    private double goal;

    private LocalDate deadline;

    @Builder.Default
    private double currentAmount = 0.0;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @Builder.Default
    private LocalDate createdAt = LocalDate.now();

}
