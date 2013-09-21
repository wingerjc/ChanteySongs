package ChanteySongs.Test;

import java.util.*;
import java.io.*;

import com.thoughtworks.xstream.*;
import com.thoughtworks.xstream.io.xml.*;

import ChanteySongs.Data.*;

public class test
{
    public static void main(String[] args) throws IOException
    {
        XStream xstream = new XStream(new DomDriver());
        xstream.alias("Person",Person.class);
        
        //testPerson(xstream);
        testIndex(xstream);
    }
    
    public static void testPerson(XStream xstream)
    {
        Person p = new Person();
        
        p.read(new Scanner(System.in), new PrintWriter(System.out, true));
        p.setID("1024");
        
        System.out.println(xstream.toXML(p));
        
        System.out.println("\n" + p);
    }
    
    public static void testIndex(XStream xstream)
    {
        Index i = new Index();
        i.read(new Scanner(System.in), new PrintWriter(System.out, true));
        
        System.out.println(xstream.toXML(i));
        
        System.out.println("\n" + i);
    }
}