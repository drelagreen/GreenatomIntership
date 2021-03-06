import java.util.List;

public class Developer {
    private String name;
    private List<String> languages;

    Developer (String name, List<String> languages){
        this.languages = languages;
        this.name = name;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguage(List<String> language) {
        this.languages = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "name='" + name + '\'' +
                ", languages=" + languages +
                '}';
    }
}
