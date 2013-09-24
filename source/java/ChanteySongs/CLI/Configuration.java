package ChanteySongs.CLI;

import java.io.*;

public class Configuration implements Serializable
{
    public String PersonDir;
    public String IndexDir;
    public String CollectionDir;
    public String SongDir;
    
    public Configuration()
    {
        PersonDir = ".";
        IndexDir = ".";
        CollectionDir = ".";
        SongDir = ".";
    }
}

