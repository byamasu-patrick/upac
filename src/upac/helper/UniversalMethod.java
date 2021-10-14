/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upac.helper;

import com.google.zxing.BarcodeFormat;
import java.awt.Color;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.xml.bind.DatatypeConverter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import upac.Administrator;
import upac.MembreData;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.*;
import com.google.zxing.common.*;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
//import com.sun.istack.internal.FragmentContentHandler;
import java.util.Base64;
import java.util.HashMap;
import upac.Apparitora;
import upac.universalclasses.FraisAcademiqueStudent;
import upac.universalclasses.FraisDiversStudent;
import upac.universalclasses.FraisMaladieStudent;

/**
 *
 * @author user
 */
public class UniversalMethod {
    public static ArrayList<MembreData> data;
    public static ArrayList<Administrator> adminLogin = new ArrayList<>();
    public static MembreData admindata;
    public static MembreData memberData;
    public static int currentId = 0;
    public static String typeAff;
    public static ArrayList<String[]> studentData;
    public static ArrayList<String[]> studentDataConf;
    
    public static ArrayList<String[]> studentDataAttenteBAC1 = new ArrayList<>();
    public static ArrayList<String[]> studentDataAttenteBAC2 = new ArrayList<>();
    public static ArrayList<String[]> studentDataAttenteBAC3 = new ArrayList<>();
    public static ArrayList<FraisMaladieStudent> fraisMaladieAttente = new ArrayList<>();
    public static ArrayList<FraisMaladieStudent> fraisMaladieConfirme = new ArrayList<>();
    public static ArrayList<FraisDiversStudent> fraisDiversAttente = new ArrayList<>();
    public static ArrayList<FraisDiversStudent> fraisDiversConfirme = new ArrayList<>();
    
    public static ArrayList<FraisAcademiqueStudent> fraisAcademique = new ArrayList<>();
    public static ArrayList<FraisDiversStudent> fraisDivers = new ArrayList<>();
    public static ArrayList<FraisMaladieStudent> fraisMaladie = new ArrayList<>();
    
    public static ArrayList<FraisAcademiqueStudent> fraisAcademiqueConfirme = new ArrayList<>();
     public static ArrayList<String[]> messages = new ArrayList<>();
    public static ArrayList<String[]> studentDataReg;
    
    public static ArrayList<FraisMaladieStudent> report = new ArrayList<>();
    
    public static boolean apparitora = false;
    public static int alertNumber = 0;
   
