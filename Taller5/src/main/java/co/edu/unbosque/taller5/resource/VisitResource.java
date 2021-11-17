package co.edu.unbosque.taller5.resource;

import co.edu.unbosque.taller5.resource.pojo.VisitPOJO;
import co.edu.unbosque.taller5.services.VisitService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/visits")
public class VisitResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(VisitPOJO visitPOJO){
        Optional<VisitPOJO> persistedVisit = Optional.of(new VisitService().createVisit(
                visitPOJO.getVisitId(), visitPOJO.getCreatedAt(), visitPOJO.getType(), visitPOJO.getDescription(),
                visitPOJO.getVetId(), visitPOJO.getPetId()));
        if (persistedVisit.isPresent()){
            return Response.status(Response.Status.CREATED)
                    .entity(persistedVisit.get())
                    .build();
        }else {
            return Response.serverError()
                    .entity("No se pudo crear la mascota")
                    .build();
        }
    }

}
