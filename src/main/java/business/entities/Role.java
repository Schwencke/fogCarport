package business.entities;

public class Role {
    int roleId;
    String name;

    public Role(int roleId) {
        this.roleId = roleId;
    }

    public Role(int roleId, String name) {
        this.roleId = roleId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}