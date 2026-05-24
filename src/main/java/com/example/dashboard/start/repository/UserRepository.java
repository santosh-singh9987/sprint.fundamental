package com.example.dashboard.start.repository;

import com.example.dashboard.start.entity.UserEntity;
import com.example.dashboard.start.repository.projection.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("""
        SELECT
            u.id  AS id,
            u.name  AS name,
            u.email AS email,
            u.age   AS age,
            u.bloodGroupType As bloodGroupType
        FROM UserEntity u
    """
    )
    List<UserProjection> findAllUserDetailBy();


    Optional<UserEntity> findByEmail(String email);

    boolean existsByEmail(String email);
}
