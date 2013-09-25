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
    private static final String CONFIG_NAME = "MainCLI.cfg";
    
    private static XStream xstream;
    
    
    private static FileFilter xmlFileFilter = new FileFilter()
    {
        public boolean accept(File f)
        { return f.getName().endsWith(".xml"); }
        
        public String description()
        { return "FileFilter: *.xml"; }
    };
        
    
    
    public static void main(String[] args) throws Exception
    {
        xstream = new XStream(new DomDriver());
        DataUtil.prepare(xstream);
        
        
        // TODO load configDir from command line args
        loadConfig("");
        
        loadPeople();
    }
    
    public static void loadConfig(String configDir)
    {
        Scanner in = null;
        File configFile = new File(configDir + CONFIG_NAME);
        
        if(!configFile.exists())
        {
            config = new Configuration();
            
            saveObjectFile(configFile.getAbsolutePath(), config,
                "Error priming configuration!\nCould not open " +
                configDir + CONFIG_NAME + ":");
        }
        
        config = (Configuration)loadObjectFile(configFile.getAbsolutePath(),
            "Error reading configuration!\nCould not open " + 
            configDir + CONFIG_NAME + ":");
    }
    
    private static void loadPeople()
    {
        people = new HashSet<Person>();
        File personDir = new File(config.PersonDir);
        
        for(File f : personDir.listFiles(xmlFileFilter))
        {
            people.add((Person)loadObjectFile(f.getAbsolutePath(),
                "Could not open person file " + f.getAbsolutePath() +":"));
        }
    }
    
    private static void savePeople()
    {
        File personDir = new File(config.PersonDir);
        
        for(Person p : people)
        {
            saveObjectFile(personDir.getAbsolutePath() + p.getID(), p,
                "Could not save person " + p.getID() + ":");
        }
    }
    
    private static void saveObjectFile(String fileName, Object o, String error)
    {
        PrintWriter out = null;
        try
        {
            out = new PrintWriter(new File(fileName));
        }catch(IOException ioe)
        {
            System.err.println(error);
            System.exit(1);
        }
        out.println(xstream.toXML(config));
        out.close();
    }
    
    private static Object loadObjectFile(String fileName, String error)
    {
        Scanner in = null;
        
        try
        {
            in = new Scanner(new File(fileName));
        }catch(IOException ioe)
        {
            System.err.println(error);
            ioe.printStackTrace(System.err);
            System.exit(1);
        }
        
        Object ret = xstream.fromXML(getXML(in));
        in.close();
        return ret;
    }
    
    private static String getXML(Scanner in)
    {
        String ret = "";
        while(in.hasNextLine())
        {
            ret += in.nextLine() + "\n";
        }
        
        return ret;
    }
}

