package com.app.library.business.person;

import com.app.library.business.person.impl.PersonEntity;
import com.app.library.common.layer.IRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonRepository extends JpaRepository<PersonEntity, Long>, IRepository {
}
