package lv.Autentica.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lv.Autentica.demo.models.Items;

@Repository
public interface ItemsRepo extends JpaRepository<Items, Long> {

}
