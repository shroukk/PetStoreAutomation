package api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Category {

    @JsonProperty("id")
    int id;
    @JsonProperty("name")
    String name;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
