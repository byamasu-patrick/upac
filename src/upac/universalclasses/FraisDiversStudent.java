/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upac.universalclasses;

/**
 *
 * @author user
 */
public class FraisDiversStudent {
    private String fullname;
    private String promotion;
    private String faculte;
    private String profile;
    private String matricule;
    private String bordereau;
    private String numeroBordereau;
    private int fraisId;
    private int studentId;

    public FraisDiversStudent() {
    }

    public FraisDiversStudent(String fullname, String promotion, String faculte, String profile, String matricule, String bordereau, String numeroBordereau, int fraisId, int studentId) {
        this.fullname = fullname;
        this.promotion = promotion;
        this.faculte = faculte;
        this.profile = profile;
        this.matricule = matricule;
        this.bordereau = bordereau;
        this.numeroBordereau = numeroBordereau;
        this.fraisId = fraisId;
        this.studentId = studentId;
    }

    public String getBordereau() {
        return bordereau;
    }

    public String getFaculte() {
        return faculte;
    }

    public int getFraisId() {
        return fraisId;
    }

    public String getFullname() {
        return fullname;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getNumeroBordereau() {
        return numeroBordereau;
    }

    public String getPromotion() {
        return promotion;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getProfile() {
        return profile;
    }

    public void setBordereau(String bordereau) {
        this.bordereau = bordereau;
    }

    public void setFaculte(String faculte) {
        this.faculte = faculte;
    }

    public void setFraisId(int fraisId) {
        this.fraisId = fraisId;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setNumeroBordereau(String numeroBordereau) {
        this.numeroBordereau = numeroBordereau;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    
}
