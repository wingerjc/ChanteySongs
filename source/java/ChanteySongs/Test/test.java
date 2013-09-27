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
        DataUtil.prepare(xstream);
        
        //testPerson(xstream);
        //testIndex(xstream);
        //testCollection(xstream);
        testSong(xstream);
    }
    
    public static void testPerson(XStream xstream)
    {
        Person p = new Person();
        
        p.read(new Scanner(System.in), new PrintWriter(System.out, true));
        
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
    
    public static void testCollection(XStream xstream)
    {
        SongCollection c = new SongCollection();
        c.read(new Scanner(System.in), new PrintWriter(System.out, true));
        
        System.out.println(xstream.toXML((Object)c));
        
        System.out.println("\n" + c);
    }
    
    public static void testSong(XStream xstream)
    {
        Song s = new Song();
        s.read(new Scanner(System.in), new PrintWriter(System.out, true));
        
        System.out.println(xstream.toXML((Object)s));
        
        System.out.println("\n" + s);
    }
}