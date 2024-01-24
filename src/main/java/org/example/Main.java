package org.example;

import org.example.data.model.Admin;
import org.example.data.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class Main implements CommandLineRunner {
    @Autowired
    AdminRepository adminRepository;

    public final static String ADMIN_EMAIL = "ojot630@gmail.com";

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (adminRepository.findByUsername("Admin") == null) {
            Admin admin = new Admin();
            admin.setUsername("Admin");
            admin.setPassword("1234567");
            admin.setEmail("ojot630@gmail.com");
            adminRepository.save(admin);}
    }
}
