package com.LearnJPA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
 
import javax.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
 
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
class Example2ApplicationTests {
    @Value("${spring.datasource.url}")
    private String URL;
    @Value("${spring.datasource.driver-class-name}")
    private String DRIVERNAME;
    @Value("${spring.datasource.username}")
    private String USERNAME;
    @Value("${spring.datasource.password}")
    private String PASSWORD;
 
    @Test
    void connTest() throws SQLException, ClassNotFoundException {
        Class.forName(DRIVERNAME);
        try(Connection conn = 
                DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            System.out.println("connTest: "+conn);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    @Autowired
    CustomerRepository customerRepository;
    
    @PostConstruct
    public void init() {
        customerRepository.save(new Customer("Jack", "Bauer"));
        customerRepository.save(new Customer("Chloe", "O'Brian"));
        customerRepository.save(new Customer("Kim", "Bauer"));
        customerRepository.save(new Customer("David", "Palmer"));
        customerRepository.save(new Customer("Michelle", "Dessler"));
    }
    
    @Test
    void queryTest() throws SQLException, ClassNotFoundException {
        Optional<Customer> result=customerRepository.findById(1L);
        System.out.println("queryTest: "+result);
    }
}
