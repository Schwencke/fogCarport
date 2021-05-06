package business.entities;

public class Role {
    int roleId;
    String role;

    public Role(int roleId, String role) {
        this.roleId = roleId;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}