package nz.govt.linz.landonline.step.landonlite;

import nz.govt.linz.landonline.step.landonlite.models.Title;
import nz.govt.linz.landonline.step.landonlite.repositories.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
class SeedData implements CommandLineRunner {

    @Autowired
    private TitleRepository titleRepository;

    @Override
    public void run(String... strings) {
        // Create the seed titles if the database is empty
        if(titleRepository.count() == 0) {
            titleRepository.save(new Title("Lot 1 on Block 1", "Jane Doe"));
            titleRepository.save(new Title("Lot 2 on Block 1", "Bob Smith"));
        }

        // Print a list of titles in the database
        titleRepository.findAll().forEach(System.out::println);
    }
}