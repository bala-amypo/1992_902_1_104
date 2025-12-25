package com.example.demo.dto;

public class AuthRequest {

    private String email;
    private String password;

    public AuthRequest() {
    }

    public AuthRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}

// =======

// package com.example.demo.config;

// import com.example.demo.servlet.HelloServlet;
// import org.springframework.boot.web.servlet.ServletRegistrationBean;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// public class ServletConfig {

//     @Bean
//     public ServletRegistrationBean<HelloServlet> helloServlet() {
//         ServletRegistrationBean<HelloServlet> bean =
//                 new ServletRegistrationBean<>(new HelloServlet(), "/hello-servlet");
//         bean.setLoadOnStartup(1);
//         return bean;
//     }
// }

