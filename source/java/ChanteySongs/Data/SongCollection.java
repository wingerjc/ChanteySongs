package ChanteySongs.Data;

import java.io.*;
import java.util.*;
import java.text.*;

public class SongCollection implements Serializable, CLIInput
{
    private String ID;
    
    private String title;
    
    private String type;
    
    private Set<String> authors;
    
    private Set<String> editors;
    
    private String publisher;
    
    private String publisherLocation;
    
    private Date published;
    
    private int edition;
    
    /** The highest numbered page in the book,
     *  or the number of seconds in the recording.
     */
    private int length;
    
    private String url;
    
    public void read(Scanner in, PrintWriter out)
    {
        Set<String> tmpSet;
        
        do
        {
            title = DataUtil.getData(in,out,"Title");
        }while(title == null);
        
        do
        {
            type = DataUtil.getData(in,out,"Type");
        }while(type == null ||
               (!type.equalsIgnoreCase("b") && // book/paper
               !type.equalsIgnoreCase("r") && // audio recording
               !type.equalsIgnoreCase("v") // video recording
               ));
        
        type = type.toUpperCase();
        
        url = DataUtil.getData(in,out,"URL");
        
        publisher = DataUtil.getData(in,out,"Publisher");
        
        publisherLocation = DataUtil.getData(in,out,"Publication City");
        
        published = DataUtil.getDataDate(in,out,"Publication Date");
        
        edition = DataUtil.getDataInt(in,out,"Edition");
        
        length = DataUtil.getDataInt(in,out,"Length");
        
        authors = DataUtil.getDataSet(in,out,"Author");
        
        editors = DataUtil.getDataSet(in,out,"Editor");
        
        computeID();
    }
    
    public void computeID()
    {
        String repl = "[^A-Za-z0-9]+";
        
        String s = title.toUpperCase().replaceAll(repl,"");
        s = s.substring(0,Math.min(title.length(),5));
        while(s.length() < 5)
            s += "Z";
        ID = s;
        
        if(authors != null)
        {
            ArrayList<String> tmp = new ArrayList<String>();
            tmp.addAll(authors);
            Collections.sort(tmp);
            
            s = tmp.get(0).toUpperCase().replaceAll(repl,"");
            while(s.length() < 5)
                s = "0" + s;
            ID += s.substring(0,5);
        }else if(editors != null)
        {
            ArrayList<String> tmp = new ArrayList<String>();
            tmp.addAll(editors);
            Collections.sort(tmp);
            
            s = tmp.get(0).toUpperCase().replaceAll(repl,"");
            while(s.length() < 5)
                s = "0" + s;
            ID += s.substring(0,5);
        }else if(publisher != null)
        {
            ID += publisher.substring(0,5).toUpperCase().replaceAll(repl,"");
        }else
        {
            ID += "00000";
        }
        ID += type;
        
        if(published != null)
        {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
            ID += formatter.format(published);
        }else
        {
            ID += "0000";
        }
    }


// -------------- ACCESSORS --------------------
    public String toString()
    {
        return "";
    }
    
    public String getID()
    {
        return ID;
    }
// -------------- MUTATORS ---------------------

    public void setAuthors(Set<String> auth)
    { authors = auth; }
    
    public void setEditors(Set<String> ed)
    { editors = ed; }
}