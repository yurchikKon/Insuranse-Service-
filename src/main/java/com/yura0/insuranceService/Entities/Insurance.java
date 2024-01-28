package com.yura0.insuranceService.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "insurance")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Insurance {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private InsType insType;

    @Column(name = "cost")
    private int cost;

    @ManyToOne(fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "user_id")
    @JsonBackReference("usr-insurance")
    private User usr;
}
