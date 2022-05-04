package com.example.hibernatestart.dao;

import com.example.hibernatestart.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    Company getCompanyByName(String name);
    boolean existsByName(String name);
}
