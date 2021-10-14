/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upac.helper;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import upac.Administrator;
import upac.MembreData;
import upac.universalclasses.FraisAcademiqueStudent;
import upac.universalclasses.FraisDiversStudent;
import upac.universalclasses.FraisMaladieStudent;
import com.mysql.cj.jdbc.Driver;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;
/**
 *
 * @author user
 */
public class DatabaseManager {
    Connection connection;
    Statement state;
    public static ArrayList<Integer> number = new ArrayList<>();
    public static ArrayList<Integer> date = new ArrayList<>();
    public static ArrayList<Integer> dateMonth = new ArrayList<>();
     public static ArrayList<Integer> numberGeneral = new ArrayList<>();
    public static ArrayList<Integer> dateGeneral = new ArrayList<>();
    public DatabaseManager(){
        Class driver_class=null; 
        try { 
            
            Driver driver = new Driver();
            
            driver.connect("jdbc:mysql://localhost:3306/id12543969_unipan", null);
            driver_class = Class.forName("com.mysql.cj.jdbc.Driver"); 
            this.connection = this.msqlConnection();
            this.getStudentInfo();
        } 
         catch (SQLException e) { 
            e.printStackTrace(); 
        } 
        catch (ClassNotFoundException e) { 
            e.printStackTrace(); 
        } 
        System.out.println("Found driver " + driver_class);
    }
    private Connection msqlConnection(){
        //Connection connection=null; 
        try { 
            //String link = "sql9.freemysqlhosting.net:3306/sql9342371";
            String link = "sql9.localhost:3306/sql9342371";
            connection = DriverManager.getConnection ("jdbc:mysql://"+ link, "id12543969_unipan", ""); 
        } 
        catch (SQLException e) { 
            e.printStackTrace(); 
            return connection;
        }
        try {
            System.out.println ("Established connection to "+ connection.getMetaData().getURL());
        } 
        catch (SQLException e1) { 
            e1.printStackTrace(); 
        }
        return connection;
    }
    public void getStudentInfo(){
        //Statement state = null;
        try {
            this.state = this.connection.createStatement();
            state.execute("SELECT * FROM etudiant");
            ResultSet result = state.getResultSet();
            
            while (result.next()) {                
                System.out.println("......."+ result.getString("first_name") +" "+ result.getString("second_name"));
            }
             if (state != null) {
                state.close();
                //connection.close();
            }
        } catch (SQLException e) {
        }
    
    }
    public boolean loginAdmin(String email, String password){
        try {
            state = this.connection.createStatement();
            String sql = "SELECT staffmembers.id_member, staffmembers.first_name, staffmembers.second_name, staffmembers.last_name, staffmembers.password, staffmembers.profile, administrator.admin_state, administrator.email FROM staffmembers, administrator WHERE administrator.member_id = staffmembers.id_member AND staffmembers.email = administrator.email AND administrator.email = '"+ email +"' AND administrator.password = '"+ password +"'; ";
            state.executeQuery(sql);
            //System.out.println("......."+ email +" "+ password);
            ResultSet result = state.getResultSet();
            
            if (result.next()) {  
                UniversalMethod.adminLogin.add(new Administrator(
                        result.getInt("id_member"),
                        result.getString("first_name") +" "+ result.getString("second_name") +" "+ result.getString("last_name"),
                        result.getString("password"),
                        result.getString("profile"),
                        result.getInt("admin_state"),
                        result.getString("email")));
                //System.out.println("......."+ result.getString("profile"));
                this.getMemberById(result.getInt("id_member"));
                return true;                
            }
            
        } catch (SQLException e) {
            System.out.println("......."+ e.getMessage());
        }
        return false;
    
    }
    //Apparitora
    public boolean loginApparitora(String email, String password){
        MembreData member = null;
        try {
            state = this.connection.createStatement();
            String sql = "SELECT * FROM staffmembers WHERE staffmembers.email = '"+ email +"' AND staffmembers.password = '"+ password +"' AND LOWER(staffmembers.affectation) = LOWER('Apparitora') AND LOWER(staffmembers.poste) = LOWER('Appariteur'); ";
            state.executeQuery(sql);
            //System.out.println("......."+ email +" "+ password);
            ResultSet result = state.getResultSet();
            
            if (result.next()) {  
                
                member = new MembreData();
                UniversalMethod.currentId = result.getInt("id_member");
                member.setNom(result.getString("first_name") +" "+ result.getString("second_name") +" "+ result.getString("last_name"));
                member.setInstitution(result.getString("instutition"));
                member.setAffectation(result.getString("affectation"));
                member.setBureau(result.getString("bureau"));
                member.setPoste(result.getString("poste"));
                member.setProfile(result.getString("profile"));
                member.setDivision(result.getString("division"));
                member.setPhone(result.getString("contact"));
                member.setEmail(result.getString("email"));
                
                UniversalMethod.memberData = member;
                //System.out.println("......."+ result.getString("profile"));
                //this.getMemberById(result.getInt("id_member"));
                return true;                
            }
            
        } catch (SQLException e) {
            System.out.println("......."+ e.getMessage());
        }
        return false;
    
    }
    public void getMemberById(int memberId){
        try {
            
            String sql = "SELECT * FROM staffmembers WHERE id_member = "+ memberId +"";
            state = this.connection.createStatement();
            state.executeQuery(sql);
            //System.out.println("......."+ email +" "+ password);
            ResultSet result = state.getResultSet();
            
            if (result.next()) {  
                UniversalMethod.admindata = new MembreData(
                        result.getString("first_name") +" "+ result.getString("second_name") +" "+ result.getString("last_name"),
                        result.getString("poste"),
                        result.getString("affectation"),
                        result.getString("bureau"),
                        result.getInt("id_member"),
                        result.getString("timestamp"), 
                        result.getString("profile"), 
                        result.getString("matricule"), 
                        result.getString("email"),
                        result.getString("contact"),
                        result.getString("instutition"),
                        result.getString("division"),
                        result.getString("password")
                );
                
                System.out.println("......."+ result.getString("email") +" "+ result.getString("password"));
                return;                
            }
        } catch (Exception e) {
        }
    }
    public void getMembers(int id){
        UniversalMethod.data = new ArrayList<>();
        try {
            state = this.connection.createStatement();
            String sql = "SELECT * FROM staffmembers WHERE id_member != "+ id +"";
            state.executeQuery(sql);
            ResultSet result = state.getResultSet();
            
            while (result.next()) {      
                UniversalMethod.data.add(new MembreData(
                        result.getString("first_name") +" "+ result.getString("second_name") +" "+ result.getString("last_name"),
                        result.getString("poste"),
                        result.getString("affectation"),
                        result.getString("bureau"),
                        result.getInt("id_member"),
                        result.getString("timestamp"), 
                        result.getString("profile"), 
                        result.getString("matricule"), 
                        result.getString("email"),
                        result.getString("contact"),
                        result.getString("instutition"),
                        result.getString("division"),
                        result.getString("password")
                ));
                //return true;         
                System.out.println("Arrived : "+ result.getString("bureau"));
            }
            return;
            
        } catch (SQLException e) {
            System.out.println("......."+ e.getMessage());
        }
        //return false;
    
    }
    public void insertMember(String first_name, String second_name, String last_name, String instutition, String division,
            String contact, String email, String bureau, String affectation, String poste, String password, String timestamp, String profile){
        try {
            String sql = " INSERT INTO staffmembers (first_name, second_name, last_name, instutition,\n" +
"			  division, matricule, contact, email, profile, bureau, affectation, poste,\n" +
"                         password, timestamp) VALUES ('"+ first_name +"', '"+ second_name 
                            +"', '"+ last_name +"', '"+ instutition +"', '"+ division 
                            +"', 'matricule', '"+ contact +"', '"+ email +"', '"+ profile
                            +"', '"+ bureau +"', '"+ affectation +"', '"+ poste +"', '"+ password +"', '"+ timestamp +"')";
                state = this.connection.createStatement();
                state.executeUpdate(sql);
            int memberId = this.getMembersByNames(first_name, second_name, last_name, email);
            if ( memberId != 0) {
                System.out.println("Updated "+ memberId);
                String fileName = first_name +"_"+ second_name +"_"+ last_name +".png";
                File file = UniversalMethod.generateQRCode(String.valueOf(memberId), fileName);
                
                this.updateMatricule( UniversalMethod.encodeFileToBase64Binary( file ), memberId );
            }
            else{
                System.out.println("Not Upadated "+ memberId);
            }
            return;
            
        } catch (Exception e) {
        }
    }
    public void updateMatricule(String matricule, int idMembre){
        try {
            String sql = "UPDATE staffmembers SET matricule = '"+ matricule +"' WHERE id_member = "+ idMembre +" AND matricule = 'matricule';";
                state = this.connection.createStatement();
                state.executeUpdate(sql);
            return;
        } catch (Exception e) {
        }
    }
    public int getMembersByNames(String firstName, String secondName, String lastName, String email){
        try {
            String sql = "SELECT * FROM staffmembers WHERE LOWER(first_name) = LOWER('"+ firstName +"') AND LOWER(second_name) = LOWER('"+ secondName +"') AND LOWER(last_name) = LOWER('"+ lastName +"') AND LOWER(email) = LOWER('"+ email +"')";
            
            state = this.connection.createStatement();
            state.executeQuery(sql);
            //System.out.println("......."+ email +" "+ password);
            ResultSet result = state.getResultSet();
            
            if (result.next()) {  
                return result.getInt("id_member");
            
            }
        
        } catch (Exception e) {
        }
        return 0;
    }
//    public void getFilteredMebers(){
//        try {
//           // String sql = "SELECT * FROM staffmembers WHERE poste LIKE '"+  +"'";
//        } catch (Exception e) {
//        }
//    }
    public void  updateMembre(String first_name, String second_name, String last_name, String instutition, String division, String matricule,
            String contact, String email, String bureau, String affectation, String poste, String password, String timestamp, int id, String profile){
        try {
            if (profile == null) {
                 String sql = "UPDATE staffmembers SET first_name = '"+ first_name +"',second_name = '"+ second_name +"',last_name = '"+ last_name +"',instutition = '"+ instutition +"',"+
		"division = '"+ division +"',matricule = '"+ matricule +"' ,contact = '"+ contact +"', email = '"+ email +"', bureau = '"+ bureau +"',affectation = '"+ affectation +"',"+
		"poste = '"+ poste +"', password = '"+ password +"',timestamp = '"+ timestamp +"' WHERE id_member = "+ id +" ";
                 
                state = this.connection.createStatement();
                state.executeUpdate(sql);
                //System.out.println("......."+ email +" "+ password);
               return;            
                
            } else {
                
                 String sql = "UPDATE staffmembers SET first_name = '"+ first_name +"',second_name = '"+ second_name +"',last_name = '"+ last_name +"',instutition = '"+ instutition +"',"+
		"division = '"+ division +"',matricule = '"+ matricule +"' ,contact = '"+ contact +"', email = '"+ email +"',profile = '"+ profile +"',bureau = '"+ bureau +"',affectation = '"+ affectation +"',"+
		"poste = '"+ poste +"', password = '"+ password +"',timestamp = '"+ timestamp +"' WHERE id_member = "+ id +" ";
                state = this.connection.createStatement();
                state.executeUpdate(sql);
                
            }
             int memberId = this.getMembersByNames(first_name, second_name, last_name, email);
            if ( memberId != 0) {
                System.out.println("Updated "+ memberId);
                String fileName = first_name +"_"+ second_name +"_"+ last_name +".png";
                File file = UniversalMethod.generateQRCode(String.valueOf(memberId), fileName);

                this.updateMatricule( UniversalMethod.encodeFileToBase64Binary( file ), memberId );
            }
           
           
        } catch (Exception e) {
        }
    }
   
    public void updateAdminState(String email, String password, int state, int member_id){
        try {
            String sql = "UPDATE administrator SET email = '"+ email +"', password = '"+ password +"', admin_state = '"+  state +"' WHERE member_id = "+ member_id +"";
            this.state = connection.createStatement();
            
            this.state.executeUpdate(sql);
            return;
        } catch (Exception e) {
        }
    }
    
