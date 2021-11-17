package co.edu.unbosque.taller5.services;

import co.edu.unbosque.taller5.jpa.entities.Owner;
import co.edu.unbosque.taller5.jpa.repositories.OwnerImpl;
import co.edu.unbosque.taller5.jpa.repositories.OwnerRepository;
import co.edu.unbosque.taller5.resource.pojo.OwnerPOJO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Optional;

@Stateless
public class OwnerService {

    OwnerRepository ownerRepository;

    public Optional<OwnerPOJO> createOwner(OwnerPOJO ownerPOJO){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        ownerRepository = new OwnerImpl(entityManager);
        Owner owner = new Owner(ownerPOJO.getUsername(), ownerPOJO.getPassword(), ownerPOJO.getEmail(),
                ownerPOJO.getPersonId(), ownerPOJO.getName(), ownerPOJO.getAddress(), ownerPOJO.getNeighborhood());
        Optional<Owner> persistedOwner = ownerRepository.create(owner);

        entityManager.close();
        entityManagerFactory.close();

        if (persistedOwner.isPresent()) {
            return Optional.of(new OwnerPOJO(persistedOwner.get().getUsername(),
                    persistedOwner.get().getPassword(),
                    persistedOwner.get().getEmail(),
                    persistedOwner.get().getPerson_id(),
                    persistedOwner.get().getName(),
                    persistedOwner.get().getAddress(),
                    persistedOwner.get().getNeighborhood()));
        } else {
            return Optional.empty();
        }
    }

    public Owner findUsername(String username){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        ownerRepository = new OwnerImpl(entityManager);
        Owner persistedOwner = ownerRepository.findByOwnerId(username).get();

        entityManager.close();
        entityManagerFactory.close();

        return persistedOwner;
    }

    public OwnerPOJO updateName(String newName, String username){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        ownerRepository = new OwnerImpl(entityManager);
        ownerRepository.updateName(newName, username);

        entityManager.close();
        entityManagerFactory.close();

        Owner owner = findUsername(username);
        OwnerPOJO ownerPOJO = new OwnerPOJO(owner.getUsername(),
                owner.getPassword(),
                owner.getEmail(),
                owner.getPerson_id(),
                owner.getName(),
                owner.getAddress(),
                owner.getNeighborhood());

        return ownerPOJO;
    }

    public OwnerPOJO updateEmail(String newEmail, String username){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        ownerRepository = new OwnerImpl(entityManager);
        ownerRepository.updateEmail(newEmail, username);

        entityManager.close();
        entityManagerFactory.close();

        Owner owner = findUsername(username);
        OwnerPOJO ownerPOJO = new OwnerPOJO(owner.getUsername(),
                owner.getPassword(),
                owner.getEmail(),
                owner.getPerson_id(),
                owner.getName(),
                owner.getAddress(),
                owner.getNeighborhood());

        return ownerPOJO;
    }

    public OwnerPOJO updateAddress(String newAddress, String username){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        ownerRepository = new OwnerImpl(entityManager);
        ownerRepository.updateAddress(newAddress, username);

        entityManager.close();
        entityManagerFactory.close();

        Owner owner = findUsername(username);
        OwnerPOJO ownerPOJO = new OwnerPOJO(owner.getUsername(),
                owner.getPassword(),
                owner.getEmail(),
                owner.getPerson_id(),
                owner.getName(),
                owner.getAddress(),
                owner.getNeighborhood());
        return ownerPOJO;
    }

    public OwnerPOJO updateNeighborhood(String newNeighbohood, String username){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        ownerRepository = new OwnerImpl(entityManager);
        ownerRepository.updateNeighborhood(newNeighbohood, username);

        entityManager.close();
        entityManagerFactory.close();

        Owner owner = findUsername(username);
        OwnerPOJO ownerPOJO = new OwnerPOJO(owner.getUsername(),
                owner.getPassword(),
                owner.getEmail(),
                owner.getPerson_id(),
                owner.getName(),
                owner.getAddress(),
                owner.getNeighborhood());
        return ownerPOJO;
    }
}
