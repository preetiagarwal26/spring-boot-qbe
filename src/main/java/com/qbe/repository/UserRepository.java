package com.qbe.repository;

import com.qbe.persistence.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, QueryByExampleExecutor<User>, JpaSpecificationExecutor<User> {
}
