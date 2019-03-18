package nz.govt.linz.landonline.step.landonlite.repositories;

import nz.govt.linz.landonline.step.landonlite.models.Title;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitleRepository extends JpaRepository<Title, Long> {
}
