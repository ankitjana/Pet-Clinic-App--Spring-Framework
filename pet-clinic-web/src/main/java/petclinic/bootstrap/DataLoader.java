package petclinic.bootstrap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import petclinic.model.*;
import petclinic.services.*;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;

    //@Autowired-not req because constructor
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }


    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();
        if (count==0) {
            loadData();
        }


    }

    private void loadData() {
        PetType dog =  new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);


        PetType cat =  new PetType();
        dog.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialityService.save(radiology);


        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialityService.save(dentistry);


        Owner owner1 = new Owner();

        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("123 blvd james");
        owner1.setCity("Laval");
        owner1.setTelephone("123789456");
        ownerService.save(owner1);

        Owner owner2 = new Owner();

        owner2.setFirstName("Fiona");
        owner2.setLastName("Glen");
        owner2.setAddress("123 blvd james");
        owner2.setCity("Laval");
        owner2.setTelephone("123789456");
        ownerService.save(owner2);

        System.out.println("Loaded Owners");

        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Rosco");
        owner1.getPets().add(mikesPet);

        Pet fionasCat = new Pet();
        fionasCat.setPetType(savedCatPetType);
        fionasCat.setBirthDate(LocalDate.now());
        fionasCat.setOwner(owner2);
        fionasCat.setName("Hamza");
        owner2.getPets().add(fionasCat);
        System.out.println("added Pet");


        Vet vet1 = new Vet();

        vet1.setFirstName("San");
        vet1.setLastName("Glen");
        vet1.getSpecialities().add(savedRadiology);
        vetService.save(vet1);


        Vet vet2 = new Vet();

        vet2.setFirstName("San");
        vet2.setLastName("Axe");
        vet2.getSpecialities().add(savedSurgery);
        vetService.save(vet2);


        System.out.println("Loaded Vets");
    }
}
