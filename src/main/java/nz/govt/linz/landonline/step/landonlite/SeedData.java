package nz.govt.linz.landonline.step.landonlite;

import nz.govt.linz.landonline.step.landonlite.models.Title;
import nz.govt.linz.landonline.step.landonlite.models.TitleJournal;
import nz.govt.linz.landonline.step.landonlite.repositories.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.List;

@Component
class SeedData implements CommandLineRunner {

    @Autowired
    private TitleRepository titleRepository;

    @Override
    public void run(String... strings) {
        // Create the seed titles if the database is empty
        if(titleRepository.count() == 0) {
            // Need to commit the new title first for our DB to auto-generate
            // the primary key (ID)
            Title titleA = new Title("1", "Lot 1 on Block 1", "Jane Doe");
            titleRepository.save(titleA);
            Title titleB = new Title("2", "Lot 2 on Block 1", "Bob Smith");
            titleRepository.save(titleB);

            // Now that we have our primary keys, we can start journaling
            titleA.getJournal().add(new TitleJournal(titleA));
            titleRepository.save(titleA);
            titleB.getJournal().add(new TitleJournal(titleB));
            titleRepository.save(titleB);
        }

        // Print a list of titles in the database
        titleRepository.findAll().forEach(System.out::println);
    }
}
