package api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tag {
    @JsonProperty("id")
    int id;
    @JsonProperty("name")
    String name;
    public Tag(int id, String name) {
        this.id = id;
        this.name = name;
    }

}
