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
public class MembreData {
    private String nom;
    private String address;
    private String poste;
    private String affectation;
    private String bureau;
    private int id;
    private String timestamp;
    private String profile;
    private String matricule;
    private String email;
    private String phone;
    private String institution;
    private String division;
    private String password;

    public MembreData() {
    }
    public MembreData(String nom, String poste, String affectation, String bureau, int id, String timestamp, 
            String profile, String matricule, String email, String phone, String institution, String division, String password) {
        this.nom = nom;
        this.address = address;
        this.poste = poste;
        this.affectation = affectation;
        this.bureau = bureau;
        this.id = id;
        this.timestamp = timestamp;
        this.profile = profile;
        this.matricule = matricule;
        this.email = email;
        this.phone = phone;
        this.institution = institution;
        this.division = division;
        this.password = password;
    }
    

    public String getAddress() {
        return address;
    }

    public String getBureau() {
        return bureau;
    }

    public String getAffectation() {
        return affectation;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getPhone() {
        return phone;
    }

    public String getPoste() {
        return poste;
    }

    public String getPassword() {
        return this.password;
    }
     public String getProfile() {
        return profile;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAffectation(String affectation) {
        this.affectation = affectation;
    }

    public void setBureau(String bureau) {
        this.bureau = bureau;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setDivision(String division) {
        this.division = division;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
    public void setInstitution(String institution){
        this.institution = institution;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getInstitution() {
        return this.institution;
       //To change body of generated methods, choose Tools | Templates.
    }

    public String getDivision() {
        return this.division;
        //To change body of generated methods, choose Tools | Templates.
    }

       
}