    public void activateMembers(int memberId, int bureauId, String email, String password, String time, int activateState){
        try {
            if (this.checkActivation(memberId)) {
               this.updateActivation(bureauId, memberId);
            }
            else{
                String sql = "INSERT INTO `agenda`( `member_id`, `bureau_id`, `activate_state`, `email`, `password`, `timestamp`) VALUES ("+ memberId +", "+ bureauId +", "+ activateState +", '"+ email +"', '"+ password +"', '"+ time +"')";
                this.state = connection.createStatement();

                this.state.executeUpdate(sql);                
            }            
        } catch (Exception e) {
        }
    }
    public boolean checkActivation(int memberId){
        try {
            String sql = "SELECT * FROM `agenda` WHERE `member_id` = "+ memberId +"";
             state = this.connection.createStatement();
            state.executeQuery(sql);
            //System.out.println("......."+ email +" "+ password);
            ResultSet result = state.getResultSet();
            
            if (result.next()) {  
                return true;
            
            }
        } catch (Exception e) {
        }
        return false;
    }
    private void updateActivation(int status, int memberId){
        try {
            String sql = "UPDATE `agenda` SET `status` = "+ status +" WHERE `member_id` = "+ memberId +"";
            this.state = connection.createStatement();
            
            this.state.executeUpdate(sql);
        } catch (Exception e) {
        }
    }
    public void deleteActivation(int memberId){
        try {
            String sql = "DELETE FROM `agenda`  WHERE `member_id` = "+ memberId +"";
             this.state = connection.createStatement();
            
            this.state.executeUpdate(sql);
        } catch (Exception e) {
//        }
    }
    }
    public int getBureauId(String type){
        int bureau = 0;
        try {
             String sql = "SELECT * FROM bureau WHERE LOWER(type) = LOWER('"+ type +"')";
            
            state = this.connection.createStatement();
            state.executeQuery(sql);
            //System.out.println("......."+ email +" "+ password);
            ResultSet result = state.getResultSet();
            
            if (result.next()) {  
                bureau = result.getInt("bureau_id");
            
            }
        } catch (Exception e) {
        }
        return bureau;
    }
    public void insertCarte(int id, String carte, String carteNumber){
        try {
            String sql = "INSERT INTO `carte_member`(`member_id`, `carte_image`, `carte_number`) VALUES ("+ id +", '"+ carte +"', '"+ carteNumber +"')";
             this.state = connection.createStatement();
            
            this.state.executeUpdate(sql);
        } catch (Exception e) {
        }
    }
    public boolean getCarteById(int id){
        try {
            String sql = "SELECT * FROM carte_member WHERE member_id = "+ id +"";
            state = this.connection.createStatement();
            state.executeQuery(sql);
            //System.out.println("......."+ email +" "+ password);
            ResultSet result = state.getResultSet();
            
            if (result.next()) {  
                //return result.getInt("id_member");
                return true;
            }
            
        } catch (Exception e) {
        }
        return false;
    }
    public boolean getCarteStudentById(int id){
        try {
            String sql = "SELECT * FROM carte_etudiant WHERE student_id = "+ id +"";
            state = this.connection.createStatement();
            state.executeQuery(sql);
            //System.out.println("......."+ email +" "+ password);
            ResultSet result = state.getResultSet();
            
            if (result.next()) {  
                //return result.getInt("id_member");
                return true;
            }
            
        } catch (Exception e) {
        }
        return false;
    }
    public boolean addStudent(String firstName, String secondName, String lastName, String genre, String address, String contact,
            String promotion, String faculte, String departement, String profile, String matricule, int etat, String timestamp, String firstNameRespo, String lastNameRespo, String addressRespo, String profession, String contactRespo){
        try {
            String sql = "INSERT INTO etudiant (first_name, second_name, last_name, genre, address, contact, promotion, faculte, departement, profile, matricule, etat, timestamp) VALUES ('"
                    + firstName +"', '"
                    + secondName +"', '"
                    + lastName +"', '"
                    + genre +"', '"
                    + address +"', '"
                    + contact +"', '"
                    + promotion +"', '"
                    + faculte +"', '"
                    + departement +"', '"
                    + profile +"', '"
                    + matricule +"', "
                    + etat +", '"
                    + timestamp +"');";
             
            this.state = connection.createStatement();
            
            this.state.executeUpdate(sql);
            
            int studentId = this.getStudentByNames(firstName, secondName, lastName, promotion);
            if (studentId != 0) {
                this.addResponsable(studentId, firstNameRespo, lastNameRespo, addressRespo, contactRespo, profession, timestamp);
                //System.out.println("Updated "+ memberId);
                String fileName = firstName +"_"+ secondName +"_"+ lastName +".png";
                File file = UniversalMethod.generateQRCode(String.valueOf(studentId), fileName);
                
                this.updateStudentById(UniversalMethod.encodeFileToBase64Binary( file ), studentId);
                return true;
            }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return false;
    }   
    public void addResponsable(int studentId, String firstName, String lastName, String address, String contact,
            String profession, String timestamp){
        try {
            String sql = "INSERT INTO responsable (student_id, first_name, last_name, address, contact, profession, timestamp) VALUES ("
                    + studentId +"', '"+ firstName +"', '"+lastName +"', '"+ address +"', '"+ contact +"', '"+ profession +"', '"+ timestamp +"')";
            this.state = connection.createStatement();
            
            this.state.executeUpdate(sql);

        } catch (Exception e) {
        }
    
    }
    public void getStudentById(int idStudent){
        try {
            String sql = "SELECT * FROM etudiant WHERE id_student = "+ idStudent +"";
             state = this.connection.createStatement();
            state.executeQuery(sql);
            //System.out.println("......."+ email +" "+ password);
            ResultSet result = state.getResultSet();
            
            if (result.next()) {  
                //return result.getInt("id_member");
                return;//result.getInt(sql);
            }
            
        } catch (Exception e) {
        }
    }
    public void updateStudentById(String matricule, int studentId){
        try {
            String sql = "UPDATE etudiant SET matricule = '"+ matricule +"' WHERE id_student = "+ studentId +" AND matricule = 'matricule'";
            this.state = connection.createStatement();
            
            this.state.executeUpdate(sql);
        
        } catch (Exception e) {
        }
    }
    public int getStudentByNames(String firstName, String secondName,String lastName, String promotion){
        try {
            String sql = "SELECT * FROM etudiant WHERE LOWER(first_name) = '"
                    + firstName +"' AND LOWER(second_name) = '"+ secondName +"' AND LOWER(last_name) = '"+ lastName +"' AND LOWER(promotion) = '"+ promotion +"'";
            state = this.connection.createStatement();
            state.executeQuery(sql);
            //System.out.println("......."+ email +" "+ password);
            ResultSet result = state.getResultSet();
            
            if (result.next()) {  
                //return result.getInt("id_member");
                return result.getInt("id_student");
            }
            
        } catch (Exception e) {
        }
        return 0;
    }
    
    public void updateStudent(int etat, int studentId){
        try {
            String sql = "UPDATE etudiant SET etat = "+ etat +" WHERE id_student = "+ studentId +"";
            this.state = connection.createStatement();
            
            
            this.state.executeUpdate(sql);
        } catch (Exception e) {
        }
    }
    /*
    public void activateMembre(){
        try {
            String sql = "INSERT INTO agenda( member_id, bureau_id, activate_state, email, password, timestamp) VALUES ('"+  +"', '"+  +"', '"+  +"', '"+  +"', '"+  +"', '"+  +"')";

        } catch (Exception e) {
        }
    }
    public boolean checkActivation(){
        try {
            String sql = "SELECT * FROM agenda WHERE member_id = '"+  +"'";
        } catch (Exception e) {
        }
        return false;
    }
    public void updateActivation(){
        try {
            String $sql = "UPDATE agenda SET status = '"+  +"' WHERE member_id = '"+  +"'";
        } catch (Exception e) {
        }
    }
    public void deleteActivation(){
        try {
            String sql = "DELETE FROM agenda  WHERE member_id = '"+  +"'";
        } catch (Exception e) {
        }
    }*/
    public void insertCardStudent(int studentId, String image, String number){
        try {
            String sql = "INSERT INTO carte_etudiant(student_id, carte_image, carte_number) VALUES ("
                    + studentId +", '"+ image +"', '"+ number +"')";
             this.state = connection.createStatement();
            
            this.state.executeUpdate(sql);
            
            return;
            
        } catch (Exception e) {
        }
    }
 
    public boolean logiMember(String email, String password){
        MembreData member; 
        try {
            String sql = "SELECT staffmembers.id_member, staffmembers.first_name, staffmembers.second_name, staffmembers.last_name, "+
				"staffmembers.password, staffmembers.profile, agenda.email, agenda.bureau_id FROM staffmembers, agenda "+
				"WHERE agenda.member_id = staffmembers.id_member AND staffmembers.email = agenda.email AND agenda.email = '"+ email +"' "+
				"AND agenda.password = '"+ password +"' ";
             state = this.connection.createStatement();
            state.executeQuery(sql);
            ResultSet result = state.getResultSet();
            
            if (result.next()) {  
                //return result.getInt("id_member");
                member = new MembreData();
                member.setNom(result.getString("first_name") +" "+ result.getString("second_name") +" "+ result.getString("last_name"));
                member.setBureau(String.valueOf(result.getInt("bureau_id")));
                member.setProfile(result.getString("profile"));
                member.setId(result.getInt("id_member"));
                
                if (this.loginData(result.getInt("bureau_id"))) {
                     UniversalMethod.memberData = member;
                    return true;
                }          
            }
            
        } catch (Exception e) {
        }
        return false;
    }
    public boolean loginData(int bureauId){
        try {
            String sql = "SELECT bureau.type, bureau.bureau_id, bureau.affectation_id, affectation.type_affectation FROM affectation, bureau "+
			"WHERE bureau.affectation_id = affectation.affectation_id AND bureau.bureau_id = "+ bureauId +"";
             state = this.connection.createStatement();
            state.executeQuery(sql);
            //System.out.println("......."+ email +" "+ password);
            ResultSet result = state.getResultSet();
            
            if (result.next()) {  
                //return result.getInt("id_member");
                UniversalMethod.typeAff = result.getString("type_affectation");
                return true;
            }
            
        } catch (Exception e) {
        }
        return false;
    }
    
    public void appPayementInscription(int studentId, String numeroBorderau, String borderaux, String timestamp ){
        try {
            String sql = "INSERT INTO frais_inscription (student_id, numero_bordereau, bordereaux, timestamp) VALUES ("
                    + studentId +", '"+ numeroBorderau +"', '"+ borderaux +"', '"+ timestamp +"')";
             this.state = connection.createStatement();
            
            this.state.executeUpdate(sql);
        

        } catch (Exception e) {
        }
    }
    public void appPayementFraisAcademique(int studentId, String numeroBorderau, String borderaux, String trache, String promotion, String timestamp ){
        try {
            String sql = "INSERT INTO frais_academique (student_id, montant, bordereaux, tranche, promotion, timestamp) VALUES ("
                    + studentId +", '"
                    + numeroBorderau +"', '"
                    + borderaux +"', '"
                    + trache +"', '"
                    + promotion +"', '"
                    + timestamp +"');";
             this.state = connection.createStatement();
            
            this.state.executeUpdate(sql);
        } catch (Exception e) {
        }
    }
    public void appPayementFraisDivers(int studentId, String numeroBorderau, String borderaux, String promotion, String timestamp ){
        try {
            String sql = "INSERT INTO frais_divers (student_id, montant, bordereaux, promotion, timestamp) VALUES ("
                    + studentId +", '"+ numeroBorderau +"', '"+ borderaux +"', '"+ promotion +"', '"+ timestamp +"')";
             this.state = connection.createStatement();
            
            this.state.executeUpdate(sql);
        

        } catch (Exception e) {
            
        }
    }
    public void appPayementAssuranceMaladie(int studentId, String numeroBorderau, String borderaux, String promotion, String timestamp ){
        try {
            String sql = "INSERT INTO assurance_maladie (student_id, montant, bordereaux, promotion, timestamp) VALUES ("
                    + studentId +", '"+ numeroBorderau +"', '"+ borderaux +"', '"+ promotion +"', '"+ timestamp +"')";
             this.state = connection.createStatement();
            
            this.state.executeUpdate(sql);
        

        } catch (Exception e) {
        }
    }
    public int getRegisteredNumber(){
        int number = 0;
        try {
            String sql = "SELECT * FROM etudiant";
               state = this.connection.createStatement();
            state.executeQuery(sql);
            //System.out.println("......."+ email +" "+ password);
            ResultSet result = state.getResultSet();            
           
            while (result.next()) {                
                number++;        
                UniversalMethod.studentDataConf.add(
                        new String[]{
                           result.getString("first_name") +""+ result.getString("second_name") +" "+ result.getString("last_name"),
                           result.getString("profile"),
                           result.getString("faculte"),
                           result.getString("departement"),
                           String.valueOf(result.getInt("id_student")),
                           result.getString("address"),
                           result.getString("matricule"),
                           result.getString("bordereaux"),
                           result.getString("promotion"),
                        });
            }
            return number;
            
        } catch (Exception e) {
        }
        return number;
    }
    public int getConfirmedAndAttenteNumber(int etat){
        int number = 0;
        try {
            UniversalMethod.studentData = new ArrayList<>();
            String sql_staff = "SELECT etudiant.first_name, etudiant.second_name, etudiant.last_name, etudiant.promotion, etudiant.faculte, etudiant.profile, etudiant.departement, "+
                    "etudiant.address, etudiant.contact, etudiant.matricule, etudiant.id_student, frais_inscription.bordereaux AS frais, frais_inscription.numero_bordereau "+
                    "FROM etudiant, frais_inscription WHERE etudiant.etat = "+ etat +" AND etudiant.id_student = frais_inscription.student_id";

            state = this.connection.createStatement();
            state.executeQuery(sql_staff);
            //System.out.println("......."+ email +" "+ password);
            ResultSet result = state.getResultSet();
            while( result.next() ){              

                number++;
            } 

            return number;


        } catch (Exception e) {
        }
        return number;
    }
    public void attenteAndConfirme(int etat, String promotion){
         try {


             String sql = "SELECT etudiant.first_name, etudiant.timestamp, etudiant.second_name, etudiant.last_name, etudiant.promotion, etudiant.faculte,  etudiant.departement, etudiant.profile, etudiant.address,etudiant.contact, etudiant.matricule, etudiant.id_student, frais_inscription.bordereaux , frais_inscription.numero_bordereau FROM etudiant, frais_inscription WHERE etudiant.etat = "+ etat +" AND etudiant.id_student = frais_inscription.student_id AND etudiant.promotion = '"+ promotion +"'";

             state = this.connection.createStatement();
            state.executeQuery(sql);
            
             if (promotion.equals("BAC 1")) {                 
                UniversalMethod.studentDataAttenteBAC1.clear(); 
             }
             else if (promotion.equals("BAC 2")) {                 
                UniversalMethod.studentDataAttenteBAC2.clear();
             }
             else if (promotion.equals("BAC 3")) {                  
                UniversalMethod.studentDataAttenteBAC3.clear();
             }
            //System.out.println("......."+ email +" "+ password);
            ResultSet result = state.getResultSet();
            while( result.next() ){

                    if (promotion.equals("BAC 1")) {            
                        UniversalMethod.studentDataAttenteBAC1.add(
                        new String[]{
                              result.getString("first_name") +""+ result.getString("second_name") +" "+ result.getString("last_name"),
                              result.getString("profile"),
                              result.getString("faculte"),
                              result.getString("departement"),
                              String.valueOf(result.getInt("id_student")),
                              result.getString("address"),
                              result.getString("matricule"),
                              result.getString("bordereaux"),
                              result.getString("promotion"),
                              result.getString("timestamp")
                           });

                    }
                    else if (promotion.equals("BAC 2")) {

                        UniversalMethod.studentDataAttenteBAC2.add(
                        new String[]{
                              result.getString("first_name") +""+ result.getString("second_name") +" "+ result.getString("last_name"),
                              result.getString("profile"),
                              result.getString("faculte"),
                              result.getString("departement"),
                              String.valueOf(result.getInt("id_student")),
                              result.getString("address"),
                              result.getString("matricule"),
                              result.getString("bordereaux"),
                              result.getString("promotion"),
                              result.getString("timestamp")
                           });

                    }
                    else if (promotion.equals("BAC 3")) {
                        UniversalMethod.studentDataAttenteBAC3.add(
                        new String[]{
                              result.getString("first_name") +""+ result.getString("second_name") +" "+ result.getString("last_name"),
                              result.getString("profile"),
                              result.getString("faculte"),
                              result.getString("departement"),
                              String.valueOf(result.getInt("id_student")),
                              result.getString("address"),
                              result.getString("matricule"),
                              result.getString("bordereaux"),
                              result.getString("promotion"),
                              result.getString("timestamp")
                           });                        
                    }      
                }         
         } catch (SQLException e) {
             System.out.println(e.getMessage());
     }
     }
// 
    public void confirmationDePayement(int fraisId, int studentId, String tranche, String numeroBord, String borderau, String promotion, String timestamp){
         try {
             //
             int stateStudent = this.getStudentConfPayement(studentId);

             if ( stateStudent == 0 ) {
                String sql = "INSERT INTO `student_status_payement`( `student_id`, `promotion`, `status`, `timestamp`) VALUES ("
                     + studentId +", '"+ promotion +"', "+ (stateStudent + 1) +", '"+ timestamp +"');";
                this.state = connection.createStatement();            
                this.state.executeUpdate(sql);

                tempFraisAcademique(fraisId, studentId, numeroBord, borderau, tranche, promotion, timestamp);

             } else {
                 this.updateStudentStatus(stateStudent + 1, studentId);
                 tempFraisAcademique(fraisId, studentId, numeroBord, borderau, tranche, promotion, timestamp);

             }
             return;

         } catch (Exception e) {
             System.out.println("Error Message : "+ e.getMessage());
         }
     }
    public int getStudentConfPayement(int studentId){
         int status = 0;
         try {
             String sql = "SELECT * FROM `student_status_payement` WHERE `student_id` = "+ studentId +";";
               state = this.connection.createStatement();
            state.executeQuery(sql);
            //System.out.println("......."+ email +" "+ password);
            ResultSet result = state.getResultSet();
            if( result.next() ){
                status = result.getInt("status");              
            }
            return status;
         } catch (Exception e) {
             System.out.println("Error Message : "+ e.getMessage());
         }
         return status;
     }
    public void updateStudentStatus(int status, int studentid){
         try {
             String sql = "UPDATE `student_status_payement` SET `status` = "+ status +" WHERE `student_id` = "+ studentid +";";

             this.state = connection.createStatement();

            this.state.executeUpdate(sql);
            return;

         } catch (Exception e) {
             System.out.println("Error Message : "+ e.getMessage());
         }
     }
    public void tempFraisAcademique(int frasId, int studentId, String numeroBorderau, String bordereau, String tranche, String promotion, String timestamp){
         try {
             String sql_statement = "INSERT INTO `frais_academique_temp`(`student_id`, `numero_bordereau`, `bordereau`, `tranche`, `promotion`, `timestamp`) VALUES ("
                     + studentId +", '"+ numeroBorderau +"', '"+ bordereau +"', '"+ tranche +"', '"+ promotion +"', '"+ timestamp +"');";
             this.state = connection.createStatement();

            this.state.executeUpdate(sql_statement);
            deleteFraisAcademique(frasId);
            return;

         } catch (Exception e) {
             System.out.println("Error Message : "+ e.getMessage());
         }
     }
    private void deleteFraisAcademique(int fraisId){
         try {
            String sql = "DELETE FROM `frais_academique` WHERE `frais_id` = "+ fraisId +";";
            this.state = connection.createStatement();

            this.state.executeUpdate(sql);

            return;
         } catch (Exception e) {
             System.out.println("Error Message : "+ e.getMessage());
         }
     }
     
    public void confirmationStatusPayementDivers(int fraisId, int studentId, String numeroBord, String borderau, String promotion, String timestamp){
         try {
             int stateDiver = this.getStudentStatusConfirmationDivers(studentId);

             if (stateDiver == 0) {
                String sql = "INSERT INTO `student_status_payement_diver`( `student_id`, `promotion`, `status`, `timestamp`) VALUES ("
                     + studentId +", '"+ promotion +"', "+ (stateDiver + 1) +", '"+ timestamp +"');";
                this.state = connection.createStatement();            
                this.state.executeUpdate(sql);

                tempFraisDivers(fraisId, studentId, numeroBord, borderau, promotion, timestamp);

             } else {
                this.updateStudentStatusFaisDivers(stateDiver + 1, studentId);
                tempFraisDivers(fraisId, studentId, numeroBord, borderau, promotion, timestamp);
             }

            return;

         } catch (Exception e) {
         }
     }
    public int getStudentStatusConfirmationDivers(int studentId){
         int status = 0;
         try {
             String sql = "SELECT * FROM `student_status_payement_diver` WHERE `student_id` = "+ studentId +";";
               state = this.connection.createStatement();
            state.executeQuery(sql);
            //System.out.println("......."+ email +" "+ password);
            ResultSet result = state.getResultSet();
            if( result.next() ){
                status = result.getInt("status");              
            }
            return status;

         } catch (Exception e) {
         }
         return 0;       
     }
    public void updateStudentStatusFaisDivers(int status, int studentId){
         try {
             String sql = "UPDATE `student_status_payement_diver` SET `status` = "+ status +" WHERE `student_id` = "+ studentId +";";
              this.state = connection.createStatement();

            this.state.executeUpdate(sql);

         } catch (Exception e) {
         }
     }
    public void tempFraisDivers(int frasId, int studentId, String numeroBorderau, String bordereau, String promotion, String timestamp){
         try {
             String sql_statement = "INSERT INTO `frais_diver_temp`(`student_id`, `numero_bordereau`, `bordereau`, `promotion`, `timestamp`) VALUES ("
                     + studentId +", '"+ numeroBorderau +"', '"+ bordereau +"', '"+ promotion +"', '"+ timestamp +"');";
              this.state = connection.createStatement();

             this.state.executeUpdate(sql_statement);
             deleteFraisDivers(frasId);

         } catch (Exception e) {
         }
     }
    public void deleteFraisDivers(int fraisId){
         try {
             String sql = "DELETE FROM `frais_divers`  WHERE `frais_id` = "+ fraisId +";";
             this.state = connection.createStatement();

             this.state.executeUpdate(sql);

         } catch (Exception e) {
         }
     }
     
     
     public void confirmationStatusPayementMadelie(int fraisId, int studentId, String numeroBord, String borderau, String promotion, String timestamp){
         try {
             int stateDiver = this.getStudentStatusConfirmationMaladie(studentId);

             if (stateDiver == 0) {
                String sql = "INSERT INTO `student_status_payement_maladie`( `student_id`, `promotion`, `status`, `timestamp`) VALUES ("
                     + studentId +", '"+ promotion +"', "+ (stateDiver + 1) +", '"+ timestamp +"');";
                this.state = connection.createStatement();            
                this.state.executeUpdate(sql);
//int frasId, int studentId, String numeroBorderau, String bordereau, String promotion, String timestamp
                tempFraisMaladie(fraisId, studentId, numeroBord, borderau, promotion, timestamp);

             } else {
                this.updateStudentStatusFaisMaladie((stateDiver + 1), studentId);
                 tempFraisMaladie(fraisId, studentId, numeroBord, borderau, promotion, timestamp);
             }

            return;

         } catch (Exception e) {
         }
     }
     public int getStudentStatusConfirmationMaladie(int studentId){
         int status = 0;
         try {
            String sql = "SELECT * FROM `student_status_payement_maladie` WHERE `student_id` = "+ studentId +"";
            state = this.connection.createStatement();
            state.executeQuery(sql);
            //System.out.println("......."+ email +" "+ password);
            ResultSet result = state.getResultSet();
            if( result.next() ){
                status = result.getInt("status");              
            }
            return status;

         } catch (Exception e) {
         }
         return 0;       
     }
     public void updateStudentStatusFaisMaladie(int status, int studentId){
         try {
             String sql = "UPDATE `student_status_payement_maladie` SET `status` = "+ status +" WHERE `student_id` = "+ studentId +";";
              this.state = connection.createStatement();

            this.state.executeUpdate(sql);

         } catch (Exception e) {
         }
     }
     public void tempFraisMaladie(int frasId, int studentId, String numeroBorderau, String bordereau, String promotion, String timestamp){
         try {
             String sql_statement = "INSERT INTO `frais_maladie_temp`(`student_id`, `numero_bordereau`, `bordereau`, `promotion`, `timestamp`) VALUES ("
                     + studentId +", '"+ numeroBorderau +"', '"+ bordereau +"', '"+ promotion +"', '"+ timestamp +"');";
              this.state = connection.createStatement();

             this.state.executeUpdate(sql_statement);
             deleteFraisMaladie(frasId);

         } catch (Exception e) {
         }
     }
     public void deleteFraisMaladie(int fraisId){
         try {
             String sql = "DELETE FROM `assurance_maladie` WHERE `frais_id` = "+ fraisId +";";
             this.state = connection.createStatement();

             this.state.executeUpdate(sql);

         } catch (Exception e) {
         }
     }


     /*
        *
        *
        *
        *
        *
                                                                    */


    public void getDemandeFraisAcademique(){
         try {
             String sql_staff = "SELECT etudiant.id_student, etudiant.first_name, etudiant.second_name, etudiant.last_name, etudiant.profile, etudiant.faculte, etudiant.matricule, frais_academique.frais_id, frais_academique.montant, frais_academique.bordereaux, frais_academique.tranche, "+
                "frais_academique.promotion, frais_academique.timestamp FROM etudiant, frais_academique WHERE etudiant.id_student = frais_academique.student_id";
             state = this.connection.createStatement();
            state.executeQuery(sql_staff);
            //System.out.println("......."+ email +" "+ password);
            ResultSet result = state.getResultSet();
            UniversalMethod.fraisAcademique.clear();
            while( result.next() ){  
                UniversalMethod.fraisAcademique.add(
                        new FraisAcademiqueStudent(
                                result.getString("first_name") +" "+ result.getString("second_name") +" "+ result.getString("last_name"),
                                result.getString("promotion"), 
                                result.getString("faculte"),
                                result.getString("profile"),
                                result.getString("matricule"), 
                                result.getString("bordereaux"), 
                                result.getString("tranche"),
                                result.getString("montant"), 
                                result.getInt("frais_id"), 
                                result.getInt("id_student"))
                        );                
            }
            return;

         } catch (Exception e) {
             System.err.println("An error occured : "+ e.getMessage());
         }
     }
    public void getDemandeFraisDivers(){
         try {
             String sql_staff = "SELECT etudiant.id_student, etudiant.first_name, etudiant.second_name, etudiant.last_name, etudiant.profile, etudiant.faculte, etudiant.matricule, frais_divers.frais_id, frais_divers.montant, frais_divers.bordereaux, "+
                "frais_divers.promotion, frais_divers.timestamp FROM etudiant, frais_divers WHERE etudiant.id_student = frais_divers.student_id";
             state = this.connection.createStatement();
            state.executeQuery(sql_staff);
            //System.out.println("......."+ email +" "+ password);
            ResultSet result = state.getResultSet();
            UniversalMethod.fraisDivers.clear();
            while( result.next() ){  
                UniversalMethod.fraisDivers.add(
                        new FraisDiversStudent(
                                result.getString("first_name") +" "+ result.getString("second_name") +" "+ result.getString("last_name"),
                                result.getString("promotion"), 
                                result.getString("faculte"),
                                result.getString("profile"),
                                result.getString("matricule"), 
                                result.getString("bordereaux"), 
                                result.getString("montant"), 
                                result.getInt("frais_id"), 
                                result.getInt("id_student"))
                        );                
            }
            return;

         } catch (Exception e) {
             System.err.println("An error occured : "+ e.getMessage());
         }
     }
    public void getDemandeFraisMaladie(){
         try {
             String sql_staff = "SELECT etudiant.id_student, etudiant.first_name, etudiant.second_name, etudiant.last_name, etudiant.profile, etudiant.faculte, etudiant.matricule, assurance_maladie.frais_id, assurance_maladie.montant, assurance_maladie.bordereaux, " +
                "assurance_maladie.promotion, assurance_maladie.timestamp FROM etudiant, assurance_maladie WHERE etudiant.id_student = assurance_maladie.student_id";
              state = this.connection.createStatement();
            state.executeQuery(sql_staff);
            //System.out.println("......."+ email +" "+ password);
            ResultSet result = state.getResultSet();
            UniversalMethod.fraisMaladie.clear();
            while( result.next() ){  
                UniversalMethod.fraisMaladie.add(
                        new FraisMaladieStudent(
                                result.getString("first_name") +" "+ result.getString("second_name") +" "+ result.getString("last_name"),
                                result.getString("promotion"), 
                                result.getString("faculte"),
                                result.getString("profile"),
                                result.getString("matricule"), 
                                result.getString("bordereaux"), 
                                result.getString("montant"), 
                                result.getInt("frais_id"), 
                                result.getInt("id_student"))
                        ); 
                System.out.println("Get Data : "+ result.getString("first_name") +" "+ result.getString("second_name") +" "+ result.getString("last_name"));
            }
            return;

         } catch (Exception e) {
             System.err.println("An error occured : "+ e.getMessage());
         }
     }
    
    public int getRegisteredStudentNumberAcademique(){
          int number = 0;
        try {
            String sql = "SELECT DISTINCT(student_id) FROM frais_academique_temp;";
            state = this.connection.createStatement();
            state.executeQuery(sql);
            //System.out.println("......."+ email +" "+ password);
            ResultSet result = state.getResultSet();         
           
            while (result.next()) {                
                number++; 
            }
            sql = "SELECT DISTINCT(student_id) FROM frais_academique;";
            state = this.connection.createStatement();
            state.executeQuery(sql);
            //System.out.println("......."+ email +" "+ password);
            result = state.getResultSet();      
           
            while (result.next()) {                
                number++; 
            }
            return number;
            
        } catch (Exception e) {
            System.out.println(""+ e.getMessage());
        }
        return number;
    }
    public int statisticAyantPaye(){
        int number = 0;
        try {
            String sql = "SELECT * FROM student_status_payement WHERE status >= 1";
               state = this.connection.createStatement();
            state.executeQuery(sql);
            //System.out.println("......."+ email +" "+ password);
            ResultSet result = state.getResultSet();         
           
            while (result.next()) {                
                number++; 
            }           
            return number;
        } catch (Exception e) {
        }
        return 0;
    }
    public int enProgressionDePayement(){
        int number = 0;
        try {
            String sql = "SELECT DISTINCT(student_id) FROM student_status_payement WHERE status > 0";
              state = this.connection.createStatement();
            state.executeQuery(sql);
            //System.out.println("......."+ email +" "+ password);
            ResultSet result = state.getResultSet();         
           
            while (result.next()) {                
                number++; 
            }           
            return number;
        } catch (Exception e) {
        }
        return number;
    }
    public int finiPaye(){
        int number = 0;
        try {
            String sql = "SELECT * FROM student_status_payement WHERE status >= 3";
              state = this.connection.createStatement();
            state.executeQuery(sql);
            //System.out.println("......."+ email +" "+ password);
            ResultSet result = state.getResultSet();         
           
            while (result.next()) {                
                number++; 
            }           
            return number;
        } catch (Exception e) {
        }
        return number;
    }
    public int noPaye(){
        int number = 0;
        try {
            String sql = "SELECT DISTINCT(student_id) FROM frais_academique";
              state = this.connection.createStatement();
            state.executeQuery(sql);
            //System.out.println("......."+ email +" "+ password);
            ResultSet result = state.getResultSet();         
           
            while (result.next()) {                
                number++; 
            }           
            return number;
        } catch (Exception e) {
        }
        return number;
    }
    public void getProgressionMensuel(){
        try {
            String sql = "SELECT * FROM student_status_payement ORDER BY timestamp ASC";
              state = this.connection.createStatement();
            state.executeQuery(sql);
            //System.out.println("......."+ email +" "+ password);
            ResultSet result = state.getResultSet();         
            int dateN = 0, i = 0;
            while (result.next()) {                
                String[] timestamp = result.getString("timestamp").split(" ");
                String[] timeDates = timestamp[0].split("-");
                String[] dateNow = UniversalMethod.getCurrentDateAndTime().split(" ");
                if (Integer.parseInt(dateNow[0].split("-")[1]) == Integer.parseInt(timeDates[1])) {
                    if (date.size() == 0) {
                        i++;                    
                        date.add(Integer.parseInt(timeDates[2]));
                        number.add(1);
                    } else {

                         if ( (date.get(i - 1) != Integer.parseInt(timeDates[2])) ) {
                            i++;
                            date.add(Integer.parseInt(timeDates[2]));
                            number.add(1);
                        }
                        else{
                            number.set(i - 1, number.get(i - 1) + 1);
                        }
                    }
                }
                
            } 
            
        } catch (Exception e) {
        }
    }
    public void getProgressionAnnuel(){
        try {
            String sql = "SELECT * FROM student_status_payement ORDER BY timestamp ASC";
              state = this.connection.createStatement();
            state.executeQuery(sql);
            //System.out.println("......."+ email +" "+ password);
            ResultSet result = state.getResultSet();         
            int dateN = 0, i = 0;
            while (result.next()) {                
                String[] timestamp = result.getString("timestamp").split(" ");
                String[] timeDates = timestamp[0].split("-");
                if (dateGeneral.size() == 0) {
                    i++;
                    dateGeneral.add(Integer.parseInt(timeDates[1]));
                    numberGeneral.add(1);
                } else {
                     if ( (dateGeneral.get(i - 1) != Integer.parseInt(timeDates[1])) ) {
                        i++;
                        dateGeneral.add(Integer.parseInt(timeDates[1]));
                        numberGeneral.add(1);
                    }
                    else{
                        numberGeneral.set(i - 1, numberGeneral.get(i - 1) + 1);
                    }
                }
            } 
            
        } catch (Exception e) {
        }
    }
    public int getRegisteredStudentNumberAcademiqueConfirmed(){
          int number = 0;
        try {
            String sql = "SELECT * FROM student_status_payement;";
               state = this.connection.createStatement();
            state.executeQuery(sql);
            //System.out.println("......."+ email +" "+ password);
            ResultSet result = state.getResultSet();         
           
            while (result.next()) {                
                number++; 
            }           
            return number;
            
        } catch (Exception e) {
             System.out.println(""+ e.getMessage());
        }
        return number;
    }
     public int getRegisteredStudentNumberAcademiqueAttente(){
          int number = 0;
        try {
            String sql = "SELECT * FROM frais_academique;";
               state = this.connection.createStatement();
            state.executeQuery(sql);
            //System.out.println("......."+ email +" "+ password);
            ResultSet result = state.getResultSet();         
           
            while (result.next()) {                
                number++; 
            }           
            return number;
            
        } catch (Exception e) {
             System.out.println(""+ e.getMessage());
        }
        return number;
    }
    public int getRegisteredStudentDiversConfirmed(){
        int number = 0;
        try {
            String sql_staff = "SELECT etudiant.first_name, etudiant.second_name, etudiant.last_name, etudiant.promotion, etudiant.faculte, etudiant.profile, "+
                "etudiant.address, etudiant.contact, etudiant.matricule, etudiant.id_student, student_status_payement_diver.status "+
                "FROM etudiant, student_status_payement_diver WHERE etudiant.id_student = student_status_payement_diver.student_id";
            state = this.connection.createStatement();
            state.executeQuery(sql_staff);
            //System.out.println("......."+ email +" "+ password);
            ResultSet result = state.getResultSet();         
           UniversalMethod.fraisDiversConfirme.clear();
            while (result.next()) { 
                UniversalMethod.fraisDiversConfirme.add(
                        new FraisDiversStudent(
                                result.getString("first_name") +""+ result.getString("second_name") +" "+ result.getString("last_name"),
                                result.getString("promotion"), 
                                result.getString("faculte"), 
                                "", 
                                "", 
                                "", 
                                result.getString("montant"), 
                                0, 
                                0
                        ));
                        
                number++; 
            }             
            return number;
            
        } catch (Exception e) {
        }
        return number;
    }
    public int getRegisteredStudentMaladieConfirme(){
        int number = 0;
        try {
            String sql_staff = "SELECT etudiant.first_name, etudiant.second_name, etudiant.last_name, etudiant.promotion, etudiant.faculte, etudiant.departement, "+
                "etudiant.address, etudiant.contact, etudiant.id_student, student_status_payement_maladie.status "+
                "FROM etudiant, student_status_payement_maladie WHERE etudiant.id_student = student_status_payement_maladie.student_id";
            state = this.connection.createStatement();
            state.executeQuery(sql_staff);
            //System.out.println("......."+ email +" "+ password);
            ResultSet result = state.getResultSet();         
           
            UniversalMethod.fraisMaladieConfirme.clear();
            while (result.next()) { 
                UniversalMethod.fraisMaladieConfirme.add(
                        new FraisMaladieStudent(
                                result.getString("first_name") +""+ result.getString("second_name") +" "+ result.getString("last_name"),
                                result.getString("promotion"), 
                                result.getString("faculte"), 
                                "", 
                                "", 
                                "", 
                                result.getString("montant"), 
                                0, 
                                0
                        ));
                        
                number++; 
            }                
            return number;
            
        } catch (Exception e) {
        }
        return number;
    }
    
    public int getRegisteredStudentMaladieAttente(){
        int number = 0;
        try {
            String sql_staff = "SELECT etudiant.first_name, etudiant.second_name, etudiant.last_name, etudiant.promotion, etudiant.faculte,  " +
                "etudiant.address, etudiant.departement, etudiant.id_student, assurance_maladie.montant " +
                "FROM etudiant, assurance_maladie WHERE etudiant.id_student = assurance_maladie.student_id";
            state = this.connection.createStatement();
            state.executeQuery(sql_staff);
            //System.out.println("......."+ email +" "+ password);
            ResultSet result = state.getResultSet();         
             UniversalMethod.fraisMaladieAttente.clear();
            while (result.next()) { 
                UniversalMethod.fraisMaladieAttente.add(
                        new FraisMaladieStudent(
                                result.getString("first_name") +""+ result.getString("second_name") +" "+ result.getString("last_name"),
                                result.getString("promotion"), 
                                result.getString("faculte"), 
                                "", 
                                "", 
                                "", 
                                result.getString("montant"), 
                                0, 
                                0
                        ));
                        
                number++; 
            }     
            return number;
            
        } catch (Exception e) {
        }
        return number;
    }
    public int getRegisteredStudentDiversAttente(){
        int number = 0;
        try {
            String sql_staff = "SELECT etudiant.first_name, etudiant.second_name, etudiant.last_name, etudiant.promotion, etudiant.faculte, etudiant.departement, "+
                "etudiant.address, etudiant.contact,  etudiant.id_student, frais_divers.montant "+
                "FROM etudiant, frais_divers WHERE etudiant.id_student = frais_divers.student_id";
            state = this.connection.createStatement();
            state.executeQuery(sql_staff);
            //System.out.println("......."+ email +" "+ password);
            ResultSet result = state.getResultSet();         
            UniversalMethod.fraisDiversAttente.clear();
            while (result.next()) { 
                UniversalMethod.fraisDiversAttente.add(
                        new FraisDiversStudent(
                                result.getString("first_name") +""+ result.getString("second_name") +" "+ result.getString("last_name"),
                                result.getString("promotion"), 
                                result.getString("faculte"), 
                                "", 
                                "", 
                                "", 
                                result.getString("montant"), 
                                0, 
                                0
                        ));
                        
                number++; 
            }     
            
        } catch (Exception e) {
        }
        return number;
    }
    public void getAcademiqueConfirmed(){
        try {
            String sql_staff = "SELECT etudiant.first_name, etudiant.second_name, etudiant.last_name, "
                    + "etudiant.promotion, etudiant.faculte, etudiant.profile, etudiant.address, "
                    + "etudiant.contact, etudiant.matricule, etudiant.id_student, student_status_payement.status "
                    + "FROM etudiant, student_status_payement WHERE etudiant.id_student = student_status_payement.student_id";
            state = this.connection.createStatement();
            state.executeQuery(sql_staff);
            ResultSet result = state.getResultSet();         
           
            UniversalMethod.fraisAcademiqueConfirme.clear();
            while( result.next() ){  
                UniversalMethod.fraisAcademiqueConfirme.add(
                        new FraisAcademiqueStudent(
                                result.getString("first_name") +" "+ result.getString("second_name") +" "+ result.getString("last_name"),
                                result.getString("promotion"), 
                                result.getString("faculte"),
                                result.getString("profile"),
                                result.getString("matricule"),
                                "", 
                                "",
                                "", 
                                0, 
                                result.getInt("id_student"))
                        );                
            }
            return;
            
        } catch (Exception e) {
        }
    }

    public int getNewAlert() {
        //To change body of generated methods, choose Tools | Templates.
         try {
            String sql_staff = "SELECT * FROM messages ORDER BY timestamp DESC";
            state = this.connection.createStatement();
            state.executeQuery(sql_staff);
            ResultSet result = state.getResultSet();         
           //`message_id`, `message_content`, `sender_name`, `student_name`, `student_carte_id`, `faculte`, 
           //`departement`, `promotion`, `timestamp`
            UniversalMethod.messages.clear();
            int i = 0;
            while( result.next() ){  
                UniversalMethod.messages.add(
                        new String[]{
                            result.getString("message_content"),
                            result.getString("sender_name"),
                            result.getString("student_name"),
                            result.getString("student_carte_id"),
                            result.getString("faculte"),
                            result.getString("departement"),
                            result.getString("promotion"),
                            result.getString("timestamp")                            
                        });
                i++;
            }
            return i;
            
        } catch (Exception e) {
             System.out.println("Error Message : "+ e.getMessage());
        }
         return 0;
    }

    public void getWeeklyReport() {
        //To change body of generated methods, choose Tools | Templates.
        try {
            
        } catch (Exception e) {
            System.out.println("Error message : "+ e.getMessage());
        }
    }

    public void getMonthlyReport() {
        //To change body of generated methods, choose Tools | Templates.
        try {
            
        } catch (Exception e) {
            System.out.println("Error message : "+ e.getMessage());
        }
    }
     public void getWeeklyReportMaladie(){
        int number = 0;
        try {
              //;
           //UniversalMethod.report.clear();
           StringBuilder sb = new StringBuilder();
           File file = new File(FileSystemView.getFileSystemView().getDefaultDirectory().getPath().concat("\\UPAC Report"));
            if (file.mkdir()) {
                File fileName = new File(file.getAbsolutePath().concat("\\Rapport_du_"+ UniversalMethod.getCurrentDateAndTime() +".csv"));
                try (PrintWriter writer = new PrintWriter(fileName)) {                       
                        sb.append("Guichet,");
                        sb.append("Assurance Maladie,");
                        sb.append(",,,,");                       
                        sb.append('\n');                        
                        sb.append("Date,");
                        sb.append(""+ UniversalMethod.getCurrentDateAndTime() +",");
                        sb.append(",,,,");                       
                        sb.append('\n');
                        sb.append(",,,,,,");                        
                        sb.append('\n');
                        sb.append("Nom,");
                        sb.append("Faculte,");
                        sb.append("Departement,");
                        sb.append("Promotion,");
                        sb.append("Status de Payement,");
                        sb.append("Date,");
                        sb.append('\n');
                        
                     String sql_staff = "SELECT etudiant.first_name, etudiant.second_name, etudiant.last_name, etudiant.promotion, etudiant.faculte, "+
                        "etudiant.departement, student_status_payement_maladie.timestamp"+
                        "FROM etudiant, student_status_payement_maladie WHERE etudiant.id_student = student_status_payement_maladie.student_id;";
                    Statement state = this.connection.createStatement();
                    state.executeQuery(sql_staff);
                    //System.out.println("......."+ email +" "+ password);
                    ResultSet result = state.getResultSet();  
                        
                        while (result.next()) {
                             if (UniversalMethod.checkDatePaid(UniversalMethod.getCurrentDateAndTime(), result.getString("timestamp"))) {
                                    sb.append(""+  result.getString("first_name") +" "+ result.getString("second_name") +" "+ result.getString("last_name") +",");
                                    sb.append(""+  result.getString("faculte") +",");
                                    sb.append(""+  result.getString("departement") +",");
                                    sb.append(""+  result.getString("promotion") +",");
                                    sb.append("Paye,");
                                    sb.append(""+  result.getString("timestamp") +",");
                                    sb.append('\n');
                             }                           
                        }  
                        String sql_s = "SELECT etudiant.first_name, etudiant.second_name, etudiant.last_name, etudiant.promotion, etudiant.faculte, etudiant.departement, " +
                            "etudiant.contact, assurance_maladie.timestamp " +
                            "FROM etudiant, assurance_maladie WHERE etudiant.id_student = assurance_maladie.student_id";
                        Statement state1 = this.connection.createStatement();
                        state1.executeQuery(sql_s);
                        //System.out.println("......."+ email +" "+ password);
                        ResultSet result1 = state.getResultSet(); 
                        while (result1.next()) {
                             if (UniversalMethod.checkDatePaid(UniversalMethod.getCurrentDateAndTime(), result1.getString("timestamp"))) {
                                    sb.append(""+  result1.getString("first_name") +" "+ result1.getString("second_name") +" "+ result1.getString("last_name") +",");
                                    sb.append(""+  result1.getString("faculte") +",");
                                    sb.append(""+  result1.getString("departement") +",");
                                    sb.append(""+  result1.getString("promotion") +",");
                                    sb.append("Pas Approuve,");
                                    sb.append(""+  result1.getString("timestamp") +",");
                                    sb.append('\n');
                             }                           
                        }  
                        writer.write(sb.toString());
                        writer.flush();
                        writer.close();
                        
                        System.out.println("Well done serious!");

                      } catch (FileNotFoundException e) {
                            System.out.println(e.getMessage());
                      }
            }
            else{
                if (file.isDirectory()) {
                    String name = (((UniversalMethod.getCurrentDateAndTime().replace("-", "_")).replace(":", "_")).replace(" ", "_"));
                     File fileName = new File(file.getAbsolutePath().concat("\\Rapport_du_"+ name +".csv"));
                try (PrintWriter writer = new PrintWriter(fileName)) {                       
                        sb.append("Guichet,");
                        sb.append("Assurance Maladie,");
                        sb.append(",,,,,");                     
                        sb.append('\n');
                        
                        sb.append("Date,");
                        sb.append(""+ UniversalMethod.getCurrentDateAndTime() +",");
                        sb.append(",,,,");
                        sb.append('\n');
                        sb.append(",,,,,,");
                        sb.append('\n');
                        sb.append("Nom,");
                        sb.append("Faculte,");
                        sb.append("Departement,");
                        sb.append("Promotion,");
                        sb.append("Status de Payement,");
                        sb.append("Date");
                        
                        sb.append('\n');
                         String sql_staff = "SELECT etudiant.first_name, etudiant.second_name, etudiant.last_name, etudiant.promotion, etudiant.faculte, "+
                        "etudiant.departement, student_status_payement_maladie.timestamp "+
                        "FROM etudiant, student_status_payement_maladie WHERE etudiant.id_student = student_status_payement_maladie.student_id;";
                    Statement state = this.connection.createStatement();
                    state.executeQuery(sql_staff);
                    //System.out.println("......."+ email +" "+ password);
                    ResultSet result = state.getResultSet();  
                        while (result.next()) {
                             if (UniversalMethod.checkDatePaid(UniversalMethod.getCurrentDateAndTime(), result.getString("timestamp"))) {
                                    sb.append(""+  result.getString("first_name") +" "+ result.getString("second_name") +" "+ result.getString("last_name") +",");
                                    sb.append(""+  result.getString("faculte") +",");
                                    sb.append(""+  result.getString("departement") +",");
                                    sb.append(""+  result.getString("promotion") +",");
                                    sb.append("Paye,");
                                    sb.append(""+  result.getString("timestamp") +"");
                                    sb.append('\n');
                             }                           
                        }
                        //state.close();
                        String sql_s = "SELECT etudiant.first_name, etudiant.second_name, etudiant.last_name, etudiant.promotion, etudiant.faculte, etudiant.departement, etudiant.contact, assurance_maladie.timestamp FROM etudiant, assurance_maladie WHERE etudiant.id_student = assurance_maladie.student_id;";
                        Statement state1 = connection.createStatement();
                        state1.executeQuery(sql_s);
                        
                        ResultSet result1 = state1.getResultSet(); 
                        //System.out.println("......."+ result1.getFetchSize());
                        while (result1.next()) {
                            //System.out.println(".......Data : ====>>>> "+ result1.getString("first_name") );
                             if (UniversalMethod.checkDatePaid(UniversalMethod.getCurrentDateAndTime(), result1.getString("timestamp"))) {
                                    sb.append(""+  result1.getString("first_name") +" "+ result1.getString("second_name") +" "+ result1.getString("last_name") +",");
                                    sb.append(""+  result1.getString("faculte") +",");
                                    sb.append(""+  result1.getString("departement") +",");
                                    sb.append(""+  result1.getString("promotion") +",");
                                    sb.append("Pas Approuve,");
                                    sb.append(""+  result1.getString("timestamp") +"");
                                    sb.append('\n');
                             }                           
                        }
                        writer.write(sb.toString());
                        writer.flush();
                        writer.close();
                        
                        System.out.println("Well done!");

                      } catch (FileNotFoundException e) {
                            System.out.println(e.getMessage());
                      }
                    
                }
            }
                   
            return;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        JOptionPane.showMessageDialog(null, "Le rapport a bien ete telecharger!!!");
        return;
    }
    
    public void getMonthlyReportMaladie(){
        int number = 0;
        try {
              //;
           //UniversalMethod.report.clear();
           StringBuilder sb = new StringBuilder();
           File file = new File(FileSystemView.getFileSystemView().getDefaultDirectory().getPath().concat("\\UPAC Report"));
            if (file.mkdir()) {
                File fileName = new File(file.getAbsolutePath().concat("\\Rapport_du_"+ UniversalMethod.getCurrentDateAndTime() +".csv"));
                try (PrintWriter writer = new PrintWriter(fileName)) {                       
                        sb.append("Guichet,");
                        sb.append("Assurance Maladie,");
                        sb.append(",,,,");                       
                        sb.append('\n');                        
                        sb.append("Date,");
                        sb.append(""+ UniversalMethod.getCurrentDateAndTime() +",");
                        sb.append(",,,,");                       
                        sb.append('\n');
                        sb.append(",,,,,,");                        
                        sb.append('\n');
                        sb.append("Nom,");
                        sb.append("Faculte,");
                        sb.append("Departement,");
                        sb.append("Promotion,");
                        sb.append("Status de Payement,");
                        sb.append("Date,");
                        sb.append('\n');
                        
                     String sql_staff = "SELECT etudiant.first_name, etudiant.second_name, etudiant.last_name, etudiant.promotion, etudiant.faculte, "+
                        "etudiant.departement, student_status_payement_maladie.timestamp"+
                        "FROM etudiant, student_status_payement_maladie WHERE etudiant.id_student = student_status_payement_maladie.student_id;";
                    Statement state = this.connection.createStatement();
                    state.executeQuery(sql_staff);
                    //System.out.println("......."+ email +" "+ password);
                    ResultSet result = state.getResultSet();  
                        
                        while (result.next()) {
                             if (UniversalMethod.checkDatePaidMonthly(UniversalMethod.getCurrentDateAndTime(), result.getString("timestamp"))) {
                                    sb.append(""+  result.getString("first_name") +" "+ result.getString("second_name") +" "+ result.getString("last_name") +",");
                                    sb.append(""+  result.getString("faculte") +",");
                                    sb.append(""+  result.getString("departement") +",");
                                    sb.append(""+  result.getString("promotion") +",");
                                    sb.append("Paye,");
                                    sb.append(""+  result.getString("timestamp") +",");
                                    sb.append('\n');
                             }                           
                        }  
                        String sql_s = "SELECT etudiant.first_name, etudiant.second_name, etudiant.last_name, etudiant.promotion, etudiant.faculte, etudiant.departement, " +
                            "etudiant.contact, assurance_maladie.timestamp " +
                            "FROM etudiant, assurance_maladie WHERE etudiant.id_student = assurance_maladie.student_id";
                        Statement state1 = this.connection.createStatement();
                        state1.executeQuery(sql_s);
                        //System.out.println("......."+ email +" "+ password);
                        ResultSet result1 = state.getResultSet(); 
                        while (result1.next()) {
                             if (UniversalMethod.checkDatePaidMonthly(UniversalMethod.getCurrentDateAndTime(), result1.getString("timestamp"))) {
                                    sb.append(""+  result1.getString("first_name") +" "+ result1.getString("second_name") +" "+ result1.getString("last_name") +",");
                                    sb.append(""+  result1.getString("faculte") +",");
                                    sb.append(""+  result1.getString("departement") +",");
                                    sb.append(""+  result1.getString("promotion") +",");
                                    sb.append("Pas Approuve,");
                                    sb.append(""+  result1.getString("timestamp") +",");
                                    sb.append('\n');
                             }                           
                        }  
                        writer.write(sb.toString());
                        writer.flush();
                        writer.close();
                        
                        System.out.println("Well done serious!");

                      } catch (FileNotFoundException e) {
                            System.out.println(e.getMessage());
                      }
            }
            else{
                if (file.isDirectory()) {
                    String name = (((UniversalMethod.getCurrentDateAndTime().replace("-", "_")).replace(":", "_")).replace(" ", "_"));
                     File fileName = new File(file.getAbsolutePath().concat("\\Rapport_du_"+ name +".csv"));
                try (PrintWriter writer = new PrintWriter(fileName)) {                       
                        sb.append("Guichet,");
                        sb.append("Assurance Maladie,");
                        sb.append(",,,,,");                     
                        sb.append('\n');
                        
                        sb.append("Date,");
                        sb.append(""+ UniversalMethod.getCurrentDateAndTime() +",");
                        sb.append(",,,,");
                        sb.append('\n');
                        sb.append(",,,,,,");
                        sb.append('\n');
                        sb.append("Nom,");
                        sb.append("Faculte,");
                        sb.append("Departement,");
                        sb.append("Promotion,");
                        sb.append("Status de Payement,");
                        sb.append("Date");
                        
                        sb.append('\n');
                         String sql_staff = "SELECT etudiant.first_name, etudiant.second_name, etudiant.last_name, etudiant.promotion, etudiant.faculte, "+
                        "etudiant.departement, student_status_payement_maladie.timestamp "+
                        "FROM etudiant, student_status_payement_maladie WHERE etudiant.id_student = student_status_payement_maladie.student_id;";
                    Statement state = this.connection.createStatement();
                    state.executeQuery(sql_staff);
                    //System.out.println("......."+ email +" "+ password);
                    ResultSet result = state.getResultSet();  
                        while (result.next()) {
                             if (UniversalMethod.checkDatePaidMonthly(UniversalMethod.getCurrentDateAndTime(), result.getString("timestamp"))) {
                                    sb.append(""+  result.getString("first_name") +" "+ result.getString("second_name") +" "+ result.getString("last_name") +",");
                                    sb.append(""+  result.getString("faculte") +",");
                                    sb.append(""+  result.getString("departement") +",");
                                    sb.append(""+  result.getString("promotion") +",");
                                    sb.append("Paye,");
                                    sb.append(""+  result.getString("timestamp") +"");
                                    sb.append('\n');
                             }                           
                        }
                        //state.close();
                        String sql_s = "SELECT etudiant.first_name, etudiant.second_name, etudiant.last_name, etudiant.promotion, etudiant.faculte, etudiant.departement, etudiant.contact, assurance_maladie.timestamp FROM etudiant, assurance_maladie WHERE etudiant.id_student = assurance_maladie.student_id;";
                        Statement state1 = connection.createStatement();
                        state1.executeQuery(sql_s);
                        
                        ResultSet result1 = state1.getResultSet(); 
                        //System.out.println("......."+ result1.getFetchSize());
                        while (result1.next()) {
                            //System.out.println(".......Data : ====>>>> "+ result1.getString("first_name") );
                             if (UniversalMethod.checkDatePaidMonthly(UniversalMethod.getCurrentDateAndTime(), result1.getString("timestamp"))) {
                                    sb.append(""+  result1.getString("first_name") +" "+ result1.getString("second_name") +" "+ result1.getString("last_name") +",");
                                    sb.append(""+  result1.getString("faculte") +",");
                                    sb.append(""+  result1.getString("departement") +",");
                                    sb.append(""+  result1.getString("promotion") +",");
                                    sb.append("Pas Approuve,");
                                    sb.append(""+  result1.getString("timestamp") +"");
                                    sb.append('\n');
                             }                           
                        }
                        writer.write(sb.toString());
                        writer.flush();
                        writer.close();
                        
                        System.out.println("Well done!");

                      } catch (FileNotFoundException e) {
                            System.out.println(e.getMessage());
                      }
                    
                }
            }
                   
            return;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        JOptionPane.showMessageDialog(null, "Le rapport a bien ete telecharger!!!");
        return;
    }

   
    public void getWeeklyReportAcademique(){
        // int number = 0;
        try {
              //;
           //UniversalMethod.report.clear();
           StringBuilder sb = new StringBuilder();
           File file = new File(FileSystemView.getFileSystemView().getDefaultDirectory().getPath().concat("\\UPAC Report"));
            if (file.mkdir()) {
                File fileName = new File(file.getAbsolutePath().concat("\\Serivice_Academique_Rapport_du_"+ UniversalMethod.getCurrentDateAndTime() +".csv"));
                try (PrintWriter writer = new PrintWriter(fileName)) {                       
                        sb.append("Guichet,");
                        sb.append("Assurance Maladie,");
                        sb.append(",,,,,");                       
                        sb.append('\n');                        
                        sb.append("Date,");
                        sb.append(""+ UniversalMethod.getCurrentDateAndTime() +",");
                        sb.append(",,,,,");                       
                        sb.append('\n');
                        sb.append(",,,,,,,");                        
                        sb.append('\n');
                        sb.append("Nom,");
                        sb.append("Faculte,");
                        sb.append("Departement,");
                        sb.append("Promotion,");
                        sb.append("Tranche,");
                        sb.append("Status de Payement,");
                        sb.append("Date,");
                        sb.append('\n');
                        
                     String sql_staff = "SELECT etudiant.first_name, etudiant.second_name, etudiant.last_name, etudiant.faculte, \n" +
"                    etudiant.departement, etudiant.promotion, etudiant.timestamp, frais_academique_temp.student_id, frais_academique_temp.tranche FROM etudiant, frais_academique_temp"
                                 + " WHERE etudiant.id_student = frais_academique_temp.student_id;";
                    Statement state = this.connection.createStatement();
                    state.executeQuery(sql_staff);
                    //System.out.println("......."+ email +" "+ password);
                    ResultSet result = state.getResultSet();  
                        
                        while (result.next()) {
                             if (UniversalMethod.checkDatePaid(UniversalMethod.getCurrentDateAndTime(), result.getString("timestamp"))) {
                                    sb.append(""+  result.getString("first_name") +" "+ result.getString("second_name") +" "+ result.getString("last_name") +",");
                                    sb.append(""+  result.getString("faculte") +",");
                                    sb.append(""+  result.getString("departement") +",");
                                    sb.append(""+  result.getString("promotion") +",");
                                    sb.append(""+  result.getString("tranche") +",");
                                    sb.append("Paye,");
                                    sb.append(""+  result.getString("timestamp") +",");
                                    sb.append('\n');
                             }                           
                        }  
                        String sql_s = "SELECT etudiant.first_name, etudiant.second_name, etudiant.last_name, etudiant.faculte, \n" +
"                    etudiant.departement, etudiant.promotion, etudiant.timestamp, frais_academique.student_id, frais_academique.tranche  FROM etudiant, frais_academique"
                                 + " WHERE etudiant.id_student = frais_academique.student_id;";
                        Statement state1 = this.connection.createStatement();
                        state1.executeQuery(sql_s);
                        //System.out.println("......."+ email +" "+ password);
                        ResultSet result1 = state.getResultSet(); 
                        while (result1.next()) {
                             if (UniversalMethod.checkDatePaid(UniversalMethod.getCurrentDateAndTime(), result1.getString("timestamp"))) {
                                    sb.append(""+  result1.getString("first_name") +" "+ result1.getString("second_name") +" "+ result1.getString("last_name") +",");
                                    sb.append(""+  result1.getString("faculte") +",");
                                    sb.append(""+  result1.getString("departement") +",");
                                    sb.append(""+  result1.getString("promotion") +",");
                                    sb.append(""+  result1.getString("tranche") +",");
                                    sb.append("Pas Approuve,");
                                    sb.append(""+  result1.getString("timestamp") +",");
                                    sb.append('\n');
                             }                           
                        }  
                        writer.write(sb.toString());
                        writer.flush();
                        writer.close();
                        
                        System.out.println("Well done serious!");

                      } catch (FileNotFoundException e) {
                            System.out.println(e.getMessage());
                      }
            }
            else{
                if (file.isDirectory()) {
                    String name = (((UniversalMethod.getCurrentDateAndTime().replace("-", "_")).replace(":", "_")).replace(" ", "_"));
                     File fileName = new File(file.getAbsolutePath().concat("\\Serivice_Academique_Rapport_du_"+ name +".csv"));
                try (PrintWriter writer = new PrintWriter(fileName)) {                       
                        sb.append("Guichet,");
                        sb.append("Assurance Maladie,");
                        sb.append(",,,,,,");                     
                        sb.append('\n');
                        
                        sb.append("Date,");
                        sb.append(""+ UniversalMethod.getCurrentDateAndTime() +",");
                        sb.append(",,,,,");
                        sb.append('\n');
                        sb.append(",,,,,,,");
                        sb.append('\n');
                        sb.append("Nom,");
                        sb.append("Faculte,");
                        sb.append("Departement,");
                        sb.append("Promotion,");
                        sb.append("Tranche,");
                        sb.append("Status de Payement,");
                        sb.append("Date");
                        
                        sb.append('\n');
                         String sql_staff = "SELECT etudiant.first_name, etudiant.second_name, etudiant.last_name, etudiant.faculte, \n" +
"                    etudiant.departement, etudiant.promotion, etudiant.timestamp, frais_academique_temp.student_id, frais_academique_temp.tranche FROM etudiant, frais_academique_temp"
                                 + " WHERE etudiant.id_student = frais_academique_temp.student_id;";
                    Statement state = this.connection.createStatement();
                    state.executeQuery(sql_staff);
                    //System.out.println("......."+ email +" "+ password);
                    ResultSet result = state.getResultSet();  
                        while (result.next()) {
                             if (UniversalMethod.checkDatePaid(UniversalMethod.getCurrentDateAndTime(), result.getString("timestamp"))) {
                                    sb.append(""+  result.getString("first_name") +" "+ result.getString("second_name") +" "+ result.getString("last_name") +",");
                                    sb.append(""+  result.getString("faculte") +",");
                                    sb.append(""+  result.getString("departement") +",");
                                    sb.append(""+  result.getString("promotion") +",");
                                    sb.append(""+  result.getString("tranche") +",");
                                    sb.append("Paye,");
                                    sb.append(""+  result.getString("timestamp") +"");
                                    sb.append('\n');
                             }                           
                        }
                        //state.close();
                        String sql_s = "SELECT etudiant.first_name, etudiant.second_name, etudiant.last_name, etudiant.faculte, \n" +
"                    etudiant.departement, etudiant.promotion, etudiant.timestamp, frais_academique.student_id, frais_academique.tranche FROM etudiant, frais_academique"
                                 + " WHERE etudiant.id_student = frais_academique.student_id;";
                        Statement state1 = connection.createStatement();
                        state1.executeQuery(sql_s);
                        
                        ResultSet result1 = state1.getResultSet(); 
                        //System.out.println("......."+ result1.getFetchSize());
                        while (result1.next()) {
                            //System.out.println(".......Data : ====>>>> "+ result1.getString("first_name") );
                             if (UniversalMethod.checkDatePaid(UniversalMethod.getCurrentDateAndTime(), result1.getString("timestamp"))) {
                                    sb.append(""+  result1.getString("first_name") +" "+ result1.getString("second_name") +" "+ result1.getString("last_name") +",");
                                    sb.append(""+  result1.getString("faculte") +",");
                                    sb.append(""+  result1.getString("departement") +",");
                                    sb.append(""+  result1.getString("promotion") +",");
                                    sb.append(""+  result1.getString("tranche") +",");
                                    sb.append("Pas Approuve,");
                                    sb.append(""+  result1.getString("timestamp") +"");
                                    sb.append('\n');
                             }                           
                        }
                        writer.write(sb.toString());
                        writer.flush();
                        writer.close();
                        
                        System.out.println("Well done!");

                      } catch (FileNotFoundException e) {
                            System.out.println(e.getMessage());
                      }
                    
                }
            }
                   
            return;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
       //JOptionPane.showMessageDialog(null, "Le rapport a bien ete telecharger!!!");
        return;
         
    }
     public void getMonthlyReportAcademique(){
        try {
              //;
           //UniversalMethod.report.clear();
           StringBuilder sb = new StringBuilder();
           File file = new File(FileSystemView.getFileSystemView().getDefaultDirectory().getPath().concat("\\UPAC Report"));
            if (file.mkdir()) {
                File fileName = new File(file.getAbsolutePath().concat("\\Service_Academique_Rapport_du_"+ UniversalMethod.getCurrentDateAndTime() +".csv"));
                try (PrintWriter writer = new PrintWriter(fileName)) {                       
                        sb.append("Guichet,");
                        sb.append("Assurance Maladie,");
                        sb.append(",,,,,");                       
                        sb.append('\n');                        
                        sb.append("Date,");
                        sb.append(""+ UniversalMethod.getCurrentDateAndTime() +",");
                        sb.append(",,,,,");                       
                        sb.append('\n');
                        sb.append(",,,,,,,");                        
                        sb.append('\n');
                        sb.append("Nom,");
                        sb.append("Faculte,");
                        sb.append("Departement,");
                        sb.append("Promotion,");
                        sb.append("Tranche,");
                        sb.append("Status de Payement,");
                        sb.append("Date,");
                        sb.append('\n');
                        
                     String sql_staff = "SELECT etudiant.first_name, etudiant.second_name, etudiant.last_name, etudiant.faculte, \n" +
"                    etudiant.departement, etudiant.promotion, etudiant.timestamp, frais_academique_temp.student_id, frais_academique_temp.tranche  FROM etudiant, frais_academique_temp"
                                 + " WHERE etudiant.id_student = frais_academique_temp.student_id;";
                    Statement state = this.connection.createStatement();
                    state.executeQuery(sql_staff);
                    //System.out.println("......."+ email +" "+ password);
                    ResultSet result = state.getResultSet();  
                        
                        while (result.next()) {
                             if (UniversalMethod.checkDatePaidMonthly(UniversalMethod.getCurrentDateAndTime(), result.getString("timestamp"))) {
                                    sb.append(""+  result.getString("first_name") +" "+ result.getString("second_name") +" "+ result.getString("last_name") +",");
                                    sb.append(""+  result.getString("faculte") +",");
                                    sb.append(""+  result.getString("departement") +",");
                                    sb.append(""+  result.getString("promotion") +",");
                                    sb.append(""+  result.getString("tranche") +",");
                                    sb.append("Paye,");
                                    sb.append(""+  result.getString("timestamp") +",");
                                    sb.append('\n');
                             }                           
                        }  
                        String sql_s = "SELECT etudiant.first_name, etudiant.second_name, etudiant.last_name, etudiant.faculte, \n" +
                                    "etudiant.departement, etudiant.promotion, etudiant.timestamp, frais_academiquep.student_id, frais_academique.tranche  FROM etudiant, frais_academique"
                                 + " WHERE etudiant.id_student = frais_academique.student_id;";
                        Statement state1 = this.connection.createStatement();
                        state1.executeQuery(sql_s);
                        //System.out.println("......."+ email +" "+ password);
                        ResultSet result1 = state.getResultSet(); 
                        while (result1.next()) {
                             if (UniversalMethod.checkDatePaidMonthly(UniversalMethod.getCurrentDateAndTime(), result1.getString("timestamp"))) {
                                    sb.append(""+  result1.getString("first_name") +" "+ result1.getString("second_name") +" "+ result1.getString("last_name") +",");
                                    sb.append(""+  result1.getString("faculte") +",");
                                    sb.append(""+  result1.getString("departement") +",");
                                    sb.append(""+  result1.getString("promotion") +",");
                                    sb.append(""+  result1.getString("tranche") +",");
                                    sb.append("Pas Approuve,");
                                    sb.append(""+  result1.getString("timestamp") +",");
                                    sb.append('\n');
                             }                           
                        }  
                        writer.write(sb.toString());
                        writer.flush();
                        writer.close();
                        
                        System.out.println("Well done serious!");

                      } catch (FileNotFoundException e) {
                            System.out.println(e.getMessage());
                      }
            }
            else{
                if (file.isDirectory()) {
                    String name = (((UniversalMethod.getCurrentDateAndTime().replace("-", "_")).replace(":", "_")).replace(" ", "_"));
                     File fileName = new File(file.getAbsolutePath().concat("\\Service_Academique_Rapport_du_"+ name +".csv"));
                try (PrintWriter writer = new PrintWriter(fileName)) {                       
                        sb.append("Guichet,");
                        sb.append("Assurance Maladie,");
                        sb.append(",,,,,,");                     
                        sb.append('\n');
                        
                        sb.append("Date,");
                        sb.append(""+ UniversalMethod.getCurrentDateAndTime() +",");
                        sb.append(",,,,,");
                        sb.append('\n');
                        sb.append(",,,,,,,");
                        sb.append('\n');
                        sb.append("Nom,");
                        sb.append("Faculte,");
                        sb.append("Departement,");
                        sb.append("Promotion,");
                        sb.append("Tranche,");
                        sb.append("Status de Payement,");
                        sb.append("Date");
                        
                        sb.append('\n');
                         String sql_staff = "SELECT etudiant.first_name, etudiant.second_name, etudiant.last_name, etudiant.faculte, " +
"                    etudiant.departement, etudiant.promotion, etudiant.timestamp, frais_academique_temp.student_id, frais_academique_temp.tranche   FROM etudiant, frais_academique_temp"
                                 + " WHERE etudiant.id_student = frais_academique_temp.student_id;";
                    Statement state = this.connection.createStatement();
                    state.executeQuery(sql_staff);
                    //System.out.println("......."+ email +" "+ password);
                    // 
                    ResultSet result = state.getResultSet();  
                        while (result.next()) {
                             if (UniversalMethod.checkDatePaidMonthly(UniversalMethod.getCurrentDateAndTime(), result.getString("timestamp"))) {
                                    sb.append(""+  result.getString("first_name") +" "+ result.getString("second_name") +" "+ result.getString("last_name") +",");
                                    sb.append(""+  result.getString("faculte") +",");
                                    sb.append(""+  result.getString("departement") +",");
                                    sb.append(""+  result.getString("promotion") +",");
                                    sb.append(""+  result.getString("tranche") +",");
                                    sb.append("Paye,");
                                    sb.append(""+  result.getString("timestamp") +"");
                                    sb.append('\n');
                             }                           
                        }
                        //state.close();
                        String sql_s = "SELECT etudiant.first_name, etudiant.second_name, etudiant.last_name, etudiant.faculte,  " +
                                    " etudiant.departement, etudiant.promotion, etudiant.timestamp, frais_academique.student_id, frais_academique.tranche  FROM etudiant, frais_academique"
                                 + " WHERE etudiant.id_student = frais_academique.student_id;";
                        Statement state1 = connection.createStatement();
                        state1.executeQuery(sql_s);
                        
                        ResultSet result1 = state1.getResultSet(); 
                        //System.out.println("......."+ result1.getFetchSize());
                        while (result1.next()) {
                            //System.out.println(".......Data : ====>>>> "+ result1.getString("first_name") );
                             if (UniversalMethod.checkDatePaidMonthly(UniversalMethod.getCurrentDateAndTime(), result1.getString("timestamp"))) {
                                    sb.append(""+  result1.getString("first_name") +" "+ result1.getString("second_name") +" "+ result1.getString("last_name") +",");
                                    sb.append(""+  result1.getString("faculte") +",");
                                    sb.append(""+  result1.getString("departement") +",");
                                    sb.append(""+  result1.getString("promotion") +",");
                                    sb.append(""+  result1.getString("tranche") +",");
                                    sb.append("Pas Approuve,");
                                    sb.append(""+  result1.getString("timestamp") +"");
                                    sb.append('\n');
                             }                           
                        }
                        writer.write(sb.toString());
                        writer.flush();
                        writer.close();
                        
                        System.out.println("Well done!");

                      } catch (FileNotFoundException e) {
                            System.out.println(e.getMessage());
                      }
                    
                }
            }
                   
            return;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
        //JOptionPane.showMessageDialog(null, "Le rapport a bien ete telecharger!!!");
        return;
     }
     
public void getMonthlyReportDivers(){
        try {
              //;
           //UniversalMethod.report.clear();
           StringBuilder sb = new StringBuilder();
           File file = new File(FileSystemView.getFileSystemView().getDefaultDirectory().getPath().concat("\\UPAC Report"));
            if (file.mkdir()) {
                File fileName = new File(file.getAbsolutePath().concat("\\Service_Divers_Rapport_du_"+ UniversalMethod.getCurrentDateAndTime() +".csv"));
                try (PrintWriter writer = new PrintWriter(fileName)) {                       
                        sb.append("Guichet,");
                        sb.append("Assurance Maladie,");
                        sb.append(",,,,");                       
                        sb.append('\n');                        
                        sb.append("Date,");
                        sb.append(""+ UniversalMethod.getCurrentDateAndTime() +",");
                        sb.append(",,,,");                       
                        sb.append('\n');
                        sb.append(",,,,,,");                        
                        sb.append('\n');
                        sb.append("Nom,");
                        sb.append("Faculte,");
                        sb.append("Departement,");
                        sb.append("Promotion,");
                        sb.append("Status de Payement,");
                        sb.append("Date,");
                        sb.append('\n');
                        
                     String sql_staff = "SELECT etudiant.first_name, etudiant.second_name, etudiant.last_name, etudiant.promotion, etudiant.faculte, etudiant.departement, "+
                    "frais_divers.timestamp FROM etudiant, frais_divers WHERE etudiant.id_student = frais_divers.student_id;";
                    Statement state = this.connection.createStatement();
                    state.executeQuery(sql_staff);
                    //System.out.println("......."+ email +" "+ password);
                    ResultSet result = state.getResultSet();  
                        
                        while (result.next()) {
                             if (UniversalMethod.checkDatePaidMonthly(UniversalMethod.getCurrentDateAndTime(), result.getString("timestamp"))) {
                                    sb.append(""+  result.getString("first_name") +" "+ result.getString("second_name") +" "+ result.getString("last_name") +",");
                                    sb.append(""+  result.getString("faculte") +",");
                                    sb.append(""+  result.getString("departement") +",");
                                    sb.append(""+  result.getString("promotion") +",");
                                    sb.append("Pas Approuve,");
                                    sb.append(""+  result.getString("timestamp") +",");
                                    sb.append('\n');
                             }                           
                        }  
                        String sql_s = "SELECT etudiant.first_name, etudiant.second_name, etudiant.last_name, etudiant.promotion, etudiant.faculte, etudiant.departement, "+
                    "frais_diver_temp.timestamp FROM etudiant, frais_diver_temp WHERE etudiant.id_student = frais_diver_temp.student_id;";
                        Statement state1 = this.connection.createStatement();
                        state1.executeQuery(sql_s);
                        //System.out.println("......."+ email +" "+ password);
                        ResultSet result1 = state.getResultSet(); 
                        while (result1.next()) {
                             if (UniversalMethod.checkDatePaidMonthly(UniversalMethod.getCurrentDateAndTime(), result1.getString("timestamp"))) {
                                    sb.append(""+  result1.getString("first_name") +" "+ result1.getString("second_name") +" "+ result1.getString("last_name") +",");
                                    sb.append(""+  result1.getString("faculte") +",");
                                    sb.append(""+  result1.getString("departement") +",");
                                    sb.append(""+  result1.getString("promotion") +",");
                                    sb.append("Paye,");
                                    sb.append(""+  result1.getString("timestamp") +",");
                                    sb.append('\n');
                             }                           
                        }  
                        writer.write(sb.toString());
                        writer.flush();
                        writer.close();
                        
                        System.out.println("Well done serious!");

                      } catch (FileNotFoundException e) {
                            System.out.println(e.getMessage());
                      }
            }
            else{
                if (file.isDirectory()) {
                    String name = (((UniversalMethod.getCurrentDateAndTime().replace("-", "_")).replace(":", "_")).replace(" ", "_"));
                     File fileName = new File(file.getAbsolutePath().concat("\\Service_Divers_Rapport_du_"+ name +".csv"));
                try (PrintWriter writer = new PrintWriter(fileName)) {                       
                        sb.append("Guichet,");
                        sb.append("Assurance Maladie,");
                        sb.append(",,,,");                     
                        sb.append('\n');
                        
                        sb.append("Date,");
                        sb.append(""+ UniversalMethod.getCurrentDateAndTime() +",");
                        sb.append(",,,,");
                        sb.append('\n');
                        sb.append(",,,,,,");
                        sb.append('\n');
                        sb.append("Nom,");
                        sb.append("Faculte,");
                        sb.append("Departement,");
                        sb.append("Promotion,");
                        sb.append("Status de Payement,");
                        sb.append("Date");
                        
                        sb.append('\n');
                         String sql_staff = "SELECT etudiant.first_name, etudiant.second_name, etudiant.last_name, etudiant.promotion, etudiant.faculte, etudiant.departement, "+
                    "frais_divers.timestamp FROM etudiant, frais_divers WHERE etudiant.id_student = frais_divers.student_id;";
                    Statement state = this.connection.createStatement();
                    state.executeQuery(sql_staff);
                    //System.out.println("......."+ email +" "+ password);
                    // 
                    ResultSet result = state.getResultSet();  
                        while (result.next()) {
                             if (UniversalMethod.checkDatePaidMonthly(UniversalMethod.getCurrentDateAndTime(), result.getString("timestamp"))) {
                                    sb.append(""+  result.getString("first_name") +" "+ result.getString("second_name") +" "+ result.getString("last_name") +",");
                                    sb.append(""+  result.getString("faculte") +",");
                                    sb.append(""+  result.getString("departement") +",");
                                    sb.append(""+  result.getString("promotion") +",");
                                    sb.append("Pas Approuve,");
                                    sb.append(""+  result.getString("timestamp") +"");
                                    sb.append('\n');
                             }                           
                        }
                        //state.close();
                        String sql_s = "SELECT etudiant.first_name, etudiant.second_name, etudiant.last_name, etudiant.promotion, etudiant.faculte, etudiant.departement, "+
                    "frais_diver_temp.timestamp FROM etudiant, frais_diver_temp WHERE etudiant.id_student = frais_diver_temp.student_id;";
                        Statement state1 = connection.createStatement();
                        state1.executeQuery(sql_s);
                        
                        ResultSet result1 = state1.getResultSet(); 
                        //System.out.println("......."+ result1.getFetchSize());
                        while (result1.next()) {
                            //System.out.println(".......Data : ====>>>> "+ result1.getString("first_name") );
                             if (UniversalMethod.checkDatePaidMonthly(UniversalMethod.getCurrentDateAndTime(), result1.getString("timestamp"))) {
                                    sb.append(""+  result1.getString("first_name") +" "+ result1.getString("second_name") +" "+ result1.getString("last_name") +",");
                                    sb.append(""+  result1.getString("faculte") +",");
                                    sb.append(""+  result1.getString("departement") +",");
                                    sb.append(""+  result1.getString("promotion") +",");
                                    sb.append("Paye,");
                                    sb.append(""+  result1.getString("timestamp") +"");
                                    sb.append('\n');
                             }                           
                        }
                        writer.write(sb.toString());
                        writer.flush();
                        writer.close();
                        
                        System.out.println("Well done!");

                      } catch (FileNotFoundException e) {
                            System.out.println(e.getMessage());
                      }
                    
                }
            }
                   
            return;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
        //JOptionPane.showMessageDialog(null, "Le rapport a bien ete telecharger!!!");
        return;
     }
public void getWeeklyReportDivers(){
        try {
              //;
           //UniversalMethod.report.clear();
           StringBuilder sb = new StringBuilder();
           File file = new File(FileSystemView.getFileSystemView().getDefaultDirectory().getPath().concat("\\UPAC Report"));
            if (file.mkdir()) {
                File fileName = new File(file.getAbsolutePath().concat("\\Service_Divers_Rapport_du_"+ UniversalMethod.getCurrentDateAndTime() +".csv"));
                try (PrintWriter writer = new PrintWriter(fileName)) {                       
                        sb.append("Guichet,");
                        sb.append("Service Divers,");
                        sb.append(",,,,");                       
                        sb.append('\n');                        
                        sb.append("Date,");
                        sb.append(""+ UniversalMethod.getCurrentDateAndTime() +",");
                        sb.append(",,,,");                       
                        sb.append('\n');
                        sb.append(",,,,,,");                        
                        sb.append('\n');
                        sb.append("Nom,");
                        sb.append("Faculte,");
                        sb.append("Departement,");
                        sb.append("Promotion,");
                        sb.append("Status de Payement,");
                        sb.append("Date,");
                        sb.append('\n');
                        
                     String sql_staff = "SELECT etudiant.first_name, etudiant.second_name, etudiant.last_name, etudiant.promotion, etudiant.faculte, etudiant.departement, "+
                    "frais_divers.timestamp FROM etudiant, frais_divers WHERE etudiant.id_student = frais_divers.student_id;";
                    Statement state = this.connection.createStatement();
                    state.executeQuery(sql_staff);
                    //System.out.println("......."+ email +" "+ password);
                    ResultSet result = state.getResultSet();  
                        
                        while (result.next()) {
                             if (UniversalMethod.checkDatePaid(UniversalMethod.getCurrentDateAndTime(), result.getString("timestamp"))) {
                                    sb.append(""+  result.getString("first_name") +" "+ result.getString("second_name") +" "+ result.getString("last_name") +",");
                                    sb.append(""+  result.getString("faculte") +",");
                                    sb.append(""+  result.getString("departement") +",");
                                    sb.append(""+  result.getString("promotion") +",");
                                    sb.append("Pas Approuve,");
                                    sb.append(""+  result.getString("timestamp") +",");
                                    sb.append('\n');
                             }                           
                        }  
                        String sql_s = "SELECT etudiant.first_name, etudiant.second_name, etudiant.last_name, etudiant.promotion, etudiant.faculte, etudiant.departement, "+
                    "frais_diver_temp.timestamp FROM etudiant, frais_diver_temp WHERE etudiant.id_student = frais_diver_temp.student_id;";
                        Statement state1 = this.connection.createStatement();
                        state1.executeQuery(sql_s);
                        //System.out.println("......."+ email +" "+ password);
                        ResultSet result1 = state.getResultSet(); 
                        while (result1.next()) {
                             if (UniversalMethod.checkDatePaid(UniversalMethod.getCurrentDateAndTime(), result1.getString("timestamp"))) {
                                    sb.append(""+  result1.getString("first_name") +" "+ result1.getString("second_name") +" "+ result1.getString("last_name") +",");
                                    sb.append(""+  result1.getString("faculte") +",");
                                    sb.append(""+  result1.getString("departement") +",");
                                    sb.append(""+  result1.getString("promotion") +",");
                                    sb.append("Paye,");
                                    sb.append(""+  result1.getString("timestamp") +",");
                                    sb.append('\n');
                             }                           
                        }  
                        writer.write(sb.toString());
                        writer.flush();
                        writer.close();
                        
                        System.out.println("Well done serious!");

                      } catch (FileNotFoundException e) {
                            System.out.println(e.getMessage());
                      }
            }
            else{
                if (file.isDirectory()) {
                    String name = (((UniversalMethod.getCurrentDateAndTime().replace("-", "_")).replace(":", "_")).replace(" ", "_"));
                     File fileName = new File(file.getAbsolutePath().concat("\\Service_Divers_Rapport_du_"+ name +".csv"));
                try (PrintWriter writer = new PrintWriter(fileName)) {                       
                        sb.append("Guichet,");
                        sb.append("Service Divers,");
                        sb.append(",,,,");                     
                        sb.append('\n');
                        
                        sb.append("Date,");
                        sb.append(""+ UniversalMethod.getCurrentDateAndTime() +",");
                        sb.append(",,,,");
                        sb.append('\n');
                        sb.append(",,,,,,");
                        sb.append('\n');
                        sb.append("Nom,");
                        sb.append("Faculte,");
                        sb.append("Departement,");
                        sb.append("Promotion,");
                        sb.append("Status de Payement,");
                        sb.append("Date");
                        
                        sb.append('\n');
                         String sql_staff = "SELECT etudiant.first_name, etudiant.second_name, etudiant.last_name, etudiant.promotion, etudiant.faculte, etudiant.departement, "+
                    "frais_divers.timestamp FROM etudiant, frais_divers WHERE etudiant.id_student = frais_divers.student_id;";
                    Statement state = this.connection.createStatement();
                    state.executeQuery(sql_staff);
                    //System.out.println("......."+ email +" "+ password);
                    // 
                    ResultSet result = state.getResultSet();  
                        while (result.next()) {
                             if (UniversalMethod.checkDatePaid(UniversalMethod.getCurrentDateAndTime(), result.getString("timestamp"))) {
                                    sb.append(""+  result.getString("first_name") +" "+ result.getString("second_name") +" "+ result.getString("last_name") +",");
                                    sb.append(""+  result.getString("faculte") +",");
                                    sb.append(""+  result.getString("departement") +",");
                                    sb.append(""+  result.getString("promotion") +",");
                                    sb.append("Pas Approuve,");
                                    sb.append(""+  result.getString("timestamp") +"");
                                    sb.append('\n');
                             }                           
                        }
                        //state.close();
                        String sql_s = "SELECT etudiant.first_name, etudiant.second_name, etudiant.last_name, etudiant.promotion, etudiant.faculte, etudiant.departement, "+
                    "frais_diver_temp.timestamp FROM etudiant, frais_diver_temp WHERE etudiant.id_student = frais_diver_temp.student_id;";
                        Statement state1 = connection.createStatement();
                        state1.executeQuery(sql_s);
                        
                        ResultSet result1 = state1.getResultSet(); 
                        //System.out.println("......."+ result1.getFetchSize());
                        while (result1.next()) {
                            //System.out.println(".......Data : ====>>>> "+ result1.getString("first_name") );
                             if (UniversalMethod.checkDatePaid(UniversalMethod.getCurrentDateAndTime(), result1.getString("timestamp"))) {
                                    sb.append(""+  result1.getString("first_name") +" "+ result1.getString("second_name") +" "+ result1.getString("last_name") +",");
                                    sb.append(""+  result1.getString("faculte") +",");
                                    sb.append(""+  result1.getString("departement") +",");
                                    sb.append(""+  result1.getString("promotion") +",");
                                    sb.append("Paye,");
                                    sb.append(""+  result1.getString("timestamp") +"");
                                    sb.append('\n');
                             }                           
                        }
                        writer.write(sb.toString());
                        writer.flush();
                        writer.close();
                        
                        System.out.println("Well done!");

                      } catch (FileNotFoundException e) {
                            System.out.println(e.getMessage());
                      }
                    
                }
            }
                   
            return;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
        //JOptionPane.showMessageDialog(null, "Le rapport a bien ete telecharger!!!");
        return;
     }
public void getWeeklyReportInscription(){
        try {
              //;
           //UniversalMethod.report.clear();
           StringBuilder sb = new StringBuilder();
           File file = new File(FileSystemView.getFileSystemView().getDefaultDirectory().getPath().concat("\\UPAC Report"));
            if (file.mkdir()) {
                File fileName = new File(file.getAbsolutePath().concat("\\Service_Inscription_Rapport_du_"+ UniversalMethod.getCurrentDateAndTime() +".csv"));
                try (PrintWriter writer = new PrintWriter(fileName)) {                       
                        sb.append("Guichet,");
                        sb.append("Service Inscription,");
                        sb.append(",,,,");                       
                        sb.append('\n');                        
                        sb.append("Date,");
                        sb.append(""+ UniversalMethod.getCurrentDateAndTime() +",");
                        sb.append(",,,,");                       
                        sb.append('\n');
                        sb.append(",,,,,,");                        
                        sb.append('\n');
                        sb.append("Nom,");
                        sb.append("Faculte,");
                        sb.append("Departement,");
                        sb.append("Promotion,");
                        sb.append("Status de Payement,");
                        sb.append("Date,");
                        sb.append('\n');
                        
                     String sql_staff = "SELECT etudiant.first_name, etudiant.second_name, etudiant.last_name, etudiant.promotion, etudiant.faculte, "
                             + "etudiant.departement, etudiant.timestamp FROM etudiant WHERE etudiant.etat = 1;";
                    Statement state = this.connection.createStatement();
                    state.executeQuery(sql_staff);
                    //System.out.println("......."+ email +" "+ password);
                    ResultSet result = state.getResultSet();  
                        
                        while (result.next()) {
                             if (UniversalMethod.checkDatePaid(UniversalMethod.getCurrentDateAndTime(), result.getString("timestamp"))) {
                                    sb.append(""+  result.getString("first_name") +" "+ result.getString("second_name") +" "+ result.getString("last_name") +",");
                                    sb.append(""+  result.getString("faculte") +",");
                                    sb.append(""+  result.getString("departement") +",");
                                    sb.append(""+  result.getString("promotion") +",");
                                    sb.append("Paye,");
                                    sb.append(""+  result.getString("timestamp") +",");
                                    sb.append('\n');
                             }                           
                        }  
                        String sql_s = "SELECT etudiant.first_name, etudiant.second_name, etudiant.last_name, etudiant.promotion, etudiant.faculte, "
                             + "etudiant.departement, etudiant.timestamp FROM etudiant WHERE etudiant.etat = 0;";
                        Statement state1 = this.connection.createStatement();
                        state1.executeQuery(sql_s);
                        //System.out.println("......."+ email +" "+ password);
                        ResultSet result1 = state.getResultSet(); 
                        while (result1.next()) {
                             if (UniversalMethod.checkDatePaid(UniversalMethod.getCurrentDateAndTime(), result1.getString("timestamp"))) {
                                    sb.append(""+  result1.getString("first_name") +" "+ result1.getString("second_name") +" "+ result1.getString("last_name") +",");
                                    sb.append(""+  result1.getString("faculte") +",");
                                    sb.append(""+  result1.getString("departement") +",");
                                    sb.append(""+  result1.getString("promotion") +",");
                                    sb.append("Pas Approuve,");
                                    sb.append(""+  result1.getString("timestamp") +",");
                                    sb.append('\n');
                             }                           
                        }  
                        writer.write(sb.toString());
                        writer.flush();
                        writer.close();
                        
                        System.out.println("Well done serious!");

                      } catch (FileNotFoundException e) {
                            System.out.println(e.getMessage());
                      }
            }
            else{
                if (file.isDirectory()) {
                    String name = (((UniversalMethod.getCurrentDateAndTime().replace("-", "_")).replace(":", "_")).replace(" ", "_"));
                     File fileName = new File(file.getAbsolutePath().concat("\\Service_Inscription_Rapport_du_"+ name +".csv"));
                try (PrintWriter writer = new PrintWriter(fileName)) {                       
                        sb.append("Guichet,");
                        sb.append("Service Divers,");
                        sb.append(",,,,,");                     
                        sb.append('\n');
                        
                        sb.append("Date,");
                        sb.append(""+ UniversalMethod.getCurrentDateAndTime() +",");
                        sb.append(",,,,");
                        sb.append('\n');
                        sb.append(",,,,,,");
                        sb.append('\n');
                        sb.append("Nom,");
                        sb.append("Faculte,");
                        sb.append("Departement,");
                        sb.append("Promotion,");
                        sb.append("Status de Payement,");
                        sb.append("Date");
                        
                        sb.append('\n');
                         String sql_staff = "SELECT etudiant.first_name, etudiant.second_name, etudiant.last_name, etudiant.promotion, etudiant.faculte, "
                             + "etudiant.departement, etudiant.timestamp FROM etudiant WHERE etudiant.etat = 1;";
                    Statement state = this.connection.createStatement();
                    state.executeQuery(sql_staff);
                    //System.out.println("......."+ email +" "+ password);
                    // 
                    ResultSet result = state.getResultSet();  
                        while (result.next()) {
                             if (UniversalMethod.checkDatePaid(UniversalMethod.getCurrentDateAndTime(), result.getString("timestamp"))) {
                                    sb.append(""+  result.getString("first_name") +" "+ result.getString("second_name") +" "+ result.getString("last_name") +",");
                                    sb.append(""+  result.getString("faculte") +",");
                                    sb.append(""+  result.getString("departement") +",");
                                    sb.append(""+  result.getString("promotion") +",");
                                    sb.append("Paye,");
                                    sb.append(""+  result.getString("timestamp") +"");
                                    sb.append('\n');
                             }                           
                        }
                        //state.close();
                        String sql_s = "SELECT etudiant.first_name, etudiant.second_name, etudiant.last_name, etudiant.promotion, etudiant.faculte, "
                             + "etudiant.departement, etudiant.timestamp FROM etudiant WHERE etudiant.etat = 0;";
                        Statement state1 = connection.createStatement();
                        state1.executeQuery(sql_s);
                        
                        ResultSet result1 = state1.getResultSet(); 
                        //System.out.println("......."+ result1.getFetchSize());
                        while (result1.next()) {
                            //System.out.println(".......Data : ====>>>> "+ result1.getString("first_name") );
                             if (UniversalMethod.checkDatePaid(UniversalMethod.getCurrentDateAndTime(), result1.getString("timestamp"))) {
                                    sb.append(""+  result1.getString("first_name") +" "+ result1.getString("second_name") +" "+ result1.getString("last_name") +",");
                                    sb.append(""+  result1.getString("faculte") +",");
                                    sb.append(""+  result1.getString("departement") +",");
                                    sb.append(""+  result1.getString("promotion") +",");
                                    sb.append("Pas Approuve,");
                                    sb.append(""+  result1.getString("timestamp") +"");
                                    sb.append('\n');
                             }                           
                        }
                        writer.write(sb.toString());
                        writer.flush();
                        writer.close();
                        
                        System.out.println("Well done!");

                      } catch (FileNotFoundException e) {
                            System.out.println(e.getMessage());
                      }
                    
                }
            }
                   
            return;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
        JOptionPane.showMessageDialog(null, "Le rapport a bien ete telecharger!!!");
        return;
     }
public void getMonthlyReportInscription(){
        try {
              //;
           //UniversalMethod.report.clear();
           StringBuilder sb = new StringBuilder();
           File file = new File(FileSystemView.getFileSystemView().getDefaultDirectory().getPath().concat("\\UPAC Report"));
            if (file.mkdir()) {
                File fileName = new File(file.getAbsolutePath().concat("\\Service_Inscription_Rapport_du_"+ UniversalMethod.getCurrentDateAndTime() +".csv"));
                try (PrintWriter writer = new PrintWriter(fileName)) {                       
                        sb.append("Guichet,");
                        sb.append("Service Inscription,");
                        sb.append(",,,,");                       
                        sb.append('\n');                        
                        sb.append("Date,");
                        sb.append(""+ UniversalMethod.getCurrentDateAndTime() +",");
                        sb.append(",,,,");                       
                        sb.append('\n');
                        sb.append(",,,,,,");                        
                        sb.append('\n');
                        sb.append("Nom,");
                        sb.append("Faculte,");
                        sb.append("Departement,");
                        sb.append("Promotion,");
                        sb.append("Status de Payement,");
                        sb.append("Date,");
                        sb.append('\n');
                        
                     String sql_staff = "SELECT etudiant.first_name, etudiant.second_name, etudiant.last_name, etudiant.promotion, etudiant.faculte, "
                             + "etudiant.departement, etudiant.timestamp FROM etudiant WHERE etudiant.etat = 1;";
                    Statement state = this.connection.createStatement();
                    state.executeQuery(sql_staff);
                    //System.out.println("......."+ email +" "+ password);
                    ResultSet result = state.getResultSet();  
                        
                        while (result.next()) {
                             if (UniversalMethod.checkDatePaidMonthly(UniversalMethod.getCurrentDateAndTime(), result.getString("timestamp"))) {
                                    sb.append(""+  result.getString("first_name") +" "+ result.getString("second_name") +" "+ result.getString("last_name") +",");
                                    sb.append(""+  result.getString("faculte") +",");
                                    sb.append(""+  result.getString("departement") +",");
                                    sb.append(""+  result.getString("promotion") +",");
                                    sb.append("Paye,");
                                    sb.append(""+  result.getString("timestamp") +",");
                                    sb.append('\n');
                             }                           
                        }  
                        String sql_s = "SELECT etudiant.first_name, etudiant.second_name, etudiant.last_name, etudiant.promotion, etudiant.faculte, "
                             + "etudiant.departement, etudiant.timestamp FROM etudiant WHERE etudiant.etat = 0;";
                        Statement state1 = this.connection.createStatement();
                        state1.executeQuery(sql_s);
                        //System.out.println("......."+ email +" "+ password);
                        ResultSet result1 = state.getResultSet(); 
                        while (result1.next()) {
                             if (UniversalMethod.checkDatePaidMonthly(UniversalMethod.getCurrentDateAndTime(), result1.getString("timestamp"))) {
                                    sb.append(""+  result1.getString("first_name") +" "+ result1.getString("second_name") +" "+ result1.getString("last_name") +",");
                                    sb.append(""+  result1.getString("faculte") +",");
                                    sb.append(""+  result1.getString("departement") +",");
                                    sb.append(""+  result1.getString("promotion") +",");
                                    sb.append("Pas Approuve,");
                                    sb.append(""+  result1.getString("timestamp") +",");
                                    sb.append('\n');
                             }                           
                        }  
                        writer.write(sb.toString());
                        writer.flush();
                        writer.close();
                        
                        System.out.println("Well done serious!");

                      } catch (FileNotFoundException e) {
                            System.out.println(e.getMessage());
                      }
            }
            else{
                if (file.isDirectory()) {
                    String name = (((UniversalMethod.getCurrentDateAndTime().replace("-", "_")).replace(":", "_")).replace(" ", "_"));
                     File fileName = new File(file.getAbsolutePath().concat("\\Service_Inscription_Rapport_du_"+ name +".csv"));
                try (PrintWriter writer = new PrintWriter(fileName)) {                       
                        sb.append("Guichet,");
                        sb.append("Service Divers,");
                        sb.append(",,,,,");                     
                        sb.append('\n');
                        
                        sb.append("Date,");
                        sb.append(""+ UniversalMethod.getCurrentDateAndTime() +",");
                        sb.append(",,,,");
                        sb.append('\n');
                        sb.append(",,,,,,");
                        sb.append('\n');
                        sb.append("Nom,");
                        sb.append("Faculte,");
                        sb.append("Departement,");
                        sb.append("Promotion,");
                        sb.append("Status de Payement,");
                        sb.append("Date");
                        
                        sb.append('\n');
                         String sql_staff = "SELECT etudiant.first_name, etudiant.second_name, etudiant.last_name, etudiant.promotion, etudiant.faculte, "
                             + "etudiant.departement, etudiant.timestamp FROM etudiant WHERE etudiant.etat = 1;";
                    Statement state = this.connection.createStatement();
                    state.executeQuery(sql_staff);
                    //System.out.println("......."+ email +" "+ password);
                    // 
                    ResultSet result = state.getResultSet();  
                        while (result.next()) {
                             if (UniversalMethod.checkDatePaidMonthly(UniversalMethod.getCurrentDateAndTime(), result.getString("timestamp"))) {
                                    sb.append(""+  result.getString("first_name") +" "+ result.getString("second_name") +" "+ result.getString("last_name") +",");
                                    sb.append(""+  result.getString("faculte") +",");
                                    sb.append(""+  result.getString("departement") +",");
                                    sb.append(""+  result.getString("promotion") +",");
                                    sb.append("Paye,");
                                    sb.append(""+  result.getString("timestamp") +"");
                                    sb.append('\n');
                             }                           
                        }
                        //state.close();
                        String sql_s = "SELECT etudiant.first_name, etudiant.second_name, etudiant.last_name, etudiant.promotion, etudiant.faculte, "
                             + "etudiant.departement, etudiant.timestamp FROM etudiant WHERE etudiant.etat = 0;";
                        Statement state1 = connection.createStatement();
                        state1.executeQuery(sql_s);
                        
                        ResultSet result1 = state1.getResultSet(); 
                        //System.out.println("......."+ result1.getFetchSize());
                        while (result1.next()) {
                            //System.out.println(".......Data : ====>>>> "+ result1.getString("first_name") );
                             if (UniversalMethod.checkDatePaidMonthly(UniversalMethod.getCurrentDateAndTime(), result1.getString("timestamp"))) {
                                    sb.append(""+  result1.getString("first_name") +" "+ result1.getString("second_name") +" "+ result1.getString("last_name") +",");
                                    sb.append(""+  result1.getString("faculte") +",");
                                    sb.append(""+  result1.getString("departement") +",");
                                    sb.append(""+  result1.getString("promotion") +",");
                                    sb.append("Pas Approuve,");
                                    sb.append(""+  result1.getString("timestamp") +"");
                                    sb.append('\n');
                             }                           
                        }
                        writer.write(sb.toString());
                        writer.flush();
                        writer.close();
                        
                        System.out.println("Well done!");

                      } catch (FileNotFoundException e) {
                            System.out.println(e.getMessage());
                      }
                    
                }
            }
                   
            return;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
        JOptionPane.showMessageDialog(null, "Le rapport a bien ete telecharger!!!");
        return;
     }

        
    
}
