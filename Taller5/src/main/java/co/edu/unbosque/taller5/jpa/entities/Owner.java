package co.edu.unbosque.taller5.jpa.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Owner")
@PrimaryKeyJoinColumn
public class Owner extends UserApp implements Serializable {

    @Column(name = "person_id", unique = true)
    private Integer person_id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "neighborhood")
    private String neighborhood;

    @OneToMany(mappedBy = "ownerId", cascade = CascadeType.ALL)
    private List<Pet> pets = new ArrayList<>();

    public Owner() {}

    public Owner(String username, String password, String email, Integer personId, String name, String address, String neighborhood) {
        super(username, password, email, "owner");
        this.person_id = personId;
        this.name = name;
        this.address = address;
        this.neighborhood = neighborhood;
    }

    public void addPet(Pet pet) {
        pets.add(pet);
        pet.setOwner(this);
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer personId) {
        this.person_id = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }
}
