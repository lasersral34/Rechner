import java.util.concurrent.TimeUnit;

//import java.util.Scanner;

/**
 * Beschreiben Sie hier die Klasse Fakultaet.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Rechner
{
    public static long Fakultaetrekursiv(long n)
    {
        return (n < 0) ? 0 : ((n == 0) ? 1 : (Fakultaetrekursiv(n-1)*n));
    }
    
    public static long FakultaetiterativWHILE(long n)
    {
        if(n < 0)
            return 0;
        long e = 1;
        while(n > 1)
        {
            e*=n;
            n--;
        }
        return e;
    }
    
    public static long FakultaetiterativFOR(long n)
    {
        if(n < 0)
            return 0;
        long e = 1;
        for(long i = n; i > 0; i--)
        {
            e*=i;
        }
        return e;
    }
    
    public static long FibonacciZahl(long n)
    {
        long a = 1, b = 1;
        for(long i = 0; i < (n - 2); i++)
        {
            long t = a;
            a = b;
            b = t;
            a += b;
        }
        return a;
    }
}
