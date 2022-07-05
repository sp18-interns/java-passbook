package com.passbook.sparkeighteen.peristence.repository;

import com.passbook.sparkeighteen.peristence.entity.TransactionEntity;
import com.passbook.sparkeighteen.peristence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Transaction repository helps to communicate with database where in the data is retrieved / found.
 */
@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {

    /**
     * Find by user optional.
     * @param user function to perform on this particular user
     * @return the optional - return th user or null
     */
    <List>TransactionEntity findByUser(UserEntity user);
}