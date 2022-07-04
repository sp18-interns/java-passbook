package com.passbook.sparkeighteen.controller;

import com.passbook.sparkeighteen.peristence.POJO.TransactionRequest;
import com.passbook.sparkeighteen.peristence.POJO.TransactionResponse;
import com.passbook.sparkeighteen.service.TransactionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * The Transaction controller is responsible for processing incoming REST API request, preparing model and returning view to be rendered in response.
 */
@RequestMapping("/api/v1/user")
@RestController
public class TransactionController {
    private final TransactionService transactionService;

    /**
     * Instantiates a new Transaction controller.
     * @param transactionService the transaction service is instantiated to perform further operation
     */
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Transact response entity - To return response entity of the transaction performed in transaction service
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

}