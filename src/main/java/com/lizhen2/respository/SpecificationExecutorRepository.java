package com.lizhen2.respository;

import com.lizhen2.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Administrator on 2018\6\15 0015.
 */
public interface SpecificationExecutorRepository extends CrudRepository<User, Integer>, JpaSpecificationExecutor<User> {
}
