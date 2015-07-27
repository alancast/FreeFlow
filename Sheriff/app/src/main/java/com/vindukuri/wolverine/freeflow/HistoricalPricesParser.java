public class HistoricalPricesParser {
  public double[] GetHistoricalPrices(String symbol) throws Exception{
      Date date = new Date();
      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      int month = cal.get(Calendar.MONTH);
      int day = cal.get(Calendar.DAY_OF_MONTH) - 14; // pull past two weeks
      int year = cal.get(Calendar.YEAR);
      // This code will break every for every time the preceeding month wasn't 31 days
      // So half of the months of half of the year this code will break
      if (day < 0) // past two weeks put us into the previous month
      {
             if (month == 0){ // month was january 
                    month = 11;
                    year--;
             }
             else{
                    month--;
             }
             day += 31;
      }
      URL oracle = new URL("http://ichart.yahoo.com/table.csv?s=" + symbol 
                + "&a=" + month + "&b=" + day + "&c=" + year);
       URLConnection yc = oracle.openConnection();
       BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
       String inputLine;
       double[] high = new double[10]; // market only open 10 days out of the past 2 weeks
       double[] low = new double[10];
       double[] open = new double[10];
       double[] close = new double[10];
       int i=0;
       while ((inputLine = in.readLine()) != null)
       {
             System.out.println(inputLine);
             if (i>0){ // first line was headers
                    open[i-1] = GetColDouble(inputLine, 1);
             high[i-1] = GetColDouble(inputLine,2);
             low[i-1] = GetColDouble(inputLine,3);
             close[i-1] = GetColDouble(inputLine,4);
             }
             ++i;
       }
       in.close();
       return high;
    }
 public double GetColDouble(String input, int col){
    String[] line = input.split(",");
    return Double.parseDouble(line[col]);
  }
}
