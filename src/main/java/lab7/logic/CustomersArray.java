package lab7.logic;

import lab7.data.Customer;

import java.util.*;

public class CustomersArray {
    private List<Customer> customers;

    public CustomersArray() {
        customers = new ArrayList<>();
    }

    public int getSize() {
        return customers.size();
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void add(Customer c) {
        customers.add(c);
    }

    public List<Customer> findByName(String s) {
        List<Customer> list = new ArrayList<>();
        for (Customer customer : customers) {
            if (s.equals(customer.getName())) {
                list.add(customer);
            }
        }
        return list;
    }

    public List<Customer> findByCard(long min, long max) {
        List<Customer> list = new ArrayList<>();
        for (Customer customer : customers) {
            if (customer.getCardNumber() >= min && customer.getCardNumber() <= max) {
                list.add(customer);
            }
        }
        return list;
    }

    public List<Customer> findByBalance() {
        List<Customer> list = new ArrayList<>();
        for (Customer customer : customers) {
            if (customer.getCardBalance() < 0) {
                list.add(customer);
            }
        }
        list.sort(new Comparator<Customer>() {

            @Override
            public int compare(Customer c1, Customer c2) {
                return Long.compare(c1.getCardBalance(), c2.getCardBalance());
            }
        });
        return list;
    }

    public List<Customer> sortByBalance() {
        List<Customer> list = new ArrayList<>(customers);
        list.sort(new Comparator<Customer>() {
            @Override
            public int compare(Customer c1, Customer c2) {
                int r = Long.compare(c1.getCardBalance(), c2.getCardBalance());
                if (r!=0){
                    return r;
                }
                return Long.compare(c1.getCardNumber(), c2.getCardNumber());
            }
        });
        return list;
    }

    public Customer get(int i) {
        return customers.get(i);
    }
}
