/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting.dto;

/**
 *
 * @author asus
 */
public class UserInfo {

    public UserInfo() {
    }

    @Override
    public String toString() {
        return "UserInfo{" + "userid=" + userid + ", username=" + username + ", city=" + city + ", email=" + email + '}';
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public UserInfo(String userid, String username, String city, String email) {
        this.userid = userid;
        this.username = username;
        this.city = city;
        this.email = email;
    }
    private String userid ;
    private String username ;
    private String city ; 
    private String email ;
    
}
