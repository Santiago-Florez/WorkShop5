package co.edu.unbosque.taller5.services;

import co.edu.unbosque.taller5.jpa.entities.Vet;
import co.edu.unbosque.taller5.jpa.repositories.VetImpl;
import co.edu.unbosque.taller5.jpa.repositories.VetRepository;
import co.edu.unbosque.taller5.resource.pojo.VetPOJO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Optional;

@Stateless
public class VetService {

    VetRepository vetRepository;

    public Optional<VetPOJO> createVet(VetPOJO vetPOJO) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        vetRepository = new VetImpl(entityManager);

        Vet vet = new Vet(vetPOJO.getUsername(), vetPOJO.getPassword(), vetPOJO.getEmail(), vetPOJO.getName(), vetPOJO.getAddress(),vetPOJO.getNeighborhood());
        Optional<Vet> persistedVet = vetRepository.save(vet);

        entityManager.close();
        entityManagerFactory.close();

        if (persistedVet.isPresent()) {
            return Optional.of(new VetPOJO(persistedVet.get().getUsername(),
                    persistedVet.get().getPassword(),
                    persistedVet.get().getEmail(),
                    persistedVet.get().getName(),persistedVet.get().getAddress(),
                    persistedVet.get().getNeighborhood()));
        } else {
            return Optional.empty();
        }
    }

}
