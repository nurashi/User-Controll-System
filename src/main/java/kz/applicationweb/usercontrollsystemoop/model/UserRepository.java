package kz.applicationweb.usercontrollsystemoop.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameAndPassword(String username, String password);

    @Query("SELECT u FROM User u WHERE u.phone = ?1")
    Optional<User> findByPhone(String phone);

    @Query("SELECT u FROM User u WHERE u.name LIKE %?1% OR u.address LIKE %?2%")
    List<User> searchUsers(String name, String address);


    List<User> searchUsersByNameOrAddress(String name, String address);
}
