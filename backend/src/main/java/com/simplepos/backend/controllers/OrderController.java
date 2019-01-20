package com.simplepos.backend.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.simplepos.backend.OrderValidator;
import com.simplepos.backend.details.ResponseDetails;
import com.simplepos.backend.models.Order;
import com.simplepos.backend.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/api/order")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private OrderRepository orderRepository;

    private OrderValidator orderValidator;

    @Autowired
    public OrderController(OrderRepository orderRepository, OrderValidator orderValidator) {
        this.orderRepository = orderRepository;
        this.orderValidator = orderValidator;
    }

    @PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> addOrder(@Valid @RequestBody Order order,
                                           HttpServletRequest request,
                                           BindingResult result) throws JsonProcessingException {

        //validate order
        orderValidator.validate(order, result);

        //if validation errors
        if (result.hasErrors()) {
            ResponseDetails responseDetails = new ResponseDetails(new Date(),
                    HttpServletResponse.SC_BAD_REQUEST,
                    result.getAllErrors().toString(),"Invalid Order.", request.getRequestURI());
            logger.error("Invalid Order. Item mismatch.");

            return new ResponseEntity<>(responseDetails.toJsonString(), HttpStatus.BAD_REQUEST);
        }

        orderRepository.save(order);

        ResponseDetails responseDetails = new ResponseDetails(new Date(),
                HttpServletResponse.SC_OK,
                "Order saved successfully.", null, request.getRequestURI());
        logger.info("Order saved successfully.");

        return new ResponseEntity<>(responseDetails.toJsonString(), HttpStatus.OK);
    }

    // TODO: 1/20/19 implement getAll, getBy status
}
