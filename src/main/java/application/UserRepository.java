package application;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<Landlord, Long> {
    @Query("SELECT u FROM Landlord u WHERE u.email = ?1")
    public Landlord findByEmail(String email);

}
