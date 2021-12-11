using System;
using System.IO;

namespace _03
{
    class Program
    {

        static void Main(string[] args)
        {
            var engineParams = new EngineParams();
            var lines = File.ReadLines("input.txt");
            int entries = 0;
            foreach (var line in lines)
            {
                engineParams.Register(line);
                entries++;
            }
            uint gamma = engineParams.getGamma();
            uint epsilon = engineParams.getEpsilon();
            System.Console.WriteLine(string.Format("Gamma: {0} Epsilon: {1}", gamma, epsilon));
            System.Console.WriteLine(string.Format("Gamma*Epsilon: {0}", gamma*epsilon));

            uint oxygen = engineParams.OxygenLevel();
            uint carbonOxide = engineParams.CarbonOxideLevel();
            System.Console.WriteLine(string.Format("Oxygen: {0} Carbon-oxide: {1}", oxygen, carbonOxide));
            System.Console.WriteLine(string.Format("Oxygen*Carbon-oxide: {0}", oxygen*carbonOxide));
        }
    }
}
