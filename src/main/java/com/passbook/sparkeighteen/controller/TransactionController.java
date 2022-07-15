package com.passbook.sparkeighteen.controller;

import com.passbook.sparkeighteen.peristence.POJO.PaginatedResponse;
import com.passbook.sparkeighteen.peristence.POJO.TransactionRequest;
import com.passbook.sparkeighteen.peristence.POJO.TransactionResponse;
import com.passbook.sparkeighteen.service.TransactionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * Gets user transaction to return response entity of the transaction performed in transaction service
     * @param userID   the user id used to show transaction of that particular user.
     * @param pageSize the page size is give the size of the page.
     * @param pageNo   you can provide the page no to view the next and previous transaction of that user.
     * @return the user transaction list by Paginated.
     */
    @ApiOperation("Get list of all the transaction")
    @GetMapping("/{userID}/transaction")
    public ResponseEntity<PaginatedResponse> getUserTransaction(@PathVariable Integer userID, @RequestParam(required = false, defaultValue = "10") String pageSize, @RequestParam(required = false, defaultValue = "0") String pageNo) {
        return ResponseEntity.ok(transactionService.getTransaction(pageNo, pageSize, userID));
    }
}