package service;

import model.Film;
import orm.FilmsService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
@Path("/films")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
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
    @Path("/{idFilm}")
    public boolean deleteFilm(@PathParam("idFilm")int idFilm){
        try{
            filmservice.removeFilm(idFilm);
            return true;
        }catch (Exception exception){
            System.out.println("Erreu dans l'API Delete Film :  "+idFilm+"\n"+exception);
            return false;
        }
    }

    @GET
    @Path("/{idFilm}")
    @Produces(MediaType.APPLICATION_JSON)
    public Film getFilm(@PathParam("idFilm") int idFilm) {
        Film film = null;
        try {
            film = filmservice.getFilmsById(idFilm);
        } catch (Exception exception) {
            System.out.println("Error in the API getFilm: " + idFilm + "\n" + exception);
        }
        System.out.println("API getFilm: " + film);
        Film filmA = new Film();
        filmA.setId(film.getId());
        filmA.setName(film.getName());
        filmA.setCateg(film.getCateg());
        filmA.setDescrp(film.getDescrp());
        filmA.setPhoto(film.getPhoto());
        //return film;
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
    @PUT
    @Path("/{idFilm}")
    public boolean updateFilm(@PathParam("idFilm") int idFilm, Film film){
        try{
            Film filmmm = filmservice.getFilmsById(idFilm);
            filmmm.setId(filmmm.getId());
            filmmm.setName(film.getName());
            filmmm.setDescrp(film.getDescrp());
            filmmm.setCateg(film.getCateg());
            filmservice.updateFilm(filmmm);
            return true;
        }catch (Exception e){
            System.out.println("Erreur dans l'API UpdateFilm : "+filmservice);
            return false;
        }
    }

}
