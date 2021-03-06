/**
 * Beschreiben Sie hier die Klasse Main.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Main
{
    public static String version = "0.0.1";
    
    public static String pcname = "MacBookAir";
    
    private static User activeUser;
    //userinfo = Users.getUserInfo("admin", "123456");
    
    public static void main()
    {
        Console.init();
        while(true)
        {
            while(activeUser == null)
            {
                if(User.getUserNames().length == 0)
                {
                    Console.writeln("Keine Benutzer vorhanden.");
                    System.exit(0);
                }
                else
                {
                    login();
                }
            }
            menue();
        }
    }

    private static void menue()
    {
        Console.write(activeUser.getName() + "@" + pcname + " ~ $ ");
        String[] e = Console.readln().toLowerCase().split(" ");
        
        boolean b = false; 
        for(Commands command : Commands.values())
        {
            if(command.getCommand().equals(e[0]))
            {
                useFctResponse(command.fct(e, activeUser.getUserInfo(pcname)));
                
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
                
            case WARGSC:
                Console.writeln("[ERROR]\tFalsche Anzahl an Argumenten!");
                break;
                
            case WARGS:
                Console.writeln("[ERROR]\tFalsches Argument!");
                break;
                
            case ERROR:
                Console.writeln("[ERROR]\tERROR");
                break;
                
            case LOGOUT:
                logout();
                break;
                
            case RESTART:
                restart();
                break;
                
            case SHUTDOWN:
                shutdown();
                break;
                
            default:
            
                break;
        }
    }
    
    private static void logout()
    {
        Console.writeln("Auf Wiedersehen " + activeUser.getName() + "!");
        Console.writeln();
        activeUser = null;
    }
    
    private static void restart()
    {
        Console.clear();
        Console.write("restarting");
        try
        {
            Thread.sleep(500);
            for(int i = 0; i < 3; i++)
            {
                Console.write(".");
                Thread.sleep(1000);
            }
            }
        catch(Exception e) 
        {}
        logout();
        Console.init();
    }
    
    private static void shutdown()
    {
        logout();
        System.exit(0);
    }
    
    private static void login()
    {
        Console.write("name: ");
        String name = Console.readln();
        if(User.userNameExists(name))
        {
            if(User.userHasPassword(name))
            {
                Console.write("password: ");
                String pw = Console.readln();
                activeUser = User.getUser(name, pw);
                if(activeUser == null)
                    Console.writeln("Passwort falsch!");
                else
                    Console.clear();
            }
            else
            {
                activeUser = User.getUser(name);
                Console.clear();
            }
        }
        else
        {
            Console.writeln("Benutzername existiert nicht!");
        }
    }
}
