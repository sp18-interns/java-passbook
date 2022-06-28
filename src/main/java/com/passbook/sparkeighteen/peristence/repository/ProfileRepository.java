package com.passbook.sparkeighteen.peristence.repository;

import com.passbook.sparkeighteen.peristence.entity.ProfileEntity;
import com.passbook.sparkeighteen.peristence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Integer> {
    Optional<ProfileEntity> findByUser(UserEntity user);

}
