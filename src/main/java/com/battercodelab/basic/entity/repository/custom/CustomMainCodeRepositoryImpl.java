package com.battercodelab.basic.entity.repository.custom;

import com.battercodelab.basic.entity.MainCode;
import jakarta.persistence.criteria.JoinType;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static org.hibernate.envers.query.AuditEntity.property;

public class CustomMainCodeRepositoryImpl extends QuerydslRepositorySupport implements CustomMainCodeRepository {
    public CustomMainCodeRepositoryImpl() {
        super(MainCode.class);
    }

    @Override
    public List<MainCode> findMainCodeHistory(String code) {
        AuditReader auditReader = AuditReaderFactory.get(getEntityManager());
        AuditQuery query = auditReader.createQuery()
                .forRevisionsOfEntity(MainCode.class, true, true)
                .add(property("code").eq(code))
                .addOrder(AuditEntity.revisionNumber().desc())
                .addProjection( property( "name") )
                .addProjection( property( "expl") )
                .addProjection( property( "modifiedAt") )
                .addProjection( property( "rev_type") )
                .traverseRelation("modified", JoinType.INNER)
                .addProjection( AuditEntity.selectEntity( false ) );
        List<Object[]> list = query.getResultList();

        return list.stream().map(e -> new MainCode(e)).toList();
    }
}
