package beans;

import java.util.Map;
import java.util.TreeMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "themeSwitcher")
@SessionScoped
public class ThemeSwitcher {
    private Map<String, String> themes;
    private String theme;

    public ThemeSwitcher() {
        theme = "eggplant"; // Set the default theme to "eggplant"
        
        themes = new TreeMap<String, String>();
        // Add themes here if needed
        themes.put("Eggplant", "eggplant"); // Add "eggplant" theme to the themes map
    }

    public Map<String, String> getThemes() {
        return themes;
    }

    public String getTheme() {
        return theme;
    }
}

