package ChanteySongs.Data;

import java.io.*;
import java.util.*;
import java.text.*;

import com.thoughtworks.xstream.*;

public class DataUtil
{
    public static final DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    
    public static String getData(Scanner in, PrintWriter out, String name)
    {
        String tmp;
        out.print(name + ": ");
        out.flush();
        tmp = in.nextLine();
        return (tmp.length() == 0) ? null : tmp;
    }
    
    public static String getDataMultiLine(Scanner in, PrintWriter out, String name)
    {
        String tmp = "";
        String ret = "";
        out.print(name + ": ");
        out.flush();
        
        while(!tmp.equalsIgnoreCase("end"))
        {
            ret += tmp + "\n";
            tmp = in.nextLine();
        }
        return (ret.replaceAll("\\s","").length() == 0) ? null : ret;
    }
    
    public static int getDataInt(Scanner in, PrintWriter out, String name)
    {
        String tmp;
        out.print(name + ": ");
        out.flush();
        tmp = in.nextLine();
        
        try
        {
            return (tmp.length() == 0) ? -1 : Integer.parseInt(tmp);
        }catch(NumberFormatException nfe)
        {
            System.err.println("Not an integer value for " + name + ": " + tmp);
            return -1;
        }
    }
    
    public static Date getDataDate(Scanner in, PrintWriter out, String name)
    {
        String tmp;
        out.print(name + ": ");
        out.flush();
        tmp = in.nextLine();
        
        try
        {
            return (tmp.length() == 0) ? null : formatter.parse(tmp);
        }catch(ParseException pe)
        {
            System.err.println("Could not parse date for " + name + ": " + tmp);
            return null;
        }
    }
    
    public static Set<String> getDataSet(Scanner in, PrintWriter out, String name)
    {
        Set<String> ret = new HashSet<String>();
        String tmp;
        
        do
        {
            tmp = getData(in, out, name);
            if(tmp != null)
            {
                ret.add(tmp);
            }
        }while(tmp != null);
        
        return (ret.size() == 0)? null : ret;
    }
    
    public static void prepare(XStream xstream)
    {
        xstream.alias("Person", Person.class);
        xstream.alias("Index", Index.class);
        xstream.alias("Collection", SongCollection.class);
        xstream.alias("Song", Song.class);
    }
}