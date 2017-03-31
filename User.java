import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.FileOutputStream;

/**
 * Write a description of class User here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class User
{
    private final Path path;
    private String name;
    private boolean pwexists;
    private int password;
    
    /**
     * Constructor for objects of class User
     */
    private User(Path path, String name, boolean pwexists, int password)
    {
        this.path = path;
        this.name = name;
        this.pwexists = pwexists;
        this.password = password;
    }
    
    public void save() throws java.io.IOException
    {
        int pos = 0;
        byte[] b = name.getBytes();
        byte[] data = new byte[6+b.length];
        data[pos]=(byte)b.length;
        pos++;
        for(int a = pos, i = 0;pos < a+b.length; pos++, i++)
            data[pos] = b[i];
        
        data[pos] = (byte)(pwexists? 1 : 0);
        pos++;
        
        byte[] c = new byte[] {
            (byte) ((password >> 24) & 0xFF),
            (byte) ((password >> 16) & 0xFF),   
            (byte) ((password >> 8) & 0xFF),   
            (byte) (password & 0xFF)};
        for(int a = pos, i = 0;pos < a+c.length; pos++, i++)
            data[pos] = c[i];
        
        FileOutputStream fos = new FileOutputStream(path.toString());
        fos.write(data);
        fos.flush();
        fos.close();
    }
    
    public static User getUser() throws java.io.IOException
    {
        Path path = Paths.get("test.txt");
        byte[] data = Files.readAllBytes(path);
        int pos = 1;
        
        String name = "";
        
        for(; pos < data[0] + 1; pos++)
        {
            name += (char)data[pos];
        }
        
        boolean pwexists = data[pos] != 0;
        pos++;
        
        int password = 0;
        password = data[pos+3] & 0xFF |
                    (data[pos+2] & 0xFF) << 8 |
                    (data[pos+1] & 0xFF) << 16 |
                    (data[pos] & 0xFF) << 24;
        pos+=4;
        
        Console.writeln(name);
        Console.writeln(pwexists+"");
        Console.writeln(password+"");
        return new User(path, name, pwexists, password);
    }
}
