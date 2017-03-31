import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Write a description of class Console here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Console
{
    
    //Consoleoutput in Datei?
    
    static BufferedReader br;
    
    public static void init()
    {
        br = new BufferedReader(new InputStreamReader(System.in));
        clear();
        writeln("ToastOS [Version " + Main.version + "]");
        writeln("(c) 2017 Toast Gmbh. All rights reserved.");
        writeln();
    }
    
    public static void clear()
    {
        write("\f");
    }
    
    public static void write(String e)
    {
        System.out.print(e);
    }
    
    public static void writeln(String e)
    {
        System.out.println(e);
    }
    
    public static void writeln()
    {
        System.out.println();
    }
    
    public static void errwrite(String e)
    {
        System.err.print(e);
    }
    
    public static void errwriteln(String e)
    {
        System.err.println(e);
    }
    
    public static void errwriteln()
    {
        System.err.println();
    }
    
    public static String readln()
    { 
        try
        {
            return br.readLine();
        }
        catch(Exception e) 
        {
            return "";
        }
    }
}
