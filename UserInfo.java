/**
 * Beschreiben Sie hier die Klasse UserInfo.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class UserInfo
{
    private final String user;
    private final String pcname;
   
    public UserInfo(String user, String pcname)
    {
       this.user = user;
       this.pcname = pcname;
    }
    
    public String getUser()
    {
        return user;
    }
    
    public String getPcname()
    {
        return pcname;
    }
}
