package ChanteySongs.Data;

import java.io.*;
import java.util.*;
import java.text.*;


public class Person implements Serializable, CLIInput
{
    private String firstName;
    private String middleName;
    private String lastName;
    
    /** Dr., Mr., Ms., etc. */
    private String title;
    
    /** Jr., Sr., etc. */
    private String suffix;
    
    /** Date of birth, null if unknown */
    private Date born;
    
    /** Date of death, null if unknown */
    private Date died;
    
    /** unique identifier */
    private String ID;
    
    public Person()
    {
        // nothing to do...
    }
    /** read in from the command line */
    public void read(Scanner in, PrintWriter out)
    {
        firstName = DataUtil.getData(in,out,"First name");
        
        middleName = DataUtil.getData(in,out,"Middle name");
        
        lastName = DataUtil.getData(in,out,"Last name");
        
        title = DataUtil.getData(in,out,"Title");
        
        suffix = DataUtil.getData(in,out,"Suffix");
        
        born = DataUtil.getDataDate(in,out,"Date of birth");
        
        died = DataUtil.getDataDate(in,out,"Date of death");
        
        computeID();
    }
    
    private void computeID()
    {
        if(lastName != null)
            ID = lastName;
        else
            ID = "FFFF";
        
        if(firstName != null)
            ID += firstName;
        else
            ID += "LLLL";
        
        if(middleName != null)
            ID += middleName.charAt(0);
        else
            ID += "_";
        
        if(born != null)
            ID += (new SimpleDateFormat("yyyy")).format(born);
        else
            ID += "0000";
    }
    
// ------------------- ACCESSORS -------------------------
    public String getFirstName()
    { return firstName; }
    
    public String getMiddleName()
    { return middleName; }
    
    public String getLastName()
    { return lastName; }
    
    public String getTitle()
    { return title; }
    
    public String getSuffix()
    { return suffix; }
    
    public Date getBorn()
    { return born; }
    
    public Date getdied()
    { return died; }
    
    public String getID()
    { return ID; }
    
    public String toString()
    {
        return ID + ":" +
            ((title == null)? "" : (" " + title)) +
            (( firstName == null)? "" : (" " + firstName)) +
            (( middleName == null)? "" : (" " + middleName)) +
            (( lastName == null)? "" : (" " + lastName)) +
            (( suffix == null)? "" : (" " + suffix)) +
            " (B: " +
            (( born == null)? "?" : DataUtil.formatter.format(born)) +
            " D: " + 
            (( died == null)? "?" : DataUtil.formatter.format(died)) +
            ")";
    }
// ------------------- MUTATORS -------------------------
    public void setID(String ID)
    { this.ID = ID; }
}