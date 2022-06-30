package com.passbook.sparkeighteen.controller;

import com.passbook.sparkeighteen.peristence.POJO.TransactionRequest;
import com.passbook.sparkeighteen.peristence.POJO.TransactionResponse;
import com.passbook.sparkeighteen.peristence.POJO.TransactionType;
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

@RequestMapping("/api/v1/user")
@RestController
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @ApiOperation("User deposits a certain amount")
    @PostMapping("/{userID}/deposit")
    public ResponseEntity<TransactionResponse> deposit(@PathVariable Integer userID, @RequestBody final @Valid TransactionRequest request) throws Exception {
        return new ResponseEntity<>(transactionService.transact(userID, request, TransactionType.CREDIT), HttpStatus.OK);
    }

    @ApiOperation("User withdraws a certain amount")
    @PostMapping("/{userID}/withdraw")
    public ResponseEntity<TransactionResponse> withdraw(@PathVariable Integer userID, @RequestBody final @Valid TransactionRequest request) throws Exception {
        return new ResponseEntity<>(transactionService.transact(userID, request, TransactionType.DEBIT), HttpStatus.OK);
    }

}