import java.util.concurrent.TimeUnit;
/**
 * Beschreiben Sie hier die Aufzählungsklasse Commands.
 * 
 * @author  (Ihr Name)
 * @version (eine Versionsnummer oder ein Datum)
 */
public enum Commands
{
    FAKULTÄT("fakultaet", "Fakultätiviert eine Zahl.")
    {
        public FctResponse fct(String[] args, UserInfo userinfo)
        {
            if(args.length == 3)
            {
                long e = 0;
                switch(args[1])
                {
                    case "iterativfor":
                    e = Rechner.FakultaetiterativFOR(Long.parseLong(args[2]));
                    break;
                    case "iterativwhile":
                    e = Rechner.FakultaetiterativWHILE(Long.parseLong(args[2]));
                    break;
                    case "rekursiv":
                    e = Rechner.Fakultaetrekursiv(Long.parseLong(args[2]));
                    break;
                }
                Console.writeln("Das Ergebnis beträgt: " + e);
                return FctResponse.OK;
            }
            else
            {
                Console.writeln("Falsche Anzahl der Argumente!");
                return FctResponse.WARGS;
            }
        }
    },
    
    
    CALC("=", "Löst einfache mathematische Rechnungen.")
    {
        public FctResponse fct(String[] args, UserInfo userinfo)
        {
            double e = Double.NaN;
            if(args.length == 4)
            {
                switch(args[2])
                {
                    case "+":
                        e = Double.parseDouble(args[1]) + Double.parseDouble(args[3]);
                    break;
                    case "-":
                        e = Double.parseDouble(args[1]) - Double.parseDouble(args[3]);
                    break;
                    case "*":
                        e = Double.parseDouble(args[1]) * Double.parseDouble(args[3]);
                    break;
                    case "/":
                        e = Double.parseDouble(args[1]) / Double.parseDouble(args[3]);
                    break;
                    case "%":
                        e = Double.parseDouble(args[1]) % Double.parseDouble(args[3]);
                    break;
                    case "^":
                        e = Math.pow(Double.parseDouble(args[1]), Double.parseDouble(args[3]));
                    break;
                    case "root":
                        e = Math.pow(Double.parseDouble(args[3]), 1/(Double.parseDouble(args[1])));
                    break;
                    case "log":
                        e = Math.log(Double.parseDouble(args[3]))/Math.log(Double.parseDouble(args[1]));
                }
                
                Console.writeln(args[1] + " " + args[2] + " " + args[3] + " = " + e);
                return FctResponse.OK;
            }
            else if(args.length == 3)
            {
                switch(args[1])
                {
                    case "sqrt":
                        e = Math.pow(Double.parseDouble(args[2]), 0.5);
                        break;
                }
                
                Console.writeln(args[1] + " " + args[2]  + " = " + e);
                return FctResponse.OK;
            }
            else
            {
                Console.writeln("Falsche Anzahl der Argumente!");
                return FctResponse.WARGS;
            }
        }
    },
    
    FIBONACCI_ZAHL("fibonaccizahl", "Gibt die Fibonaccizahl an entsprechender Stelle an.")
    {
        public FctResponse fct(String[] args, UserInfo userinfo)
        {
            if(args.length == 2)
            {
                Console.writeln("Die " + Rechner.FibonacciZahl(Long.parseLong(args[1])) + ". Fibonacci-Zahl ist " + args[1] + ".");
                return FctResponse.OK;
            }
            else
            {
                Console.writeln("Falsche Anzahl der Argumente!");
                return FctResponse.WARGS;
            }
        }
    },
    SPEEDTEST("speedtest", "Speedtestiviert einen Befehl.")
    {
        public FctResponse fct(String[] args, UserInfo userinfo)
        {
            if(args.length > 1)
            {
                Console.writeln();
                Console.writeln("---------");
                Console.writeln("SPEEDTEST");
                Console.writeln("---------");
                Console.writeln();
                
                Commands cmd = Parse(args[1]);
                if(cmd != null)
                {
                    Console.writeln("Speedtest für den Befehl " + cmd + ":");
                    Console.writeln();
                    
                    String[] nargs = new String[args.length - 1];
                    System.arraycopy(args, 1, nargs, 0, args.length-1);
                    
                    long t = System.nanoTime();
                    cmd.fct(nargs, userinfo);
                    long d = System.nanoTime()-t;
                    
                    Console.writeln();
                    Console.writeln("Es dauerte " + d + "ns.");
                    return FctResponse.OK;
                }
                else
                {
                    Console.writeln("Der Befehl \"" + args[1] + "\" wurde nicht gefunden.\nBenutze help für eine liste and Befehlen.");
                    return FctResponse.ERROR;
                }
            }
            else
            {
                return FctResponse.WARGS;
            }
        }
    },
    
