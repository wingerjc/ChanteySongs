package ChanteySongs.Data;

import java.util.*;
import java.io.*;

public class Index implements CLIInput
{
    /** The index ID.
     * This should be in the format of:
     * indextype.indexid
     * 
     * For example, Round index numbers have type R 
     * so Roud 1234 get and index ID of "R.1234"
     * Index types should be unique to each external
     * Idexer (or indexing system) to prevent collisions.
     */
    private String ID;
    
    /** A Set of ID's that have similar titles.
     *  Think of them as unconfirmed cross references.
     */
    private Set<String> seeAlso;
    
    /** A Set of other IDs that reference the same
     *  song/melody/story.
     */
    private Set<String> crossRef;
    
    public void read(Scanner in, PrintWriter out)
    {
        ID = DataUtil.getData(in,out,"ID");
        
        seeAlso = DataUtil.getDataSet(in,out,"See also");
        
        crossRef = DataUtil.getDataSet(in,out,"Cross Reference");
    }
    
// ------------------------- ACCESSORS -----------------------
    public String getID()
    { return ID; }
    
    public Set<String> getSeeAlso()
    { return seeAlso; }
    
    public Set<String> getCrossRef()
    { return crossRef; }
    
    public String getSeeAlsoString()
    {
        String ret = "";
        for(String s : seeAlso)
        {
            ret += s + ", ";
        }
        
        return ret.substring(0,ret.length()-2);
    }
    
    public String getCrossRefString()
    {
        String ret = "";
        for(String s : crossRef)
        {
            ret += s + ", ";
        }
        
        return ret.substring(0,ret.length()-2);
    }
    
    public String toString()
    {
        return "ID: " + ID + "\n" +
            "See also: " + getSeeAlsoString() + "\n" +
            "Cross Reference: " + getCrossRefString();
    }
// ------------------------- MUTATORS -----------------------
}