    public static DefaultTableModel tableModel;
    public static void populateTableData(JTable table){
        ArrayList<MembreData> dataForTable = data;
        tableModel = (DefaultTableModel) table.getModel();
        
        Object[] object = new Object[5];
        for (int i = 0; i < data.size(); i++) {
            object[0] = data.get(i).getNom();
            object[1] = data.get(i).getEmail();
            object[2] = data.get(i).getPoste();
            object[3] = data.get(i).getAffectation();
            object[4] = data.get(i).getBureau();
            tableModel.addRow(object);
        }
        table.setModel(tableModel);
    }
    public static void populateTableStudentData(JTable table, ArrayList<String[]> data){
       //"Nom", "Address", "Faculte", "Departement", "Promotion"
        tableModel = (DefaultTableModel) table.getModel();
               
        Object[] object = new Object[5];
        for (int i = 0; i < data.size(); i++) {             
            object[0] = data.get(i)[0];
            object[1] = data.get(i)[5];
            object[2] = data.get(i)[2];
            object[3] = data.get(i)[3];
            object[4] = data.get(i)[8];
            tableModel.addRow(object);
        }
        table.setModel(tableModel);
    }
    public static void populateTableStudentAcademique(JTable table, ArrayList<FraisAcademiqueStudent> dataStu){
       //"Nom", "Address", "Faculte", "Departement", "Promotion"
        tableModel = (DefaultTableModel) table.getModel();
               
        Object[] object = new Object[5];
        for (int i = 0; i < dataStu.size(); i++) {             
            object[0] = dataStu.get(i).getFullname();
            object[1] = dataStu.get(i).getFaculte();
            object[2] = dataStu.get(i).getPromotion();
            object[3] = dataStu.get(i).getTranche();
            tableModel.addRow(object);
        }
        table.setModel(tableModel);
    }
    public static void populateTableStudentDivers(JTable table, ArrayList<FraisDiversStudent> dataStu){
       //"Nom", "Address", "Faculte", "Departement", "Promotion"
        tableModel = (DefaultTableModel) table.getModel();
               
        Object[] object = new Object[5];
        for (int i = 0; i < dataStu.size(); i++) {             
            object[0] = dataStu.get(i).getFullname();
            object[1] = dataStu.get(i).getFaculte();
            object[2] = dataStu.get(i).getPromotion();
            tableModel.addRow(object);
        }
        table.setModel(tableModel);
    }
    public static void populateTableStudentMaladie(JTable table, ArrayList<FraisMaladieStudent> dataStu){
       //"Nom", "Address", "Faculte", "Departement", "Promotion"
        tableModel = (DefaultTableModel) table.getModel();
               
        Object[] object = new Object[5];
        for (int i = 0; i < dataStu.size(); i++) {             
            object[0] = dataStu.get(i).getFullname();
            object[1] = dataStu.get(i).getFaculte();
            object[2] = dataStu.get(i).getPromotion();
            tableModel.addRow(object);
        }
        table.setModel(tableModel);
    }
    public static String encodeFileToBase64Binary(File file){
        String encodedfile = null;
        try {
                FileInputStream fileInputStreamReader = new FileInputStream(file);
                byte[] bytes = new byte[(int)file.length()];
                fileInputStreamReader.read(bytes);
                encodedfile = new String(Base64.getEncoder().encode(bytes));
                System.out.println(encodedfile);
        } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
         }

