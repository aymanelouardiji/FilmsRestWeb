package service;


import model.Comment;
import model.RequestComment;
import orm.CommentService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Path("comments")
public class CommentRestService {
    CommentService cmntservice = new CommentService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean addComment (RequestComment comment){
        try{
            Comment com = new Comment();
            com.setUser(cmntservice.getUserId((int) comment.getIdUser()));
            com.setFilm(cmntservice.getFilmsById(comment.getIdFilm()));
            com.setMsgDesc(comment.getMsgDesc());
            cmntservice.addCmnt(com);
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
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Comment[] getAllComments() {
        List<Comment> allComments = new ArrayList<>();
        try {
            allComments = cmntservice.getAllComments();
        } catch (Exception e) {
            System.out.println("Error in API users:getAllComments \n" + e);
        }

        Comment[] commentsArray = new Comment[allComments.size()];
        allComments.toArray(commentsArray);

        System.out.println("Comments Array = \n" + Arrays.toString(commentsArray));
        return commentsArray;
    }


}
