package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.EmployeeFacade;
import facades.PersonFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("employee")
public class EmployeeRescource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final EmployeeFacade FACADE =  EmployeeFacade.getEmployeeFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @GET
    @Produces("text/plain")
    @Path("QueryDemo")
    public String getText(@QueryParam("id") int id,
                          @QueryParam("name") String name) {
        return name;
    }
    @GET
    @Path("/byId/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getById(@PathParam("id") int id) {
        return Response.ok().entity(GSON.toJson(FACADE.getById(id))).build();
    }


    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllEmployees() {
        return Response.ok().entity(GSON.toJson(FACADE.getAll())).build();
    }
   /*
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response postExample(String input){
        PersonDTO personDTO = GSON.fromJson(input, PersonDTO.class);
        System.out.println(personDTO);
        return Response.ok().entity(personDTO).build();
    }
    */
}
