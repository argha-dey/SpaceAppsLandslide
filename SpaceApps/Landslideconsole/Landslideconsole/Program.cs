using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using LandSlideClass;
using System.Timers;
using System.Threading;
namespace Landslideconsole
{
    class Program
    {
        static void Main(string[] args)
        {
            int Hour, Minute;
            double FactorSafety;
            string data = string.Empty;
            for (int n = 0; n < 6; n++)
            {
                Analysis Analysis1 = new Analysis();
                Analysis1.CreateData();

                Analysis1.Run(n);
                FactorSafety = Analysis1.FactorSafety;
                Hour = Analysis1.Hour;
                Minute = Analysis1.Minute;
                Console.WriteLine("Time " + Hour.ToString("00") + ":" + Minute.ToString("00"));
                data = FactorSafety.ToString("0.00");
                Console.WriteLine("Factory Of Safety " +data);
                Thread.Sleep(10000);
            }
            System.IO.File.WriteAllText("data.txt","<var1>"+ data +"</var1>");
            Console.Write("press eny key to exit.");
            System.Console.ReadKey();
        }

    }
}

