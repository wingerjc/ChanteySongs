package ChanteySongs.Data;

import java.io.*;
import java.util.*;


public class Song implements Serializable, CLIInput
{
    private String ID;
    
    private String title;
    
    private String indexID;
    
    private String collection;
    
    private int collectionLocation;
    
    private String lyrics;
    
    private String abc;
    
    private String collectionGeographicLocation;
    
    private Set<String> lyricists;
    
    private Set<String> composers;
    
    private Set<String> arrangers;
    
    private Set<String> performers;
    
    private Set<String> collectors;
    
    public void read(Scanner in, PrintWriter out)
    {
        title = DataUtil.getData(in,out,"Title");
        
        indexID  = DataUtil.getData(in,out,"Index");
        
        collection = DataUtil.getData(in,out,"Collection");
        
        collectionLocation = DataUtil.getDataInt(in,out,"Page/Time");
        
        lyrics = DataUtil.getDataMultiLine(in,out,"Lyrics");
        
        abc = DataUtil.getDataMultiLine(in,out,"ABC");
        
        collectionGeographicLocation =
            DataUtil.getData(in,out,"Collection Location");
        
        lyricists = DataUtil.getDataSet(in,out,"Lyricist");
        
        composers = DataUtil.getDataSet(in,out,"Composer");
        
        arrangers = DataUtil.getDataSet(in,out,"Arranger");
        
        performers = DataUtil.getDataSet(in,out,"Performer");
        
        collectors = DataUtil.getDataSet(in,out,"Collector");
        
        computeID();
    }
    
    public void computeID()
    {
        ID = indexID + ".";
        ID += collection + ".";
        ID += collectionLocation;
    }
    
    public String getID()
    { return ID; }
    
    public String getTitle()
    { return title; }
    
    public String getCollection()
    { return collection; }
    
    public String getIndexID()
    { return indexID; }
}