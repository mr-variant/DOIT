package aksenchyk.doit.models;


import aksenchyk.doit.adapters.UserId;

/**
 * Created by ixvar on 6/8/2018.
 */

public class Tasks  extends UserId {

    private String category;
    private String name;
    private Boolean status;

    public Tasks() {

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
