package com.javc.springpetclinic.bootstrap;

import com.javc.springpetclinic.model.Owner;
import com.javc.springpetclinic.model.Pet;
import com.javc.springpetclinic.model.PetType;
import com.javc.springpetclinic.model.Specialty;
import com.javc.springpetclinic.model.Vet;
import com.javc.springpetclinic.services.OwnerService;
import com.javc.springpetclinic.services.PetTypeService;
import com.javc.springpetclinic.services.SpecialtyService;
import com.javc.springpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;

    public DataLoader(OwnerService ownerService, VetService vetService,
                      PetTypeService petTypeService, SpecialtyService specialtyService) {
        this.ownerService = ownerService;
        this.vetService   = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();
        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology = specialtyService.save(radiology);

        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        Specialty savedSurgery = specialtyService.save(surgery);

        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");
        Specialty savedDentistry = specialtyService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("Antonio");
        owner1.setLastName("Villavicencio");
        owner1.setAddress("123 Brickerel");
        owner1.setCity("Miami");
        owner1.setTelephone("0152552343");

        Pet tonysPet = new Pet();
        tonysPet.setOwner(owner1);
        tonysPet.setPetType(savedDogPetType);
        tonysPet.setBirthDate(LocalDate.now());
        tonysPet.setName("Amadeus");
        owner1.getPets().add(tonysPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Gina");
        owner2.setLastName("Castillo");
        owner2.setAddress("458 Zamora");
        owner2.setCity("CDMX");
        owner2.setTelephone("182931");

        Pet pocasaPet = new Pet();
        pocasaPet.setOwner(owner2);
        pocasaPet.setPetType(savedCatPetType);
        pocasaPet.setBirthDate(LocalDate.now());
        pocasaPet.setName("Kayak");
        owner2.getPets().add(pocasaPet);

        ownerService.save(owner2);

        System.out.println("Loades Owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Fernanda");
        vet1.setLastName("Castro");
        vet1.getSpecialties().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Sam");
        vet2.setLastName("Axe");
        vet2.getSpecialties().add(savedSurgery);

        vetService.save(vet2);

        System.out.println("Loaded vets...");
    }
}
