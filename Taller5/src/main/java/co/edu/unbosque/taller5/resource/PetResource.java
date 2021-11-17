package co.edu.unbosque.taller5.resource;

import co.edu.unbosque.taller5.resource.pojo.PetPOJO;
import co.edu.unbosque.taller5.services.PetService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/pets")
public class PetResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(PetPOJO petPOJO){
        Optional<PetPOJO> persistedPet = Optional.of(new PetService().createdPet(petPOJO.getPetId(), petPOJO.getMicrochip(), petPOJO.getName(), petPOJO.getSpecies(),
                petPOJO.getRace(), petPOJO.getSize(), petPOJO.getSex(), petPOJO.getPicture(), petPOJO.getOwnerId()));
        if (persistedPet.isPresent()){
            return Response.status(Response.Status.CREATED)
                    .entity(persistedPet.get())
                    .build();
        }else {
            return Response.serverError()
                    .entity("No se pudo crear la mascota")
                    .build();
        }
    }
}
