package com.yura0.insuranceService.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pretensions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pretension {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "pr_text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference("user-pretensions")
    @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "user_id")
    private User usr;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference("employee-pretensions")
    @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "user_id")
    private User employee;

    @Column(name = "answer")
    private String answer;

    @Enumerated(EnumType.STRING)
    private InsType insType;
}
