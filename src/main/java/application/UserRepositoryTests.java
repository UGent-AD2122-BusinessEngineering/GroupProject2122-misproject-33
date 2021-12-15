package application;

import static org.assertj.core.api.Assertions.assertThat;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repo;

    // test methods go below

    @Test
    public void testCreateUser() {
        Landlord landlord= new Landlord();
        landlord.setEmail("ravikumar@gmail.com");
        landlord.setPassword("ravi2020");
        landlord.setFirstname("Ravi");
        landlord.setLastname("Kumar");

        Landlord savedLandlord = repo.save(landlord);

        Landlord existLandlord = entityManager.find(Landlord.class, savedLandlord.getEmail());

        assertThat(landlord.getEmail()).isEqualTo(existLandlord.getEmail());

    }

}

