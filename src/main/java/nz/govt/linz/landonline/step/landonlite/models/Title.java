package nz.govt.linz.landonline.step.landonlite.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Title {
    @Id @GeneratedValue
    private long id;

    @Column(unique = true, nullable = false)
    private String titleNumber;

    private String description;
    private String ownerName;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL},
            mappedBy = "title", orphanRemoval = true)
    @OrderBy("id desc")
    private Set<TitleJournal> journal;

    public Title() {

    }

    public Title(String titleNumber, String description, String ownerName) {
        this.titleNumber = titleNumber;
        this.description = description;
        this.ownerName = ownerName;
        journal = new HashSet();
    }

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

    public Set<TitleJournal> getJournal() {
        return journal;
    }

    public void setTitleNumber(String titleNumber) {
        this.titleNumber = titleNumber;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setJournal(Set<TitleJournal> journal) {
        this.journal = journal;
    }

    @Override
    public String toString() {
        return "Title{" +
                "id=" + id +
                ", titleNumber='" + titleNumber + '\'' +
                ", description='" + description + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", journal=" + journal +
                '}';
    }
}
