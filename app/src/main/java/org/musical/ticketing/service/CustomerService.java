package org.musical.ticketing.service;

import org.musical.ticketing.domain.Customer;
import org.musical.ticketing.repositories.CustomersRepository;

/**
 * @author suranjanpoudel
 */
public class CustomerService {

    private final CustomersRepository customersRespository;

    public CustomerService() {
        this.customersRespository = new CustomersRepository();
    }

    public Customer createCustomer(Customer customer) {
        return customersRespository.findByPhoneNumber(customer.phoneNumber())
                .orElseGet(() -> customersRespository.save(customer));
    }
}