    REPEAT("repeat", "Wiederholt einen Command")
    {
        public FctResponse fct(String[] args, UserInfo userinfo)
        {
            if(args.length > 2)
            {
                Commands cmd = Parse(args[2]);
                long anzahl = Long.parseLong(args[1]);
                if(cmd != null)
                {
                    String[] nargs = new String[args.length - 2];
                    System.arraycopy(args, 2, nargs, 0, args.length-2);
                    for(long i = 0; i < anzahl; i++)
                    {
                        cmd.fct(nargs, userinfo);
                    }
                    return FctResponse.OK;
                }
                else
                {
                    Console.writeln("Der Befehl \"" + args[2] + "\" wurde nicht gefunden.\nBenutze help für eine liste and Befehlen.");
                    return FctResponse.ERROR;
                }
            }
            else
            {
                return FctResponse.WARGS;
            }
        }
    },
    
    CLEAR("clear", "Löscht den Text auf der Konsole.")
    {
        public FctResponse fct(String[] args, UserInfo userinfo)
        {
            if(args.length == 1)
            {
                Console.clear();
                return FctResponse.OK;
            }
            else
            {
                return FctResponse.WARGS;
            }
        }
    },
    
    LOGOUT("logout", "Meldet Benutzer ab.")
    {
        public FctResponse fct(String[] args, UserInfo userinfo)
        {
            if(args.length == 1)
            {
                Console.writeln("Auf Wiedersehen " + userinfo.getUser() + "!");
                //logout
                return FctResponse.ERROR;
            }
            else
            {
                return FctResponse.WARGS;
            }
        }
    },
    
    RESTART("restart", "Startet Anwendung neu.")
    {
        public FctResponse fct(String[] args, UserInfo userinfo)
        {
            if(args.length == 1)
            {
                Console.clear();
                Console.write("rebooting");
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
                //logout
                Console.init();
                return FctResponse.ERROR;
            }
            else
            {
                return FctResponse.WARGS;
            }
        }
    },
    
    SHUTDOWN("shutdown", "Fährt Anwendung herunter.")
    {
        public FctResponse fct(String[] args, UserInfo userinfo)
        {
            if(args.length == 1)
            {
                //shutdown
                return FctResponse.ERROR;
            }
            else
            {
                return FctResponse.WARGS;
            }
        }
    },
    
    HELP("help", "Gibt eine Liste aller Commands oder einen Command mit seiner Beschreibung an.")
    {
        public FctResponse fct(String[] args, UserInfo userinfo)
        {
            if(args.length == 2)
            {
                Commands cmd = Parse(args[1]);
                if(cmd!=null)
                {
                    Console.writeln(cmd.getCommand() + ": " + cmd.getDesrciption());
                    return FctResponse.OK;
                }
                else
                {
                    Console.writeln("Command nicht gefunden. Benutze help um dir eine Liste aller Commands anzeigen zu lassen.");
                    return FctResponse.ERROR;
                }
            }
            else if(args.length == 1)
            {
                Console.writeln("Folgende Commands stehen zur Verfügung:");
                Commands[] cmds = Commands.values();
                for(int i = 0; i < cmds.length; i++)
                {
                    
                    Console.writeln("[" + (i+1) + "] " + cmds[i].getCommand());
                    return FctResponse.OK;
                }
            }
            return FctResponse.WARGS;
        }
    },
    
    SAMARITAN("samaritan", "HILFE! DIE RUSSEN KOMMEN!")
    {
        public FctResponse fct(String[] args, UserInfo userinfo)
        {
            if(userinfo.getPcname() == "TheMachine")
            {
                    throw new RuntimeException("ENEMY DETECTED");
            }
            return FctResponse.OK;
        }
    };

    protected final String command;
    protected final String description;

    private Commands(final String command, final String description) 
    {
        this.command = command;
        this.description = description;
    }
    
    public String getCommand() 
    {
        return command;
    }
    
    public String getDesrciption() 
    {
        return description;
    }
    
    public abstract FctResponse fct(String[] args, UserInfo userinfo);
    
    public static Commands Parse(String cmdname)
    {
        for(Commands command : Commands.values())
        {
            if(command.getCommand().equals(cmdname))
            {
                return command;
            }
        }
        return null;
    }
}
