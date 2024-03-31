package beans;



import model.Film;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.json.Json;
import javax.json.JsonObject;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.client5.http.impl.classic.BasicHttpClientResponseHandler;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONObject;

import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "adminBean",eager = true)
@SessionScoped
public class AdminBean {
    private  static final long serialVersionUID = 500233038557772336L;
    private final String restResource = "http://localhost:8080/RestWebservice/api/films/";

    private List<Film> listFilms = new ArrayList<>();
    private Film selectedFilm = new Film();
    private Film filmToAdd = new Film();
    private int idToUpdate;
    private String result = "No Data To Show";
    private HttpClient client;
    public AdminBean(){
        client = HttpClients.createDefault();
    }
    public void getAllFilms(){
        try {
            HttpGet request = new HttpGet(restResource);
            CloseableHttpClient client = HttpClients.createDefault();
            result = client.execute(request, new BasicHttpClientResponseHandler());
            JSONArray jsonArray = new JSONArray(result);
            listFilms.clear();
            for (int i =0; i< jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Film obFilm = new Film();
                obFilm.setId(jsonObject.getInt("idFilm"));
                obFilm.setName(jsonObject.getString("name"));
                obFilm.setCateg(jsonObject.getString("categ"));
                obFilm.setDescrp(jsonObject.getString("descrp"));
                obFilm.setPhoto(jsonObject.getString("photo"));
                listFilms.add(obFilm);
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de l'execution de getAllFilms:\n" + e);
        }
    }

    public void getOneFilm(){
        try{
            System.out.println("Request = "+restResource+idToUpdate);
            HttpGet request = new HttpGet(restResource+idToUpdate);
            CloseableHttpClient client = HttpClients.createDefault();
            result = client.execute(request, new BasicHttpClientResponseHandler());
            JSONObject jsonObject = new JSONObject(result);
            selectedFilm = new Film();
            selectedFilm.setId(jsonObject.getInt("idFilm"));
            selectedFilm.setName(jsonObject.getString("name"));
            selectedFilm.setCateg(jsonObject.getString("categ"));
            selectedFilm.setDescrp(jsonObject.getString("descrp"));
            selectedFilm.setPhoto(jsonObject.getString("photo"));
        }catch (Exception e) {
            System.out.println("Erreur lors de l'execution de getOneFilm:\n" + e);
        }
    }
    public void addFilm(){
        try{
            String jsonStr ;
            JsonObject jsonObject = Json.createObjectBuilder()
                    .add("idFilm",0)
                    .add("name",filmToAdd.getName())
                    .add("categ",filmToAdd.getCateg())
                    .add("descrp",filmToAdd.getDescrp())
                    .add("photo",filmToAdd.getPhoto())
                    .build();
            Writer writer = new StringWriter();
            Json.createWriter(writer).write(jsonObject);
            jsonStr = writer.toString();
            System.out.println("\nJSON string:\n" + jsonStr);
            System.out.println("Request to : "+restResource);
            StringEntity entity = new StringEntity(jsonStr);
            HttpPost request = new HttpPost(restResource);
            request.setEntity(entity);
            request.setHeader("Content-type", "application/json");
            CloseableHttpClient client = HttpClients.createDefault();
            result = client.execute(request, new BasicHttpClientResponseHandler());
            System.out.println("result = "+result);
        }catch (Exception e) {
            System.out.println("Erreur lors de l'execution de AddUser:\n" + e);
        }
    }
    public void updateFilm(){
        try{
            String jsonStr;
            JsonObject jsonObject = Json.createObjectBuilder()
                    .add("idFilm",selectedFilm.getId())
                    .add("name",selectedFilm.getName())
                    .add("categ",selectedFilm.getCateg())
                    .add("descrp",selectedFilm.getDescrp())
                    .add("photo",selectedFilm.getPhoto())
                    .build();
            Writer writer = new StringWriter();
            Json.createWriter(writer).write(jsonObject);
            jsonStr = writer.toString();

            StringEntity entity = new StringEntity(jsonStr);
            HttpPut request = new HttpPut(restResource+idToUpdate);
            request.setEntity(entity);
            request.setHeader("Content-type", "application/json");
            CloseableHttpClient client = HttpClients.createDefault();
            result = client.execute(request, new BasicHttpClientResponseHandler());
            System.out.println("result = "+result);
        }catch (Exception e) {
            System.out.println("Erreur lors de l'execution de getAllFilms:\n" + e);
        }
    }
    public void deleteFilm() {
        try {
            HttpDelete request = new HttpDelete(restResource+idToUpdate);
            CloseableHttpClient client = HttpClients.createDefault();
            result = client.execute(request, new BasicHttpClientResponseHandler());
            System.out.println("result = "+result);
        } catch (Exception e) {
            System.out.println("Erreur lors de l'execution de getAllFilms:\n" + e);
        }
    }
    public List<Film> getListFilms(){return listFilms;}
    public void setListFilms(List<Film> listFilms) {this.listFilms = listFilms;}

    public String getRestResource() {
        return restResource;
    }

    public Film getSelectedFilm() {
        return selectedFilm;
    }

    public void setSelectedFilm(Film selectedFilm) {
        this.selectedFilm = selectedFilm;
    }

    public Film getFilmToAdd() {
        return filmToAdd;
    }

    public void setFilmToAdd(Film filmToAdd) {
        this.filmToAdd = filmToAdd;
    }

    public int getIdToUpdate() {
        return idToUpdate;
    }

    public void setIdToUpdate(int idToUpdate) {
        this.idToUpdate = idToUpdate;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
