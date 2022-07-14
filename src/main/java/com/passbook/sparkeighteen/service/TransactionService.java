package com.passbook.sparkeighteen.service;

import com.passbook.sparkeighteen.peristence.POJO.PaginatedResponse;
import com.passbook.sparkeighteen.peristence.POJO.TransactionFilter;
import com.passbook.sparkeighteen.peristence.POJO.TransactionRequest;
import com.passbook.sparkeighteen.peristence.POJO.TransactionResponse;
import com.passbook.sparkeighteen.peristence.entity.TransactionEntity;
import com.passbook.sparkeighteen.peristence.entity.UserEntity;
import com.passbook.sparkeighteen.peristence.repository.TransactionRepository;
import com.passbook.sparkeighteen.peristence.repository.UserRepository;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.convert.QueryByExamplePredicateBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;

/**
 * Transaction service helps to provide service to perform transactions
 */
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final UserRepository userRepository;

    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    /**
     * Create method to do the actual transaction contaning the business logic to add deposit and withdraw and return response according
     * @param userID  to make transaction for specific user.
     * @param request all fields required in transaction
     * @return the transaction response. (CREDIT OR DEBIT)
     * @throws Exception shows error messages.
     */
    public TransactionResponse transact(Integer userID, TransactionRequest request) throws Exception {

        Optional<UserEntity> optionalUser = userRepository.findById(userID);
        if (optionalUser.isEmpty())
            return TransactionResponse.builder().message("User not found. Please use a registered user").build();

        // TODO: Add deposit/withdraw logic and change below return accordingly

        return TransactionResponse.builder().build();
    }

    /**
     * This method(getZeroOrLastBalance) is to get balance as zero if its user first transaction or the closing balance of the latest transaction.
     * first assign the balance 0.
     * @param user to get the balance of that specific user.
     * @return updated balance after transaction done.
     */
    private Float getUpdatedBalance(UserEntity user) {
        Float balance = 0f;
        // TODO: Add getting the latest closing balance of the user
        return balance;
    }

    /**
     * @param userID used to get paginated response of transactions which is relevant to given userID.
     * @param filter which will used to filter the transactions based on given input fields which are amount, note, timeInterval.
     * @return paginated response of transactions.
     */
    public PaginatedResponse searchTransaction(Integer userID, TransactionFilter filter) {
        TransactionEntity searchEntity = TransactionEntity.builder()
                .note(filter.getNote())
                .build();

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withNullHandler(ExampleMatcher.NullHandler.IGNORE)
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase()
                .withIgnoreNullValues();

        Example<TransactionEntity> example = Example.of(searchEntity, matcher);
        Pageable page = PageRequest.of(0 , 100, Sort.by("time").ascending());
        Page<TransactionEntity> response = transactionRepository
                .findAll(getSpecFromDatesAndExample(filter, Example.of(searchEntity, matcher)), page);
        return PaginatedResponse.builder()
                .content(response.getContent())
                .totalPages(response.getTotalPages())
                .currentPage(0)
                .pageSize(100)
                .message("return list of transaction")
                .totalElements(response.getTotalElements())
                .build();

    }

    /**
     * @param filter which will used to filter the transactions based on given input fields which are amount, note, timeInterval.
     * @param example is object of TransactionEntity which is wrapped by Example.
     * @return transaction entity which is wrapped by specification.
     */
    public Specification<TransactionEntity> getSpecFromDatesAndExample(TransactionFilter filter , Example<TransactionEntity> example) {

        return (Specification<TransactionEntity>) (root, query, builder) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (filter.getTimeInterval()!= null && filter.getTimeInterval().getFrom() != null) {
                predicates.add(builder.greaterThan(root.get("time"), filter.getTimeInterval().getFrom()));
            }
            if (filter.getTimeInterval()!= null && filter.getTimeInterval().getTo() != null) {
                predicates.add(builder.lessThan(root.get("time"), filter.getTimeInterval().getTo()));
            }
            if (Objects.nonNull(filter.getAmount())) {
                predicates.add(builder.greaterThan(root.get("amount"), filter.getAmount()));
            }
            predicates.add(QueryByExamplePredicateBuilder.getPredicate(root, builder, example));
            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}