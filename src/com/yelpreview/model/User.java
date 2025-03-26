package com.yelpreview.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

    @Entity
    @Table(name = "user_information")
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(unique = true, nullable = false)
        private String username;

        @Column(unique = true, nullable = false)
        private String password;
        // hash passwords for security?

        // create a database of api calls that map to which user created the call
        //onetomany (mappedBy = "username") //cascade = CascadeType.ALL, fetch = FetchType.LAZY
        // The user's api calls
        // List<ApiCall> apiCalls;

        @CreationTimestamp
        private LocalDateTime createdAt;

        @UpdateTimestamp
        private LocalDateTime updatedAt;


        public User() {}

        public User(String username, String password)
        {
            this.username = username;
            this.password = password;
        }

        public Long getId()
        {
            return id;
        }

        // getters and setters for username and password

        public String getUsername()
        {
            return username;
        }

        public void setUsername(String username)
        {
            this.username = username;
        }

        public String getPassword()
        {
            return password;
        }

        public void setPassword(String password)
        {
            this.password = password;
        }

    }
