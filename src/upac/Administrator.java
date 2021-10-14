/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upac;

/**
 *
 * @author user
 */
public class Administrator{
    //staffmembers.id_member, staffmembers.first_name, staffmembers.second_name, staffmembers.last_name, staffmembers.password, 
    //staffmembers.profile, administrator.admin_state, administrator.email 
    private int member_id;
    private String name;
    private String password;
    private String profile;
    private int admin_state;
    private String email;

    public Administrator() {
    }

    public Administrator(int member_id, String name, String password, String profile, int admin_state, String email) {
        this.member_id = member_id;
        this.name = name;
        this.password = password;
        this.profile = profile;
        this.admin_state = admin_state;
        this.email = email;
    }

    public int getAdmin_state() {
        return admin_state;
    }

    public String getEmail() {
        return email;
    }

    public int getMember_id() {
        return member_id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getProfile() {
        return profile;
    }

    public void setAdmin_state(int admin_state) {
        this.admin_state = admin_state;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
    
    
}