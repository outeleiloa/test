package nz.govt.linz.landonline.step.landonlite.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Entity
public class TitleJournal {
    @Id @GeneratedValue
    private long id;

    @Column(nullable = false)
    private Timestamp time;

    private String titleNumber;
    private String description;
    private String ownerName;

    @ManyToOne
    @JoinColumn(name = "title_id")
    private Title title;

    public TitleJournal() {
    }

    public TitleJournal(Title t) {
        titleNumber = t.getTitleNumber();
        description = t.getDescription();
        ownerName = t.getOwnerName();
        time = new Timestamp(System.currentTimeMillis());
        title = t;
    }

    // No setters, journal entries are read-only

    public long getId() {
        return id;
    }

    public String getTitleNumber() {
        return titleNumber;
    }

    public String getDescription() {
        return description;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public Timestamp getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "TitleJournal{" +
                "id=" + id +
                ", title.id=" + title.getId() +
                ", time=" + time +
                ", titleNumber='" + titleNumber + '\'' +
                ", description='" + description + '\'' +
                ", ownerName='" + ownerName + '\'' +
                '}';
    }
}
