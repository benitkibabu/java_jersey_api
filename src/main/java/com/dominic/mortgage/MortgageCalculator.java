package com.dominic.mortgage;

import com.dominic.mortgage.models.User;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.glassfish.jersey.client.ClientResponse;

@Path("/v2")
public class MortgageCalculator {
    List<User> userList = new ArrayList<>();
    
    @GET
    @Path("/payment")
    @Produces("application/json")
    public Response getPayment(@Context UriInfo info){
        Gson gson = new Gson();
        
        String principle = info.getQueryParameters().getFirst("principle");
	String interest = info.getQueryParameters().getFirst("interest");
        String term = info.getQueryParameters().getFirst("term");
        MortgageInfo mortgageInfo = new MortgageInfo(Double.parseDouble(principle), Double.parseDouble(interest), Integer.parseInt(term));

       return Response.status(200).entity(gson.toJson(mortgageInfo)).build();
    }
    
    @POST
    @Path("/echo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response post(String entity){
       return Response.status(200).entity(entity).build();
    }
    
    
    @POST
    @Path("/user/")
    @Produces("application/json")
    public Response postUser(@FormParam("fname") String fname,
            @FormParam("email") String email,
            @FormParam("address") String address ){
        Gson gson = new Gson();
        User user = new User(fname, email, address);
        userList.add(user);
        
        return Response.status(200).entity(gson.toJson(user)).build();
    }
    
    
    @GET
    @Path("/users")
    @Produces("application/json")
    public Response getUsers(){
        Gson gson = new Gson();
       return Response.status(200).entity(gson.toJson(userList)).build();
    }
}
