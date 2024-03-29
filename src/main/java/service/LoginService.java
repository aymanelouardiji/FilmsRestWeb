package service;


import model.Admin;
import model.User;
import orm.AdminService;
import orm.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;

@Path("/authentification")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginService {
    private AdminService adminsrv;
    private UserService usersrv;

    @GET
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdminByEmailAndPass(@QueryParam("emailAdmin") String emailAdmin, @QueryParam("password") String password) {
        Admin admin = adminsrv.getAdminByEmailAndPass(emailAdmin, password);
        if (admin != null) {
            return Response.ok(admin).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
 /*   @GET
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserByEmailAndPassword(@QueryParam("")){
        User user = usersrv.getUserByEmailAndPass()
    }*/
}
