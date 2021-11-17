package co.edu.unbosque.taller5.services;

import co.edu.unbosque.taller5.jpa.entities.Owner;
import co.edu.unbosque.taller5.jpa.entities.Pet;
import co.edu.unbosque.taller5.jpa.repositories.OwnerImpl;
import co.edu.unbosque.taller5.jpa.repositories.OwnerRepository;
import co.edu.unbosque.taller5.jpa.repositories.PetImpl;
import co.edu.unbosque.taller5.jpa.repositories.PetRepository;
import co.edu.unbosque.taller5.resource.pojo.PetPOJO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Optional;

@Stateless
public class PetService {

    PetRepository petRepository;
    OwnerRepository ownerRepository;

    public PetPOJO createdPet(Integer petId, String microchip, String name, String species, String race, String size, String sex, String picture, Integer ownerId){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        petRepository = new PetImpl(entityManager);
        ownerRepository = new OwnerImpl(entityManager);
        Optional<Owner> owner = Optional.of(ownerRepository.findAll().get(ownerId-1));
        owner.ifPresent(o->{
            o.addPet(new Pet(petId, microchip, name,species, race,size,sex, picture));
            ownerRepository.create(o);
        });

        entityManager.close();
        entityManagerFactory.close();

        PetPOJO petPOJO = new PetPOJO(petId,microchip,name,species,race,size,sex,picture,ownerId);
        return petPOJO;

    }
}
