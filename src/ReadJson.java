import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLOutput;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JScrollPane;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.*;




// video to load jar
//https://www.youtube.com/watch?v=QAJ09o3Xl_0

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// Program for print data in JSON format.
public class ReadJson implements ActionListener{

    private JFrame mainFrame;
    private  JTextArea dayoutput;
    private JPanel controlPanel2;
    private JPanel controlPanel3;
    private JTextArea countryinput;
    private JTextArea yearinput;
    private JTextArea monthinput;
    private JTextArea dayinput;
    private int WIDTH = 800;
    private int HEIGHT = 700;

    public static void main(String[] args) throws ParseException{
        // In java JSONObject is used to create JSON object
        // which is a subclass of java.util.HashMap.
        // JFrame mainFrame;

        ReadJson api = new ReadJson();
        JSONObject file = new JSONObject();
        file.put("Full Name", "Ritu Sharma");
        file.put("Roll No.", 1704310046);
        file.put("Tuition Fees", 65400);


        // To print in JSON format.
        System.out.print(file.get("Tuition Fees"));
        System.out.println(file.get("Full Name"));


    }

    public ReadJson() {
        prepareGUI();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Do you have school?");
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setLayout(new GridLayout(1, 2));
        countryinput = new JTextArea("Input Country Name: ");
        yearinput = new JTextArea("Input Year Between 2010-2030: ");
        monthinput = new JTextArea("Input Month Number: ");
        dayinput = new JTextArea("Input Day Number: ");
        dayoutput = new JTextArea("Day info: ");
        controlPanel2 = new JPanel();
        controlPanel3 = new JPanel();
        controlPanel2.setLayout(new GridLayout(5, 1));
        controlPanel3.setLayout(new GridLayout(1, 2));

        JButton startbutton = new JButton("Start");
        startbutton.setActionCommand("Start");
        startbutton.addActionListener(new ReadJson.ButtonClickListener());

        JButton resetbutton = new JButton("Reset");
        resetbutton.setActionCommand("Reset");
        resetbutton.addActionListener(new ReadJson.ButtonClickListener());

        mainFrame.add(controlPanel2);
        mainFrame.add(dayoutput);
        controlPanel2.add(countryinput);
        controlPanel2.add(yearinput);
        controlPanel2.add(monthinput);
        controlPanel2.add(dayinput);
        controlPanel2.add(controlPanel3);
        controlPanel3.add(startbutton);
        controlPanel3.add(resetbutton);



        mainFrame.setVisible(true);
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }

