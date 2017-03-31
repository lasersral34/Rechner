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
        public void fct(String[] args, UserInfo userinfo)
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
            }
            else
            {
                Console.writeln("Falsche Anzahl der Argumente!");
            }
        }
    },
    
    
    CALC("=", "Löst einfache mathematische Rechnungen.")
    {
        public void fct(String[] args, UserInfo userinfo)
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
            }
            else
            {
                Console.writeln("Falsche Anzahl der Argumente!");
            }
        }
    },
    
    FIBONACCI_ZAHL("fibonaccizahl", "Gibt die Fibonaccizahl an entsprechender Stelle an.")
    {
        public void fct(String[] args, UserInfo userinfo)
        {
            if(args.length == 2)
            {
                Console.writeln("Die " + Rechner.FibonacciZahl(Long.parseLong(args[1])) + ". Fibonacci-Zahl ist " + args[1] + ".");
            }
            else
            {
                Console.writeln("Falsche Anzahl der Argumente!");
            }
        }
    },
    SPEEDTEST("speedtest", "Speedtestiviert einen Befehl.")
    {
        public void fct(String[] args, UserInfo userinfo)
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
                }
                else
                {
                    Console.writeln("Der Befehl \"" + args[1] + "\" wurde nicht gefunden.\nBenutze help für eine liste and Befehlen.");
                }
            }
            else
            {
                Console.writeln("Falsche Anzahl der Argumente!");
            }
        }
    },
    
    REPEAT("repeat", "Wiederholt einen Command")
    {
        public void fct(String[] args, UserInfo userinfo)
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
                }
                else
                {
                    Console.writeln("Der Befehl \"" + args[2] + "\" wurde nicht gefunden.\nBenutze help für eine liste and Befehlen.");
                }
            }
            else
            {
                Console.writeln("Falsche Anzahl der Argumente!");
            }
        }
    },
    
    CLEAR("clear", "Löscht den Text auf der Konsole.")
    {
        public void fct(String[] args, UserInfo userinfo)
        {
            if(args.length == 1)
            {
                Console.clear();
            }
            else
            {
                Console.writeln("Falsche Anzahl der Argumente!");
            }
        }
    },
    
    LOGOUT("logout", "Meldet Benutzer ab.")
    {
        public void fct(String[] args, UserInfo userinfo)
        {
            if(args.length == 1)
            {
                Console.writeln("Auf Wiedersehen " + userinfo.getUser() + "!");
                //logout
            }
            else
            {
                Console.writeln("Falsche Anzahl der Argumente!");
            }
        }
    },
    
    RESTART("restart", "Startet Anwendung neu.")
    {
        public void fct(String[] args, UserInfo userinfo)
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
            }
            else
            {
                Console.writeln("Falsche Anzahl der Argumente!");
            }
        }
    },
    
    SHUTDOWN("shutdown", "Fährt Anwendung herunter.")
    {
        public void fct(String[] args, UserInfo userinfo)
        {
            if(args.length == 1)
            {
                //shutdown
            }
            else
            {
                Console.writeln("Falsche Anzahl der Argumente!");
            }
        }
    },
    
    HELP("help", "Gibt eine Liste aller Commands oder einen Command mit seiner Beschreibung an.")
    {
        public void fct(String[] args, UserInfo userinfo)
        {
            if(args.length == 2)
            {
                Commands cmd = Parse(args[1]);
                if(cmd!=null)
                    Console.writeln(cmd.getCommand() + ": " + cmd.getDesrciption());
                else
                    Console.writeln("Command nicht gefunden. Benutze help um dir eine Liste aller Commands anzeigen zu lassen.");
            }
            else if(args.length == 1)
            {
                Console.writeln("Folgende Commands stehen zur Verfügung:");
                Commands[] cmds = Commands.values();
                for(int i = 0; i < cmds.length; i++)
                {
                    
                    Console.writeln("[" + (i+1) + "] " + cmds[i].getCommand());
                }
            }
            else
            {
                Console.writeln("Falsche Anzahl der Argumente!");
            }
        }
    },
    
    SAMARITAN("samaritan", "HILFE! DIE RUSSEN KOMMEN!")
    {
        public void fct(String[] args, UserInfo userinfo)
        {
            if(userinfo.getPcname() == "TheMachine")
                    throw new RuntimeException("ENEMY DETECTED");
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
    
    public abstract void fct(String[] args, UserInfo userinfo);
    
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
