package service;


import model.Comment;
import orm.CommentService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

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
    @DELETE
    @Path("/{id}")
    public boolean deleteComment(@PathParam("id") int id){
       try{
           cmntservice.deleteComment(id);
           return true;
       }catch (Exception e){
           System.out.println("Error delete the comment : " + cmntservice +": "+ e);
           return false;
       }
    }
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Comment[] getAllComments() {
        List<Comment> allComments = new ArrayList<>();
        try {
            allComments = cmntservice.getAllComments();
        } catch (Exception e) {
            System.out.println("Erreu dans l'API users:getAllComments \n"+e);
        }
        Comment [] commentsArray = allComments.stream().toArray(Comment[]::new);
        System.out.println("Comments Array = \n"+commentsArray);
        return commentsArray;
    }

}
