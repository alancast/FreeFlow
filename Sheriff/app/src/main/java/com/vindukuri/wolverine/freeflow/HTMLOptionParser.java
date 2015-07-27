import org.jsoup.select.Elements;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;

public class HTMLOptionParser {

 String urlPt1;
 String urlPt2;
 
 public HTMLOptionParser(String url1, String url2){
    this.urlPt1 = url1;
    this.urlPt2 = url2;
 }
       
 public ArrayList<Option> read(String symbol, String str){
    String result = "";
    Date expiration = dateToEpoch(str);
    Document doc = null;
    try {
      System.out.println(urlPt1 + symbol + urlPt2 + expiration.getTime()/1000);
        doc = Jsoup.connect(urlPt1 + symbol + urlPt2 + expiration.getTime()/1000).get();
    } catch (IOException ex) {
           System.out.println("yikes");
    }
    
    ArrayList<Option> options = new ArrayList<Option>(); 
    Element table = doc.select("tbody").get(1);
    Elements rows = table.select("tr");
           
    for (int i = 2; i < rows.size(); ++i) {
           Element row = rows.get(i);
           Elements cols = row.select("td");
           Option o = new Option();
           o.strike = Double.parseDouble(cols.get(0).text());
           o.contractName = cols.get(1).text();
           o.last = Double.parseDouble(cols.get(2).text());
           o.bid = Double.parseDouble(cols.get(3).text());
           o.ask = Double.parseDouble(cols.get(4).text());
           o.change = Double.parseDouble(cols.get(5).text());
           o.volume = Integer.parseInt(cols.get(7).text());
           o.openInterest = Integer.parseInt(cols.get(8).text());
           options.add(o);
    }
   return options;
  }
       
 public Date dateToEpoch(String str){
        SimpleDateFormat df = new SimpleDateFormat("MM dd yyyy HH:mm:ss.SSS zzz");
     Date date = null;
     long epoch = 0;
        try {
               date = df.parse(str);
               epoch = date.getTime();
               System.out.println(epoch);
        } catch (ParseException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
        }
        return date;
 }
}
