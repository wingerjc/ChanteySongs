package ChanteySongs.CLI;


import java.util.*;
import java.io.*;

import com.thoughtworks.xstream.*;
import com.thoughtworks.xstream.io.*;
import com.thoughtworks.xstream.io.xml.*;

import ChanteySongs.Data.*;

public class MainCLI
{
    private static Set<Person> people;
    private static Set<Index> indices;
    private static Set<SongCollection> collections;
    private static Set<Song> songs;
    
    private static Configuration config;
    
    public static void main(String[] args) throws Exception
    {
        XStream xstream = new XStream(new DomDriver());
        DataUtil.prepare(xstream);
        
        loadConfig();
    }
    
    public static class loadConfig()
    {
        File f = new File("MainCLI.cfg");
        
        if(!file.exists())
        {
            config = new Configuration();
            
            // need error handling
            PrintWriter out = new PrintWriter("MainCLI.cfg");
            out.println(xstream.toXML(config));
            out.close();
        }
        
        
    }
}

