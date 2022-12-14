package com.battercodelab.basic.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import java.time.LocalDateTime;

@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MainCode extends Timestamped {

    @Id
    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column
    private String expl;

    @Column(columnDefinition = "boolean default false")
    private Boolean hasLevel;

    @Transient
    private String revType;

    @Transient
    private String modifiedName;

    @Transient
    private String modifiedIdx;

    @Transient
    private String modifiedId;

    public void update(String name, String expl){
        this.name = name;
        this.expl = expl;
    }

    public MainCode(Object[] object){
        this.name = object[0].toString();
        this.expl = object[1].toString();
        setModifiedAt(LocalDateTime.parse(object[2].toString()));
        this.revType = object[3].toString();
        setModified((Member) object[4]);
    }
}
