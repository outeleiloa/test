package nz.govt.linz.landonline.step.landonlite.controllers;

import nz.govt.linz.landonline.step.landonlite.models.Title;
import nz.govt.linz.landonline.step.landonlite.models.TitleJournal;
import nz.govt.linz.landonline.step.landonlite.repositories.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

@RestController
@RequestMapping("/api/titles")
public class TitlesController {
    private static ResponseEntity NOT_FOUND = ResponseEntity.notFound().build();

    @Autowired
    private TitleRepository titleRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Title> showTitle(@PathVariable long id) {
        Optional<Title> title = titleRepository.findById(id);
        return title.map(t -> ResponseEntity.ok().body(t)).orElse(NOT_FOUND);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Title> updateTitle(@PathVariable long id, @RequestBody Title body) {
        Title result = titleRepository.findById(id).get();
        String name = body.getOwnerName();
        if (name != null && name.trim().length() > 0) {
            result.setOwnerName(name);
        }
        String description = body.getDescription();
        if (description != null && description.trim().length() > 0) {
            result.setDescription(description);
        }
        String titleNumber = body.getTitleNumber();
        if (titleNumber != null && titleNumber.trim().length() > 0) {
            result.setTitleNumber(titleNumber);
        }
        result.getJournal().add(new TitleJournal(result));
        titleRepository.save(result);
        // Although we do order journal entries by most recently created in
        // descending order when performing a SELECT, Hibernate does not
        // update/create a new Title.journal Set with the new journal
        // entry as the first element of the set (for whatever reason).
        // So, we manually reorder the entries ourselves, then update the
        // associated Title instance before replying to the POST.
        SortedSet<TitleJournal> sorted = new TreeSet<TitleJournal>(
            new Comparator<TitleJournal>() {
                @Override
                public int compare(TitleJournal t1, TitleJournal t2) {
                    return t1.getId() < t2.getId() ? 1 : -1;
                }
            });
        for (TitleJournal t : result.getJournal()) {
            sorted.add(t);
        }
        result.setJournal(sorted);
        return ResponseEntity.ok().body(result);
    }
}
