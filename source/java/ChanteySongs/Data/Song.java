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
        
    }
    
    public void computeID()
    {
        ID = indexID + ".";
        ID += collection + ".";
        ID += collectionLocation;
    }
}