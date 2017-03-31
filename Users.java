
/**
 * Beschreiben Sie hier die Aufzählungsklasse Users.
 * 
 * @author  (Ihr Name)
 * @version (eine Versionsnummer oder ein Datum)
 */
public enum Users
{
    USER01("user01"),
    ADMIN("admin", "123456"),
    TESTUSER("testuser","testpw");
    
    private final String name;
    private final int password;
    private final boolean pwexist;
    
    private Users(final String name)
    {
        this.name = name;
        this.password = 0;
        this.pwexist = false;
    }
    private Users(final String name, final String password)
    {
        this.name = name;
        this.password = hash(name,password);
        this.pwexist = true;
    }
    
    private String getName()
    {
        return name;
    }
    
    private boolean getPwExist()
    {
        return pwexist;
    }
    
    private int getPassword()
    {
        return password;
    }
    
    private static int hash(String name, String password)
    {
        return (name+password).hashCode();
    }
    
    private static Users getUser(String name)
    {
        for(Users user : Users.values())
        {
            if(user.getName().equals(name))
            {
                return user;
            }
        }
        return null;
    }
    
    public static UserInfo getUserInfo(String name, String password)
    {
       return getUserInfo(getUser(name), password);
    }
    public static UserInfo getUserInfo(Users user, String password)
    {
        if(user != null)
        {
            if(user.getPassword() == hash(user.getName(),password))
            return new UserInfo(user.getName(), Main.pcname);
        }
        return null;
    }
    public static UserInfo getUserInfo(String name)
    {
       return getUserInfo(getUser(name));
    }
    public static UserInfo getUserInfo(Users user)
    {
        if(user != null)
        {
            if(!user.getPwExist())
                return new UserInfo(user.getName(), Main.pcname);
        }
        return null;
    }
}
