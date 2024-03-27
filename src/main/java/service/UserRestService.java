package service;


import model.User;
import orm.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;


@Path("/users")
public class UserRestService {
    UserService usrv = new UserService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean addUser(User user) {
        try {
            usrv.addUser(user);
            return true;
        } catch (Exception e) {
            System.out.println("Erreur dans l'API users:addUser avec : "+user+"\n"+e);
            return false;
        }
    }

    @DELETE
    @Path("/{id}")
    public boolean deleteUser(@PathParam("id") int id) {
        try {
            usrv.removeUser(id);
            return true;
        } catch (Exception e) {
            System.out.println("Erreu dans l'API users:deleteUser avec id : "+id+"\n"+e);
            return false;
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("id") int id) {
        User aUser = null;
        try {
            aUser = usrv.getUserById(id);
        } catch (Exception e) {
            System.out.println("Erreu dans l'API users:getUser avec id : "+id+"\n"+e);
        }
        System.out.println("Api:getUser, aUser = "+aUser);
        return aUser;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User[] getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        try {
            allUsers = usrv.getAllUsers();
        } catch (Exception e) {
            System.out.println("Erreu dans l'API users:getAllUsers \n"+e);
        }
        User [] usersArray = allUsers.stream().toArray(User[]::new);
        System.out.println("Users Array = \n"+usersArray);
        return usersArray;
    }
}
