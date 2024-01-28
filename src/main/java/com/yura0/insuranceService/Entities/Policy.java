package com.yura0.insuranceService.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Policy")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Policy {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "number")
    private String number;

    @OneToOne
    @JsonBackReference("user-policy")
    @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "user_id")
    private User usr;
}
