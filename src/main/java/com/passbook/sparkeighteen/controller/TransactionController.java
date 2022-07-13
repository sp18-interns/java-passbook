package com.passbook.sparkeighteen.controller;

import com.passbook.sparkeighteen.peristence.POJO.PaginatedResponse;
import com.passbook.sparkeighteen.peristence.POJO.TransactionFilter;
import com.passbook.sparkeighteen.peristence.POJO.TransactionRequest;
import com.passbook.sparkeighteen.peristence.POJO.TransactionResponse;
import com.passbook.sparkeighteen.service.TransactionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * To process incoming REST API requests for Transactions and give response from the request body and connecting through transaction service (business logic) is carried out here with the help of Transaction Controller
 */
@RequestMapping("/api/v1/user")
@RestController
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * To return response entity of the transaction performed in transaction service
     * @param userID  used to do the transaction of that particular user
     * @param request to get transaction request field for performing transaction
     * @return the response entity of the transaction
     * @throws Exception the exception / error message
     */
    @ApiOperation("User can transact")
    @PostMapping("/{userID}/transaction")
    public ResponseEntity<TransactionResponse> transact(@PathVariable Integer userID, @RequestBody final @Valid TransactionRequest request) throws Exception {
        return new ResponseEntity<>(transactionService.transact(userID, request), HttpStatus.OK);
    }

    @ApiOperation("User can search transaction")
    @GetMapping("/searchTransactions")
    public ResponseEntity<PaginatedResponse> searchTransaction(@PathVariable Integer userID,
                                                               @RequestBody final @Valid TransactionFilter filter)
            throws Exception {
        return new ResponseEntity<>(transactionService.searchTransaction(userID, filter), HttpStatus.OK);
    }

}