package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.BasicHttpClientResponseHandler;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONObject;

import model.Film;
import model.Comment;

import model.User;

@ManagedBean(name = "userRestBean", eager = true)
@SessionScoped
public class userRestBean implements Serializable{

	private static final long serialVersionUID = -6265131590659205761L;
	
	private final String restResource = "http://localhost:8081/FilmsRestWeb/api/films";
	
	private List<Film> listFilms = new ArrayList<Film>();
	private Comment selectedFilm = new Comment();
	private int idToUpdate;

	private String result = "walou";
	private HttpClient client;
	
	public userRestBean() {
		client = HttpClients.createDefault();
	}
	
	public void getAllFilms() {
		try {
			HttpGet request = new HttpGet(restResource);
			CloseableHttpClient client = HttpClients.createDefault();
			result = client.execute(request, new BasicHttpClientResponseHandler());
			JSONArray jsonArray = new JSONArray(result);
			listFilms.clear();
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				Film aFilm = new Film();
				aFilm.setId(jsonObject.getInt("id"));
				aFilm.setName(jsonObject.getString("name"));
				aFilm.setDescrp(jsonObject.getString("photo"));
				aFilm.setDescrp(jsonObject.getString("descrp"));
				aFilm.setCateg(jsonObject.getString("categ"));
				listFilms.add(aFilm);
			}
		} catch (Exception e) {
			System.out.println("Erreur lors de l'execution de getAllFilms:\n" + e);
		}
	}
	
//	public void getOneFilm() {
//		try {
//			System.out.println("Request = "+restResource+idToUpdate);
//			HttpGet request = new HttpGet(restResource+idToUpdate);
//			CloseableHttpClient client = HttpClients.createDefault();
//			result = client.execute(request, new BasicHttpClientResponseHandler());
//			JSONObject jsonObject = new JSONObject(result);
//			selectedFilm = new Comment();
//			selectedFilm.setId(jsonObject.getInt("id"));
//			selectedFilm.setMsgDesc(jsonObject.getString("msgDesc"));
//		} catch (Exception e) {
//			System.out.println("Erreur lors de l'execution de getOneComment:\n" + e);
//		}
//	}
	
	public String redirectToFilmDetails(int filmId) {
	    // Perform any necessary logic here, like loading film details from database

	    // Assuming you have a page named "filmDetails.xhtml"
	    String destination = "filmDetails.xhtml?faces-redirect=true&filmId=" + filmId;
	    FacesContext context = FacesContext.getCurrentInstance();
	    NavigationHandler handler = context.getApplication().getNavigationHandler();
	    handler.handleNavigation(context, null, destination);
	    return null; // Or return a navigation case if needed
	}

	public List<Film> getListFilms() {
		return listFilms;
	}

	public void setListFilms(List<Film> listFilms) {
		this.listFilms = listFilms;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public HttpClient getClient() {
		return client;
	}

	public void setClient(HttpClient client) {
		this.client = client;
	}

	public String getRestResource() {
		return restResource;
	}

	



}
