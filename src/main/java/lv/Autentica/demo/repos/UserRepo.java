package lv.Autentica.demo.repos;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import lv.Autentica.demo.models.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

	User findByEmail(String username);

	boolean existsByEmail(@NotNull String email);

}