        return encodedfile;
    }
    public static String getCurrentDateAndTime(){
        
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = new Date();
            
        return simpleFormat.format(now);
    }
    public static void removeAllRowFromTable(JTable table){
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        table.removeRowSelectionInterval(0, model.getRowCount() - 1);
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Nom", "Email", "Poste", "Affectation", "Bureau"
            }
        ));
    
    }
    public static void addChartToPanel(JPanel panel){
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        pieDataset.setValue("Janvier", new Integer(10));
        pieDataset.setValue("Fervrier", new Integer(15));
        pieDataset.setValue("Mars", new Integer(22));
        pieDataset.setValue("Avril", new Integer(30));
        
        JFreeChart chart = ChartFactory.createPieChart("Donne general des etudiant", pieDataset, true, true, true);
        PiePlot plot = (PiePlot) chart.getPlot();
        //plot.setForegroundAlpha(0);
        ChartPanel panleChart = new ChartPanel(chart, true);
        //ChartFrame frame = new ChartFrame("Pie Chart", chart);
        panleChart.setVisible(true);
        panleChart.setSize(panel.getWidth(), panel.getHeight());
        panel.add(panleChart);
        
    
    }
    public static void addChartToPanel(JPanel panel, ArrayList<Integer> date, ArrayList<Integer> number){
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        for (int i = 0; i < date.size(); i++) {
            pieDataset.setValue(date.get(i), number.get(i));
        }        
        JFreeChart chart = ChartFactory.createPieChart("Donne general des etudiant", pieDataset, true, true, true);
        PiePlot plot = (PiePlot) chart.getPlot();
        //plot.setForegroundAlpha(0);
        ChartPanel panleChart = new ChartPanel(chart, true);
        //ChartFrame frame = new ChartFrame("Pie Chart", chart);
        panleChart.setVisible(true);
        panleChart.setSize(panel.getWidth(), panel.getHeight());
        panel.add(panleChart);
        
    
    }
    public static void addChartToPanel2(JPanel panel , ArrayList<Integer> date, ArrayList<Integer> number){
        
        JFreeChart barChart = ChartFactory.createBarChart(
         "Progression de Payement de ce mois",           
         "Jour du mois",            
         "Nombre total des etudiants ayant paye",            
         createDataset(date, number),          
         PlotOrientation.VERTICAL,           
         true, true, false);
         
        ChartPanel panleChart = new ChartPanel(barChart, true);
        //ChartFrame frame = new ChartFrame("Pie Chart", chart);
        panleChart.setVisible(true);
        panleChart.setSize(panel.getWidth(), panel.getHeight());
        panel.add(panleChart);
        
    
    }
    
   private static DefaultCategoryDataset createDataset( ArrayList<Integer> date, ArrayList<Integer> number ) {
      
      final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );  
      for (int i = 0; i < date.size(); i++) {
           dataset.addValue( number.get(i) ,  number.get(i), date.get(i));
        }
     
      return dataset; 
   }
    public static void addChartToPanel2(JPanel panel){
        DefaultCategoryDataset pieDataset = new DefaultCategoryDataset();
        pieDataset.setValue( new Integer(10), "Janvier", "Fervrier");
        pieDataset.setValue(new Integer(15), "Fervrier", "Janvier");
        pieDataset.setValue(new Integer(22), "Janvier", "Mars");
        pieDataset.setValue(new Integer(30), "Avril", "Janvier");
        
        JFreeChart chart = ChartFactory.createAreaChart("Donne de par le mois", "Monthly", "Month", pieDataset, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setRangeGridlinePaint(Color.ORANGE);
        //plot.setForegroundAlpha(0);
        ChartPanel panleChart = new ChartPanel(chart, true);
        //ChartFrame frame = new ChartFrame("Pie Chart", chart);
        panleChart.setVisible(true);
        panleChart.setSize(panel.getWidth(), panel.getHeight());
        panel.add(panleChart);
        
    
    }
    public static BufferedImage getScreenshot(Component component){
        BufferedImage image = new BufferedImage(component.getWidth(), component.getHeight(), BufferedImage.TYPE_INT_RGB);
        
        component.paint(image.getGraphics());
        return image;
    }
    public static File saveScreenshot(Component component, String filename){
        try {
            BufferedImage image = getScreenshot(component);
            ImageIO.write(image, "png",new File(filename));
            
            return new File(filename);
        } catch (Exception e) {
            System.err.println( e );
        }
        return null;
    }
    public static ImageIcon convertStringToImageIcon(String imageData){
        ImageIcon icon = null;
        try {
            //byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
            //String imageBase64 = imageData.split(",")[1];
            byte[] imageByte = DatatypeConverter.parseBase64Binary(imageData);
            
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageByte));
            icon = new ImageIcon(image);
            
        } catch (Exception e) {
        }
        return icon;
    }
    public static File generateQRCode(String data, String fileName){
        try {
            String charset = "UTF-8";
            
            Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
            
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            
            BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(), charset),
                    com.google.zxing.BarcodeFormat.QR_CODE, 200, 200, hintMap);
            
            MatrixToImageWriter.writeToFile(matrix, "PNG", new File(fileName));
                        
            return new File(fileName);
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
                
    
    }
    public static boolean checkDatePaid(String currentDate, String regidteredDate){
        try {
            String cDate = currentDate.split(" ")[0];
            String rDate = regidteredDate.split(" ")[0];
            if (cDate.split("-")[1].equals(rDate.split("-")[1])) {
               if ((Integer.parseInt(cDate.split("-")[2]) - 7 ) >= (Integer.parseInt(rDate.split("-")[2]) - 7)) {
                return true;
               } 
            }
            
        } catch (Exception e) {
        }
        return false;
    }
     public static boolean checkDatePaidMonthly(String currentDate, String regidteredDate){
        try {
            String cDate = currentDate.split(" ")[0];
            String rDate = regidteredDate.split(" ")[0];
            if (cDate.split("-")[1].equals(rDate.split("-")[1])) {
               if ((Integer.parseInt(cDate.split("-")[2]) - 25 ) >= (Integer.parseInt(rDate.split("-")[2]) - 25)) {
                return true;
               } 
            }
            
        } catch (Exception e) {
        }
        return false;
    }
}
