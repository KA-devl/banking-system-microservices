package com.banking.bankapi.repositories;

import com.banking.bankapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    //EXAMPLESS OF JPA QUERIES ========================================

    //SELECT * FROM _user WHERE firstname = ?
    List<User> findAllByFirstname(String firstname);

    //SELECT * FROM _user WHERE firstname LIKE %Ali%
    List<User> findAllByFirstnameContaining(String firstname);

    //SELECT * FROM _USER U INNER JOIN ACCOUNT A ON U.ID = A.USER_ID WHERE A.IBAN = ?
    List<User> findAllByAccountIban(String iban);

    //SELECT * FROM _USER WHERE FIRSTNAME = ? AND EMAIL = ?
    User findByFirstnameContainingIgnoreCaseAndEmail(String firstname, String email);

    //CUSTOM QUERIES WITH JPQL ========================================
    @Query("from User u where u.firstname = :firstname")
    List<User> searchByFirstname(String firstname);

    @Query("from User u where u.firstname = '%:firstname%'")
    List<User> searchByFirstnameContaining(String firstname);

    @Query("from User u inner join Account a on u.id = a.user.id where a.iban = :iban")
    List<User> searchByAccountIban(String iban);

    // CUSTOM QUERIES WITH NATIVE SQL ========================================
//    @Query(value = "select * from _user u inner join account a on u.id = a.user.id where a.iban = :iban", nativeQuery = true)
//    List<User> searchByAccountIbanNative(@Param(":iban") String iban);

}
