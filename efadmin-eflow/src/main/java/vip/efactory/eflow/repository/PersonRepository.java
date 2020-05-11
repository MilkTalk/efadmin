package vip.efactory.eflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vip.efactory.eflow.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByUsername(String username);
}
