package ca.bcit.snaydon.castillo.alvar.happydays;

import java.io.Serializable;
import java.util.List;

public class Route implements Serializable {

    private String name;
    private List<String> jsonData;

    public Route(String name, List<String> jsonData) {
        this.name = name;
        this.jsonData = jsonData;
    }

    public String getName() { return name; }

}
