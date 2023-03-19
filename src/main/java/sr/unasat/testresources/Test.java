package sr.unasat.testresources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
@Path("/test")
public class Test {    
    @Path("/getText")    
    @GET    
    @Produces(MediaType.APPLICATION_JSON)    
    public String getText(){
        return " IT Works";}}
