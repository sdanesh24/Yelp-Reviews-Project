import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

public class UserEntity {

    // Entity = class that maps to a database
    @Entity
    @Table(name = "userInformation")
    public class User {
        @Id
        @OneToMany
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private YelpReview id;

        @Column(unique = true, nullable = false)
        private String username;

        @Column(unique = true, nullable = false)
        private String password;
        // hash passwords for security?

        // create a database of api calls that map to which user created the call
        @OneToMany (mappedBy = "username", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        // THe user's api calls
        // List<ApiCall> apiCalls;

        @CreationTimestamp
        private LocalDateTime createdAt;

        @UpdateTimestamp
        private LocalDateTime updatedAt;

        // why is this User method needed?
        public User() {}
        public User(String username, String password)
        {
            this.username = username;
            this.password = password;
        }

    }
}
