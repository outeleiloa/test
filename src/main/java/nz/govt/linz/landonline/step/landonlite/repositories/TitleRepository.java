package nz.govt.linz.landonline.step.landonlite.repositories;

import nz.govt.linz.landonline.step.landonlite.models.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TitleRepository extends JpaRepository<Title, Long> {

    List<Title> findByTitleNumber(String titleNumber);
}
