package service;

import model.Film;
import orm.FilmsService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
@Path("/films")

public class FilmRestService {
    FilmsService filmservice = new FilmsService();

    @POST
    public boolean addFilm(Film film){
        try{
            filmservice.addFilm(film);
            return true;
        }catch (Exception  exception){
            System.out.println("Erreur dans L'API ADD FILMS : " +film+"\n"+exception);
        }
        return false;
    }
    @DELETE
    @Path("/{id}")
    public boolean deleteFilm(@PathParam("id")int id){
        try{
            filmservice.removeFilm(id);
            return true;
        }catch (Exception exception){
            System.out.println("Erreu dans l'API Delete Film :  "+id+"\n"+exception);
            return false;
        }
    }

    @GET
    @Path("/{id}")
    public Film getFilm(@PathParam("id")int id){
        Film film = null;
        try{
            film = filmservice.getFilmsById(id);
        }catch (Exception exception){
            System.out.println("Erreu dans l'API  get Film : "+id+"\n"+exception);
        }
        System.out.println("API GetUser "+film);
        return film;
    }
    @GET
    public Film[] getAllFilms(){
        List<Film> allFilms = new ArrayList<>();
        try{
            allFilms = filmservice.getAllFilms();
        }catch (Exception exception){
            System.out.println("Erreu dans l'API GET ALL Films \n"+exception);
        }
        Film [] filmsArray = allFilms.stream().toArray(Film[]::new);
        System.out.println("Films Array = " +filmsArray);
        return filmsArray;
    }

}
