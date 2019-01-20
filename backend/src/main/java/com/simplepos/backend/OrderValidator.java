package com.simplepos.backend;

import com.simplepos.backend.models.Order;
import com.simplepos.backend.models.OrderItem;
import com.simplepos.backend.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Set;

@Component
public class OrderValidator implements Validator {
    @Autowired
    ItemRepository itemRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return Order.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Order order = (Order) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "status", "NotEmpty.Order.status");
        Set<OrderItem> orderItems = order.getItems();
        for (OrderItem orderItem : orderItems) {
            if (!(itemRepository.existsByName(orderItem.getName()))) {
                errors.rejectValue("items","Invalid.Order.items");
                break;
            }
        }
    }
}
