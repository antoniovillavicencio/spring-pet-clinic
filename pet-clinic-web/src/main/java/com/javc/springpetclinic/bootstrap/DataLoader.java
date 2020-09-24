package com.javc.springpetclinic.bootstrap;

import com.javc.springpetclinic.model.Owner;
import com.javc.springpetclinic.model.PetType;
import com.javc.springpetclinic.model.Vet;
import com.javc.springpetclinic.services.OwnerService;
import com.javc.springpetclinic.services.PetTypeService;
import com.javc.springpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService,
                      PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService   = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Antonio");
        owner1.setLastName("Villavicencio");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Gina");
        owner2.setLastName("Castillo");

        ownerService.save(owner2);

        System.out.println("Loades Owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Fernanda");
        vet1.setLastName("Castro");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Sam");
        vet2.setLastName("Axe");

        vetService.save(vet2);

        System.out.println("Loaded vets...");
    }
}
