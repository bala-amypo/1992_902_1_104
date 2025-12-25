package com.example.demo.service.impl;

import com.example.demo.model.EmployeeProfile;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.service.EmployeeProfileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeProfileServiceImpl implements EmployeeProfileService {

    private final EmployeeProfileRepository repository;

    public EmployeeProfileServiceImpl(EmployeeProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public EmployeeProfile createEmployee(EmployeeProfile employee) {
        return repository.save(employee);
    }

    @Override
    public EmployeeProfile getEmployeeById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<EmployeeProfile> getAllEmployees() {
        return repository.findAll();
    }

    @Override
    public EmployeeProfile updateEmployeeStatus(Long id, boolean active) {
        EmployeeProfile employee = repository.findById(id).orElse(null);
        if (employee != null) {
            employee.setActive(active);
            return repository.save(employee);
        }
        return null;
    }
}
==========



=============




===========


===========




=========


package com.example.demo.service.impl;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserAccountRepository repository;

    public AuthServiceImpl(UserAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public AuthResponse register(RegisterRequest request) {

        UserAccount user = new UserAccount();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPasswordHash(request.getPassword());
        user.setRole(request.getRole());
        user.setActive(true);

        UserAccount saved = repository.save(user);

        return new AuthResponse("dummy-token", saved.getId(),
                saved.getEmail(), saved.getRole());
    }

    @Override
    public AuthResponse login(AuthRequest request) {

        UserAccount user = repository.findByEmail(request.getEmail());

        return new AuthResponse("dummy-token", user.getId(),
                user.getEmail(), user.getRole());
    }
}
