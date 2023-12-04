package api.payload;

import java.util.Collections;
import java.util.List;

public class Pet {
        String petId;
        Category category;
        String name;
        List<String> photoUrls;
        Tag[] tags;
        String status;

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(int id, String name) {
        this.category = new Category(id, name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public Tag[] getTags() {
        return tags;
    }
    public void setTags(int id , String name) {
        Tag[] tag = new Tag[]{new Tag(id, name)};
        this.tags = tag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    }

