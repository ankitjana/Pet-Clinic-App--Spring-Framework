package petclinic.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "owners")
public class Owner extends Person{


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();

    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "telephone")
    private String telephone;
    @Builder
    public Owner(Long id, String firstName, String lastName, String address, String city,
                String telephone, Set<Pet> pets)

            {
        super(id, firstName, lastName);
        this.address = address;
        this.city = city;
        this.telephone = telephone;
        if (pets!=null){
            this.pets = pets;
        }


    }
    public Pet getPet(String name) {
        return getPet(name,false);
    }

    public Pet getPet(String name, boolean ignoreNew){
        name = name.toLowerCase();
        for (Pet pet : pets){
            if (!ignoreNew || !pet.isNew()){
                String compName = pet.getName();
                if (compName.equals(name)){
                    return pet;
                }
            }
        }
        return null;
    }
}
