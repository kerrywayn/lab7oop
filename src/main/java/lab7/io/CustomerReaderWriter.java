package lab7.io;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lab7.data.Customer;
import lab7.logic.CustomersArray;

import java.io.*;
import java.util.List;

public class CustomerReaderWriter {
    public CustomersArray readFromFile(File file){
        CustomersArray customersArray = new CustomersArray();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Customer c = Customer.parse(line);
                customersArray.add(c);
            }
            return customersArray;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void writeToFile(File file, CustomersArray customersArray) {
        try (PrintWriter writer = new PrintWriter(file)) {
            for (int i = 0; i < customersArray.getSize(); i++) {
                writer.println(customersArray.get(i).toTxt());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeToJSON(File file, CustomersArray array) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(file, array.getCustomers());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CustomersArray readFromJSON(File file) {
        CustomersArray customersArray = new CustomersArray();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        String line = reader.readLine();
            ObjectMapper mapper = new ObjectMapper();
            List<Customer> customers = mapper.readValue(line, new TypeReference<List<Customer>>(){});
            customersArray.getCustomers().addAll(customers);
            return customersArray;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
