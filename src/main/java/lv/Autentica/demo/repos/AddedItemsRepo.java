package lv.Autentica.demo.repos;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lv.Autentica.demo.models.AddedItems;
import lv.Autentica.demo.models.Status;

@Repository
public interface AddedItemsRepo extends JpaRepository<AddedItems, Long> {

	ArrayList<AddedItems> findByStatus(Status status);

	ArrayList<AddedItems> findByUserIdUser(long id);

}
