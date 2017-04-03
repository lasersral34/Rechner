import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.*;

/**
 * Write a description of class User here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class User
{
    private final String name;
    private final boolean pwexists;
    private final int password;
    
    /**
     * Constructor for objects of class User
     */
    private User(String name, boolean pwexists, int password)
    {
        this.name = name;
        this.pwexists = pwexists;
        this.password = password;
    }
    
    public static void delete(String pName)
    {
        File fs = new File("users");
        File[] fileArray = fs.listFiles();
        
        for(File f : fileArray)
        {
            try
            {
                byte[] data = Files.readAllBytes(f.toPath());
                
                if(data[0] != '.' || data[1] != 'U' || data[2] != 'S' || data[3] != 'E' || data[4] != 'R')
                {
                    continue;
                }
                
                int pos = 6;
                
                String name = "";
                
                for(; pos < data[5] + 6; pos++)
                {
                    name += (char)data[pos];
                }
                if(name.equals(pName))
                {
                    f.delete();
                }
            }
            catch(Exception e) {}
        }
    }
    
    public static void createRootUser()
    {
        create("root", "root");
    }
    
    public static void create(String name)
    {
        try
        {
            if(userNameExists(name))
                return;
            
            int pos = 0;
            byte[] b = name.getBytes();
            byte[] data = new byte[11+b.length];
            
            for(int a = pos, i = 0;pos < a+5; pos++, i++)
                data[pos] = (byte)".USER".charAt(i);
                
            data[pos]=(byte)b.length;
            pos++;
            
            for(int a = pos, i = 0;pos < a+b.length; pos++, i++)
                data[pos] = b[i];
                
            
            data[pos] = 0;
            pos+=5;
                
            int i = 0;
            File file = new File("users/user.usr");
                
            while(file.exists())
            {
                file = new File("users/user" + i + ".usr");
                i++;
            }
            
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data);
            fos.flush();
            fos.close();
        }
        catch(Exception e) {}
    }
    
    public static void create(String name, String password)
    {
        try
        {
            if(userNameExists(name))
                return;
            
            int pos = 0;
            byte[] b = name.getBytes();
            byte[] data = new byte[11+b.length];
            
            for(int a = pos, i = 0;pos < a+5; pos++, i++)
                data[pos] = (byte)".USER".charAt(i);
                
            data[pos]=(byte)b.length;
            pos++;
            
            for(int a = pos, i = 0;pos < a+b.length; pos++, i++)
                data[pos] = b[i];
                
            
            data[pos] = 1;
            pos++;
            
            int hp = hash(name, password);
            
            byte[] c = new byte[] {
                (byte) ((hp >> 24) & 0xFF),
                (byte) ((hp >> 16) & 0xFF),   
                (byte) ((hp >> 8) & 0xFF),   
                (byte) (hp & 0xFF)};
            for(int a = pos, i = 0;pos < a+c.length; pos++, i++)
                data[pos] = c[i];
                
            int i = 0;
            File file = new File("users/user.usr");
                
            while(file.exists())
            {
                file = new File("users/user" + i + ".usr");
                i++;
            }
            
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data);
            fos.flush();
            fos.close();
        }
        catch(Exception e) {}
    }
    
    public static boolean userNameExists(String pName)
    {
        File fs = new File("users");
        File[] fileArray = fs.listFiles();
        

        
        {
            try
            {
                byte[] data = Files.readAllBytes(f.toPath());
                
                if(data[0] != '.' || data[1] != 'U' || data[2] != 'S' || data[3] != 'E' || data[4] != 'R')
                    continue;
                
                int pos = 6;
                
                String name = "";
                
                for(; pos < data[5] + 6; pos++)
                {
                    name += (char)data[pos];
                }
                
                if(name.equals(pName))
                    return true;
                
            }
            catch(Exception e) {}
        }
        
        return false;
    }
    
    public static String[] getUserNames()
    {
        List<String> names = new ArrayList<String>();
        
        File fs = new File("users");
        File[] fileArray = fs.listFiles();
        
        for(File f : fileArray)
        {
            try
            {
                byte[] data = Files.readAllBytes(f.toPath());
                
                if(data[0] != '.' || data[1] != 'U' || data[2] != 'S' || data[3] != 'E' || data[4] != 'R')
                    continue;
                
                int pos = 6;
                
                String name = "";
                
                for(; pos < data[5] + 6; pos++)
                {
                    name += (char)data[pos];
                }
                
                names.add(name);
            }
            catch(Exception e) {}
        }
        
        return Arrays.copyOf(names.toArray(), names.size(), String[].class);
    }
    
    public static boolean userHasPassword(String pName)
    {
        File fs = new File("users");
        File[] fileArray = fs.listFiles();
        
        for(File f : fileArray)
        {
            try
            {
                byte[] data = Files.readAllBytes(f.toPath());
                
                if(data[0] != '.' || data[1] != 'U' || data[2] != 'S' || data[3] != 'E' || data[4] != 'R')
                    continue;
                
                int pos = 6;
                
                String name = "";
                
                for(; pos < data[5] + 6; pos++)
                {
                    name += (char)data[pos];
                }
                
                if(!name.equals(pName))
                    continue;
                
                boolean pwexists = data[pos] != 0;
                pos++;
                
                return pwexists;
            }
            catch(Exception e) {}
        }
        
        return false;
    }
    
    public static User getUser(String pName, String pPassword)
    {
        File fs = new File("users");
        File[] fileArray = fs.listFiles();
        
        for(File f : fileArray)
        {
            try
            {
                byte[] data = Files.readAllBytes(f.toPath());
                
                if(data[0] != '.' || data[1] != 'U' || data[2] != 'S' || data[3] != 'E' || data[4] != 'R')
                    continue;
                
                int pos = 6;
                
                String name = "";
                
                for(; pos < data[5] + 6; pos++)
                {
                    name += (char)data[pos];
                }
                
                if(!name.equals(pName))
                    continue;
                
                boolean pwexists = data[pos] != 0;
                pos++;
                
                int password = 0;
                password = data[pos+3] & 0xFF |
                            (data[pos+2] & 0xFF) << 8 |
                            (data[pos+1] & 0xFF) << 16 |
                            (data[pos] & 0xFF) << 24;
                pos+=4;
                
                if(!pwexists || password != hash(name, pPassword))
                    continue;
                
                return new User(name, pwexists, password);
            }
            catch(Exception e) {}
        }
        
        return null;
    }
    
    public static User getUser(String pName)
    {
        File fs = new File("users");
        File[] fileArray = fs.listFiles();
        
        for(File f : fileArray)
        {
            try
            {
                byte[] data = Files.readAllBytes(f.toPath());
                
                if(data[0] != '.' || data[1] != 'U' || data[2] != 'S' || data[3] != 'E' || data[4] != 'R')
                    continue;
                
                int pos = 6;
                
                String name = "";
                
                for(; pos < data[5] + 6; pos++)
                {
                    name += (char)data[pos];
                }
                
                if(!name.equals(pName))
                    continue;
                
                boolean pwexists = data[pos] != 0;
                pos++;
                
                int password = 0;
                password = data[pos+3] & 0xFF |
                            (data[pos+2] & 0xFF) << 8 |
                            (data[pos+1] & 0xFF) << 16 |
                            (data[pos] & 0xFF) << 24;
                pos+=4;
                
                if(pwexists)
                    continue;
                
                return new User(name, pwexists, password);
            }
            catch(Exception e) {}
        }
        
        return null;
    }
    
    public UserInfo getUserInfo(String pcname)
    {
        return new UserInfo(name, pcname);
    }
    
    private static int hash(String name, String password)
    {
        return (name+password).hashCode();
    }
    
    public boolean getPwExists()
    {
        return pwexists;
    }
    
    public String getName()
    {
        return name;
    }
}
