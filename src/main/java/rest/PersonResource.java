package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.PersonDTO;
import dtos.RenameMeDTO;
import errorhandling.PersonNotFoundException;
import utils.EMF_Creator;
import facades.PersonFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//Todo Remove or change relevant parts before ACTUAL use
@Path("person")
public class PersonResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
       
    private static final PersonFacade FACADE =  PersonFacade.getPersonFacade(EMF);
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

    @Path("{id}")
    @GET
    @Produces("text/plain")
    public Response getUserById(@PathParam("id") long id) throws PersonNotFoundException {

        return Response.ok().entity(GSON.toJson(FACADE.getById(id))).build();
    }

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllPersons() {
        return Response.ok().entity(GSON.toJson(FACADE.getAll())).build();
    }



    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createPerson(String jsonInput){
        PersonDTO personDTO = GSON.fromJson(jsonInput, PersonDTO.class);
        PersonDTO created = FACADE.create(personDTO);
        //System.out.println(personDTO);
        return Response.ok().entity(created).build();
    }
    @Path("/testException")
    @GET
    @Produces("text/plain")
    public String throwExpeption() throws Exception{
        throw new Exception("My exception");
    }

    @PUT
    @Path("/update/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response update(@PathParam("id") int id, String input) {
        PersonDTO newPerson = GSON.fromJson(input,PersonDTO.class);
        newPerson.setId(id);
        PersonDTO returned = FACADE.update(newPerson);
        return Response.ok().entity(returned).build();
    }
}
