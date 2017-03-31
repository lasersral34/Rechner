/**
 * Beschreiben Sie hier die Klasse Main.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Main
{
    public static String version = "0.0.1";
    
    public static String pcname = "TheMachine";
    
    private static UserInfo userinfo;
    //userinfo = Users.getUserInfo("admin", "123456");
    
    public static void main()
    {
        Console.init();
        while(true)
        {
            //anmeldung richtig machen
            while(userinfo == null)
            {
                login();
            }
            menue();
        }
    }

    private static void menue()
    {
        Console.write(userinfo.getUser() + "@" + userinfo.getPcname() + " ~ $ ");
        String[] e = Console.readln().toLowerCase().split(" ");
        
        boolean b = false; 
        for(Commands command : Commands.values())
        {
            if(command.getCommand().equals(e[0]))
            {
                useFctResponse(command.fct(e, userinfo));
                
                b = true;
                break;
            }
        }
        if(!b)
            Console.writeln("Command nicht gefunden. Benutze help um dir eine Liste aller Commands anzeigen zu lassen.");
    }
    
    private static void useFctResponse(FctResponse fctresponse)
    {
        switch(fctresponse)
        {
            case OK:
                
                break;
                
            case WARGS:
            
                break;
                
            case ERROR:
                
                break;
                
            case LOGOUT:
                logout();
                break;
                
            case SHUTDOWN:
                shutdown();
                break;
        }
    }
    
    private static void logout()
    {
        
    }
    
    private static void shutdown()
    {
        
    }
    
    private static void login()
    {
        Console.write("name: ");
        String name = Console.readln();
        Console.write("password: ");
        String pw = Console.readln();
        
        if(pw.equals(""))
            userinfo = Users.getUserInfo(name);
        else
            userinfo = Users.getUserInfo(name, pw);
        if(userinfo == null)
            Console.writeln("Anmeldung nicht erfolgreich. Anmeldedaten überprüfen!");
        else
            Console.clear();
    }
}
