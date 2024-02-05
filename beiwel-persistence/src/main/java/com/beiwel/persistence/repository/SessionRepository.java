package com.beiwel.persistence.repository;

import com.beiwel.model.entity.SessionEntity;
import com.beiwel.model.filter.SessionFilter;
import com.beiwel.persistence.repository.specification.SessionSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends AbstractRepository<SessionEntity> {

    SessionEntity findById(long sessionId);

    SessionEntity findByGuid(String guid);

    default Page<SessionEntity> findAllByFilter(SessionFilter sessionFilter) {
        SessionSpecification sessionSpecification = new SessionSpecification(sessionFilter);

        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable =
                PageRequest.of(sessionFilter.getPage(), sessionFilter.getSize(), sort);

        return findAll(sessionSpecification, pageable);
    }

}
