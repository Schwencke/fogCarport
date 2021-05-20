package business.entities;

public class User
{
    private int userId;
    private int roleId;
    private String name;
    private String address;
    private int postalCode;
    private String phoneNo;
    private String email;
    private String password;

    public User() {
    }

    public User( String name, String address, int postalCode, String phoneNo, String email) {
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNo = phoneNo;
        this.email = email;
    }

    public User(String name, String address, int postalCode, String phoneNo, String email, String password) {
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNo = phoneNo;
        this.email = email;
        this.password = password;
    }

    //<editor-fold desc="Getters and setters">
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //</editor-fold>
}
