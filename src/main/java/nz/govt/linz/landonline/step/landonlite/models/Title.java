package nz.govt.linz.landonline.step.landonlite.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Title {
    @Id @GeneratedValue
    private long id;

    private String description;
    private String ownerName;

    public Title() {

    }

    public Title(String description, String ownerName) {
        this.description = description;
        this.ownerName = ownerName;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Override
    public String toString() {
        return "Title{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", ownerName='" + ownerName + '\'' +
                '}';
    }
}
