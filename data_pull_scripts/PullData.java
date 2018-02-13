import java.sql.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class PullData
{

  public static void main(String[] args)
  {


    try
    {
      // create a mysql database connection
      String myDriver = "com.mysql.jdbc.Driver";
      String myUrl = "jdbc:mysql://localhost:3306/nba_stats";
      Class.forName(myDriver);
      Connection conn = DriverManager.getConnection(myUrl, "stats", "nba");

      PreparedStatement truncate = conn.prepareStatement("truncate player");
      truncate.execute();

      // the mysql insert statement
      String query = " insert into player (name, team, gp, mpg, fgm, fga, fgp, 3pm, 3pa, 3pp, ftm, fta, ftp, tov, pf, orb, drb, rpg, apg, spg, bpg, ppg)"
        + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

      PreparedStatement insertData = conn.prepareStatement(query);

      // get information for players
      String line = null;
      String name, team, data;
      int i;
      boolean inTable = false;

      for (int j = 1; j < 6; j++) {
        // Make a URL to the web page
        URL url = new URL("https://basketball.realgm.com/nba/stats/2018/Averages/All/points/All/asc/" + j + "/Regular_Season");
        // Get the input stream through URL Connection
        URLConnection con = url.openConnection();
        InputStream is =con.getInputStream();

        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        // read each line and write to System.out
        while ((line = br.readLine()) != null) {
          if (line.contains("</tbody>")) {
            break;
          }

          if (line.contains("<tr>")) {
            if (inTable) {
              i = line.indexOf("<a");
              i = line.indexOf(">", i);
              name = line.substring(i+1, line.indexOf("<", i));
              // System.out.println(name);
              data = line.substring(line.indexOf("<td>", i)+4, line.lastIndexOf("</td>"));
              String[] dataArr = data.split("</td><td>");
              // for (i = 0; i < dataArr.length; i++) {
              //   System.out.println(dataArr[i]);
              // }

              insertData.setString(1, name);
              insertData.setString(2, dataArr[0]);
              insertData.setDouble(3, Double.parseDouble(dataArr[1]));
              insertData.setDouble(4, Double.parseDouble(dataArr[2]));
              insertData.setDouble(5, Double.parseDouble(dataArr[3]));
              insertData.setDouble(6, Double.parseDouble(dataArr[4]));
              insertData.setDouble(7, Double.parseDouble(dataArr[5]));
              insertData.setDouble(8, Double.parseDouble(dataArr[6]));
              insertData.setDouble(9, Double.parseDouble(dataArr[7]));
              insertData.setDouble(10, Double.parseDouble(dataArr[8]));
              insertData.setDouble(11, Double.parseDouble(dataArr[9]));
              insertData.setDouble(12, Double.parseDouble(dataArr[10]));
              insertData.setDouble(13, Double.parseDouble(dataArr[11]));
              insertData.setDouble(14, Double.parseDouble(dataArr[12]));
              insertData.setDouble(15, Double.parseDouble(dataArr[13]));
              insertData.setDouble(16, Double.parseDouble(dataArr[14]));
              insertData.setDouble(17, Double.parseDouble(dataArr[15]));
              insertData.setDouble(18, Double.parseDouble(dataArr[16]));
              insertData.setDouble(19, Double.parseDouble(dataArr[17]));
              insertData.setDouble(20, Double.parseDouble(dataArr[18]));
              insertData.setDouble(21, Double.parseDouble(dataArr[19]));
              insertData.setDouble(22, Double.parseDouble(dataArr[20]));

              insertData.execute();

            } else {
              inTable = true;
            }
          }
        }
        inTable = false;
      }

      conn.close();
    }
    catch (Exception e)
    {
      System.err.println("Got an exception!");
      System.err.println(e.getMessage());
    }
  }
}
