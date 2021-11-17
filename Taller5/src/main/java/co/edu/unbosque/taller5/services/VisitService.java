package co.edu.unbosque.taller5.services;

import co.edu.unbosque.taller5.jpa.entities.Pet;
import co.edu.unbosque.taller5.jpa.entities.UserApp;
import co.edu.unbosque.taller5.jpa.entities.Vet;
import co.edu.unbosque.taller5.jpa.entities.Visit;
import co.edu.unbosque.taller5.jpa.repositories.*;
import co.edu.unbosque.taller5.resource.pojo.VisitPOJO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Optional;

@Stateless
public class VisitService {

    VisitRepository visitRepository;
    VetRepository userAppRepository;
    PetRepository petRepository;

    public VisitPOJO createVisit(String visitId, String createdAt, String type, String description, String vetId, Integer petId){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        visitRepository = new VisitImpl(entityManager);
        userAppRepository = new VetImpl(entityManager);
        petRepository = new PetImpl(entityManager);
        Optional<Pet> pet = petRepository.findId(petId);
        Optional<Vet> userApp = userAppRepository.findByUsername(vetId);

        pet.ifPresent(pet1 -> {
            Visit visit = new Visit(visitId, createdAt, type, description);
            visit.setPet_id(pet1);
            pet1.addVisit(visit);
            visitRepository.save(visit);
        });
        userApp.ifPresent(userApp1 -> {
            Visit visit = new Visit(visitId, createdAt, type, description);
            visit.setVet_id(userApp1);
            userApp1.addVisit(visit);
            visitRepository.save(visit);
        });

        entityManager.close();
        entityManagerFactory.close();

        VisitPOJO visitPOJO = new VisitPOJO(visitId,createdAt, type, description);

        return visitPOJO;
    }

}
