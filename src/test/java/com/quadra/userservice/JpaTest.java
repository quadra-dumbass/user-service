package com.quadra.userservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class JpaTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void testCascadeRemoveWithSpringDataJpa() {
        // given
        Customer customer = Customer.builder()
                .name("wooseong")
                .build();

        customerRepository.save(customer);

        Order order1 = Order.builder()
                .customer(customer)
                .build();

        Order order2 = Order.builder()
                .customer(customer)
                .build();

        orderRepository.save(order1);
        orderRepository.save(order2);

        Long customerId = customer.getId();
        Long order1Id = order1.getId();
        Long order2Id = order2.getId();


        // when
        System.out.println("customerId : " + customerId);
        System.out.println("order1Id : " + order1Id);
        System.out.println("order2Id : " + order2Id);
        System.out.println("order size : " + orderRepository.findAll().size());
        orderRepository.deleteById(order1Id);

        System.out.println("customer size : " + customerRepository.findAll().size());
        // then

        assertThat(orderRepository.findById(order1Id)).isEmpty();
        assertThat(customerRepository.findById(customerId)).isEmpty();
    }
}
