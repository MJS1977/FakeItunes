package com.example.task4itunes.controllers;
import com.example.task4itunes.data_access.CustomerRepository;
import com.example.task4itunes.models.Customer;
import com.example.task4itunes.models.CustomerCount;
import com.example.task4itunes.models.CustomerSpend;
import org.springframework.web.bind.annotation.*;
import com.example.task4itunes.models.GetRandomArtist;

import java.util.ArrayList;


@RestController
public class CustomerController {
    // Configure some endpoints to manage crud
    private CustomerRepository customerRepository = new CustomerRepository();

    //get all the customers
    @RequestMapping(value="/api/customers", method = RequestMethod.GET)
    public ArrayList<Customer> getAllCustomers(){
        return customerRepository.getAllCustomers();
    }

    //adds a new customer
    @RequestMapping(value= "/api/customers", method = RequestMethod.POST)
    public Boolean addNewCustomer(@RequestBody Customer customer){
        return customerRepository.addNewCustomer(customer);
    }

    //updates existing customer
    @RequestMapping(value = "api/customers/{id}", method = RequestMethod.PUT)
    public Boolean updateExistingCustomer(@PathVariable String id, @RequestBody Customer customer){
        return customerRepository.updateCustomer(customer);
    }

    //get customers by country
    @RequestMapping(value="/api/customers/country", method = RequestMethod.GET)
    public ArrayList<CustomerCount> getCustomersByCountry(){
        return customerRepository.getCustomersByCountry();
    }

    //get customers who spend the most
    @RequestMapping(value="/api/customers/spenders", method = RequestMethod.GET)
    public ArrayList<CustomerSpend> getHighestSpender(){
        return customerRepository.getHighestSpender();
    }
}
