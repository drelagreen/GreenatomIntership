import java.util.*;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Developer dev1 = new Developer("Наташа", Arrays.asList("Java", "C++"));
        Developer dev2 = new Developer("Эрнест", Arrays.asList("Java", "Python"));
        Developer dev3 = new Developer("Элла", Arrays.asList("С#", "Python", "JavaScript"));
        List<Developer> devList = List.of(dev1, dev2, dev3);
        HashMap<String, Integer> map = new HashMap<>();
        devList.forEach(d->{
                    d.getLanguages()
                            .forEach(s->map.put(s,map.containsKey(s) ? map.get(s)+1 : 1));
                });
        devList.stream()
                .filter(developer -> developer.getLanguages()
                        .stream()
                        .anyMatch(s -> map.get(s) <= 1)
                ).forEach(System.out::println);
    }
}
