package com.battercodelab.basic.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;


@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Entity
public class SubCode extends Timestamped {

    @Id
    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column
    private String expl;

    @ManyToOne(fetch = FetchType.LAZY)
    private MainCode mainCode;

    @Column
    private Integer level;
}
