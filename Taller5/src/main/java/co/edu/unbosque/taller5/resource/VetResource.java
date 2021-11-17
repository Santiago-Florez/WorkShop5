package co.edu.unbosque.taller5.resource;

import co.edu.unbosque.taller5.resource.pojo.VetPOJO;
import co.edu.unbosque.taller5.services.VetService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/vets")
public class VetResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(VetPOJO vet) {

        Optional<VetPOJO> persistedVet = new VetService().createVet(vet);

        if (persistedVet.isPresent()) {
            return Response.status(Response.Status.CREATED)
                    .entity(persistedVet.get())
                    .build();
        } else {
            return Response.serverError()
                    .entity("Owner user could not be created")
                    .build();
        }

    }


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response hello(@HeaderParam("role") String role) {

        // If role doesn't match
        if (!"vet".equals(role))
            return Response.status(Response.Status.FORBIDDEN)
                    .entity("Role " + role + " cannot access to this method")
                    .build();

        return Response.ok()
                .entity("Hello, World, " + role + "!")
                .build();

    }

}
