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
    
    private static String CONSOLE_STRING = "=>";
    
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
        
        loadAllData();
        
        REPL(new Scanner(System.in), new PrintWriter(System.out));
        
        saveAllData();
    }
    
    private static void REPL(Scanner in, PrintWriter out)
    {
        String choice = "";
        
        while(!choice.equalsIgnoreCase("quit") 
            && !choice.equalsIgnoreCase("exit"))
        {
            // print console
            printConsole(out);
            
            // read choice
            choice = in.nextLine();
            
            // do something based on choice
            choice = choice.toLowerCase();
            
            if(choice.equals("refresh") || choice.equals("r"))
            {
                loadAllData();
                out.println("Data refreshed from file system.");
                out.flush();
            }
            
            if(choice.equals("add person") || choice.equals("ap"))
            {
                addPerson(in,out);
            }
            
            if(choice.equals("add index") || choice.equals("ai"))
            {
                addIndex(in,out);
            }
            
            if(choice.equals("add collection") || choice.equals("ac"))
            {
                addCollection(in,out);
            }
            
            if(choice.equals("add song") || choice.equals("as"))
            {
                addSong(in,out);
            }
            
            if(choice.equals("search index") || choice.equals("si"))
            {
                findIndexByTitle(in,out);
            }
            
            if(choice.equals("search song") || choice.equals("ss"))
            {
                findSongByTitle(in,out);
            }
            
            if(choice.equals("search collection") || choice.equals("sc"))
            {
                findCollectionByTitle(in,out);
            }
            
            if(choice.equals("search person") || choice.equals("sp"))
            {
                findPerson(in,out);
            }
        }
    }
    
    private static void printConsole(PrintWriter out)
    {
        out.print(CONSOLE_STRING);
        out.flush();
    }
    
    /* ------------------------------------------------------- */
    /* -------------------- SEARCH --------------------------- */
    /* ------------------------------------------------------- */
    
    public static void findIndexByTitle(Scanner in, PrintWriter out)
    {
        out.println("write findIndexbytitle");
        out.flush();
    }
    
    public static void findSongByTitle(Scanner in, PrintWriter out)
    {
        out.println("write findsongbytitle");
        out.flush();
    }
    
    public static void findCollectionByTitle(Scanner in, PrintWriter out)
    {
        out.println("write findcollectionbytitle");
        out.flush();
    }
    
    public static void findPerson(Scanner in, PrintWriter out)
    {
        out.println("write findperson");
        out.flush();
    }
    
    /* ------------------------------------------------------- */
    /* -------------------- ADD OBJECT ----------------------- */
    /* ------------------------------------------------------- */
    
    public static void addPerson(Scanner in, PrintWriter out)
    {
        out.println("write addperson");
        out.flush();
    }
    
    public static void addIndex(Scanner in, PrintWriter out)
    {
        out.println("write addindex");
        out.flush();
    }
    
    public static void addCollection(Scanner in, PrintWriter out)
    {
        out.println("write addcollection");
        out.flush();
    }
    
    public static void addSong(Scanner in, PrintWriter out)
    {
        out.println("write addsong");
        out.flush();
    }
    
    /* ------------------------------------------------------- */
    /* -------------------- LOAD DATA ------------------------ */
    /* ------------------------------------------------------- */
    
    private static void loadAllData()
    {
        loadPeople();
        loadIndices();
        loadCollections();
        loadSongs();
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
    
    private static void loadIndices()
    {
        indices = new HashSet<Index>();
        File indexDir = new File(config.IndexDir);
        
        for(File f : indexDir.listFiles(xmlFileFilter))
        {
            indices.add((Index)loadObjectFile(f.getAbsolutePath(),
                "Could not open index file " + f.getAbsolutePath() +":"));
        }
    }
    
    private static void loadCollections()
    {
        collections = new HashSet<SongCollection>();
        File collectionDir = new File(config.CollectionDir);
        
        for(File f : collectionDir.listFiles(xmlFileFilter))
        {
            collections.add((SongCollection)loadObjectFile(f.getAbsolutePath(),
                "Could not open collection file " + f.getAbsolutePath() +":"));
        }
    }
    
    private static void loadSongs()
    {
        songs = new HashSet<Song>();
        File songDir = new File(config.SongDir);
        
        for(File f : songDir.listFiles(xmlFileFilter))
        {
            songs.add((Song)loadObjectFile(f.getAbsolutePath(),
                "Could not open song file " + f.getAbsolutePath() +":"));
        }
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
    
    /* ------------------------------------------------------- */
    /* -------------------- SAVE DATA ------------------------ */
    /* ------------------------------------------------------- */
    
    private static void saveAllData()
    {
        savePeople();
        saveIndices();
        saveCollections();
        saveSongs();
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
    
    private static void saveIndices()
    {
        File indexDir = new File(config.IndexDir);
        
        for(Index i : indices)
        {
            saveObjectFile(indexDir.getAbsolutePath() + i.getID(), i,
                "Could not save index " + i.getID() + ":");
        }
    }
    
    private static void saveCollections()
    {
        File collectionDir = new File(config.CollectionDir);
        
        for(SongCollection c : collections)
        {
            saveObjectFile(collectionDir.getAbsolutePath() + c.getID(), c,
                "Could not save collection " + c.getID() + ":");
        }
    }
    
    private static void saveSongs()
    {
        File songDir = new File(config.SongDir);
        
        for(Song s : songs)
        {
            saveObjectFile(songDir.getAbsolutePath() + s.getID(), s,
                "Could not save song " + s.getID() + ":");
        }
    }
    
    /* ------------------------------------------------------- */
    /* ------------------- FILE MANAGEMENT ------------------- */
    /* ------------------------------------------------------- */
    
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

