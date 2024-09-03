/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting.dto;

/**
 *
 * @author asus
 */
public class UserDetails {

    @Override
    public String toString() {
        return "UserDetails{" + "userid=" + userid + ", password=" + password + ", username=" + username + ", address=" + address + ", city=" + city + ", email=" + email + ", mobile=" + mobile + '}';
    }

    public UserDetails() {
    }

    public String getUserId() {
        return userid;
    }

    public void setUserId(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }
    
    private String userid;
    private String password ;
    private String username;
    private String address ;
    private String city ;
    private String email ;
    private long mobile;

    public UserDetails(String userid, String password, String username, String address, String city, String email, long mobile) {
        this.userid = userid;
        this.password = password;
        this.username = username;
        this.address = address;
        this.city = city;
        this.email = email;
        this.mobile = mobile;
    }
    
}