    public void pull() throws ParseException {
        String output = "abc";
        String totlaJson = "";
        try {

            String usercountry = countryinput.getText();
            String usercountryName = usercountry.substring(20);
            //countryinput.append("\n");
            String useryear = yearinput.getText();
            String useryearNumber = useryear.substring(30);
            //yearinput.append("\n");
            String usermonth = monthinput.getText();
            String usermonthNumber = usermonth.substring(20);
            //monthinput.append("\n");
            String userday = dayinput.getText();
            String userdayNumber = userday.substring(18);
            //countryinput.append(userpokemonName);
            //usercountryName = "US";
            //useryearNumber = "2021";
            URL url = new URL("https://api.api-ninjas.com/v1/holidays?country="+usercountryName+"&year="+useryearNumber+"&type=");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("X-Api-Key", "67GGggozF6jiETmV0Xcshg==udWw2CrXhMGpD9P2");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {

                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));


            //System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                //System.out.println(output);
                totlaJson += output;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONParser parser = new JSONParser();
        //System.out.println(str);
        org.json.simple.JSONArray jsonArray = (org.json.simple.JSONArray) parser.parse(totlaJson);
        System.out.println(jsonArray);


        String country = null;
        String holidaynames = null;
        String holidaydate = null;
        String userdate = null;
        String holidaydayofweek = null;
        String holidaytype = null;
        try {
            String usercountry = countryinput.getText();
            String usercountryName = usercountry.substring(20);
            countryinput.append("\n");
            String useryear = yearinput.getText();
            String useryearNumber = useryear.substring(30);
            yearinput.append("\n");
            String usermonth = monthinput.getText();
            String usermonthNumber = usermonth.substring(20);
            monthinput.append("\n");
            String userday = dayinput.getText();
            String userdayNumber = userday.substring(18);
//            JSONObject jsonObject = (JSONObject) jsonArray.get(1);
//            country = (String) jsonObject.get("country");
            System.out.println("Country: " + usercountryName);
            dayoutput.append("\n");
            dayoutput.append("Country: " + usercountryName);
            System.out.println("Year: " + useryearNumber);
            dayoutput.append("\n");
            dayoutput.append("Date: "+useryearNumber+"-"+usermonthNumber+"-"+userdayNumber);

            userdate = useryearNumber+"-"+usermonthNumber+"-"+userdayNumber;
            //org.json.simple.JSONArray msg = (org.json.simple.JSONArray) jsonObject.get("");
            int n = jsonArray.size(); //(msg).length();
            int i = 0;
            while (!userdate.equals(holidaydate)){
                if(i==n){
                    break;
                }
          //  for (int i = 0; i < n; ++i) {
                JSONObject test = (JSONObject) jsonArray.get(i);
                //System.out.println(test);
                //JSONObject holidayname = (JSONObject) test.get("name");

                holidaynames = (String) test.get("name");
                holidaydate = (String) test.get("date");
                holidaydayofweek = (String) test.get("day");
                holidaytype = (String) test.get("type");
                // System.out.println(person.getInt("key"));
                i++;
            }

            System.out.println(userdate);
            System.out.println(holidaydate);

            if(userdate.equals(holidaydate)) {
                System.out.println("Holiday: " + holidaynames);
                dayoutput.append("\n");
                dayoutput.append("Holiday: " + holidaynames);
                System.out.println("Day of The Week: " + holidaydayofweek);
                dayoutput.append("\n");
                dayoutput.append("Day of The Week: " + holidaydayofweek);
                if(holidaydayofweek.equals("Saturday") || holidaydayofweek.equals("Sunday")){
                    System.out.println("There is no school on " + holidaynames + "!");
                    dayoutput.append("\n");
                    dayoutput.append("There is no school on " + holidaynames + "!");
                }
            }
            else{
                System.out.println("There is no holiday on that day!");
                dayoutput.append("\n");
                dayoutput.append("There is no holiday on that day!");
            }
//            org.json.simple.JSONArray msg1 = (org.json.simple.JSONArray) jsonObject.get("moves");
//            for (int s = 0; s < n; ++s) {
//                JSONObject test1 = (JSONObject) msg1.get(s);
//                //System.out.println(test);
//                JSONObject moves = (JSONObject) test1.get("move");
//                moves1 = (String) moves.get("name");
//                System.out.println("move: " + moves1);
//                dayoutput.append("\n");
//                dayoutput.append("move: " + moves1);
//                // System.out.println(person.getInt("key"));
//            }


            // org.json.simple.JSONArray msg = (org.json.simple.JSONArray) jsonObject.get("abilities");


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getDayNumberOld(Date date) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        cal.setTime(date);
        String day = "";
        if(cal.get(Calendar.DAY_OF_WEEK) == 1){
            System.out.println("Sunday");
            day = "Sunday";
        }
        else if(cal.get(Calendar.DAY_OF_WEEK) == 2){
            System.out.println("Monday");
            day = "Monday";
        }
        else if(cal.get(Calendar.DAY_OF_WEEK) == 3){
            System.out.println("Tuesday");
            day = "Tuesday";
        }
        else if(cal.get(Calendar.DAY_OF_WEEK) == 4){
            System.out.println("Wednesday");
            day = "Wednesday";
        }
        else if(cal.get(Calendar.DAY_OF_WEEK) == 5){
            System.out.println("Thursday");
            day = "Thursday";
        }
        else if(cal.get(Calendar.DAY_OF_WEEK) == 6){
            System.out.println("Friday");
            day = "Friday";
        }
        else if(cal.get(Calendar.DAY_OF_WEEK) == 7){
            System.out.println("Saturday");
            day = "Saturday";
        }
        return day;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
    }
    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("Start")) {
                try {
                    pull();
                    getDayNumberOld()
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }
            else if (command.equals("Reset")){
                dayoutput.setText("Day info: ");
                countryinput.setText("Input Country Name: ");
                monthinput.setText("Input Month Number: ");
                dayinput.setText("Input Day Number: ");
                yearinput.setText("Input Year Between 2010-2030: ");
            }


        }
    }
}


