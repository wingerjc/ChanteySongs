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
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String tmp = "";
        
        out.print("First Name: ");
        out.flush();
        tmp = in.nextLine();
        firstName = tmp.length() == 0 ? null : tmp;
        
        out.print("Middle Name: ");
        out.flush();
        tmp = in.nextLine();
        middleName = tmp.length() == 0 ? null : tmp;
        
        out.print("Last Name: ");
        out.flush();
        tmp = in.nextLine();
        lastName = tmp.length() == 0 ? null : tmp;
        
        out.print("Title: ");
        out.flush();
        tmp = in.nextLine();
        title = tmp.length() == 0 ? null : tmp;
        
        out.print("Suffix: ");
        out.flush();
        tmp = in.nextLine();
        suffix = tmp.length() == 0 ? null : tmp;
        
        try
        {
            out.print("Date of Birth: ");
            out.flush();
            tmp = in.nextLine();
            born = tmp.length() == 0 ? null : formatter.parse(tmp);
        }catch(ParseException pe)
        {
            System.err.println("Could not parse Birth Date: " + tmp);
            born = null;
        }
        
        try
        {
            out.print("Date of Death: ");
            out.flush();
            tmp = in.nextLine();
            died = tmp.length() == 0 ? null : formatter.parse(tmp);
        }catch(ParseException pe)
        {
            System.err.println("Could not parse Death Date: " + tmp);
            born = null;
        }
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
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        return ID + ":" +
            ((title == null)? "" : (" " + title)) +
            (( firstName == null)? "" : (" " + firstName)) +
            (( middleName == null)? "" : (" " + middleName)) +
            (( lastName == null)? "" : (" " + lastName)) +
            (( suffix == null)? "" : (" " + suffix)) +
            " (B: " +
            (( born == null)? "?" : formatter.format(born)) +
            " D: " + 
            (( died == null)? "?" : formatter.format(died)) +
            ")";
    }
// ------------------- MUTATORS -------------------------
    public void setID(String ID)
    { this.ID = ID; }
}