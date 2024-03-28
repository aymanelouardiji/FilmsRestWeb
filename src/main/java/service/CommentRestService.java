package service;


import model.Comment;
import orm.CommentService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("comments")
public class CommentRestService {
    CommentService cmntservice = new CommentService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean addComment (Comment comment){
        try{
            cmntservice.addCmnt(comment);
            return true;
        }catch (Exception e){
            System.out.println("Erreur dans l'API Comment" + comment +" :" +e);
            return false;
        }
    }


}
