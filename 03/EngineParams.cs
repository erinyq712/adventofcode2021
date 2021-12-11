using System.Collections.Generic;
using System.Linq;
using System;

namespace _03
{
    class EngineParams
    {
        const int LENGTH = 12;
        private int[] bitCounter;
        private List<char[]> values;
        private int position = 0;

        public EngineParams() {
            this.bitCounter = new int[12];
            this.values = new List<char[]>();
            this.position = 0;
        }

        public EngineParams(int position, List<char[]> values) {
            this.bitCounter = new int[12];
            this.position = position;
            this.values = values;
            this.values.ForEach(v => UpdateBitCounter(v));
        }

        private void UpdateBitCounter(char[] bits) {
            for (int i = 0; i < bits.Length; i++)
            {
                if (bits[i] == '1')
                {
                    bitCounter[i]++;
                }
            }
        }

        public void Register(string bitstring)
        {
            var bits = bitstring.ToCharArray();
            UpdateBitCounter(bits);
            values.Add(bits);
        }

        public uint getGamma()
        {
            String gammaStr = "";
            for (int i = 0; i < LENGTH; i++)
            {
                if (GammaFor(i))
                {
                    gammaStr += "1";
                }
                else
                {
                    gammaStr += "0";
                }
            }
            return Convert.ToUInt32(gammaStr, 2);
        }

        public uint getEpsilon()
        {
            uint gamma = getGamma();
            return ~gamma & ~(UInt32.MaxValue << 12);
        }

        public uint Level(Func<char[],int,bool> criteriaTrue, Func<char[],int,bool> criteriaFalse)
        {
            while (position < LENGTH)
            {
                bool gamma = GammaFor(position);
                List<char[]> matches;
                if (gamma)
                {
                    matches = values.ToList().Where(arr => criteriaTrue(arr,position)).ToList();
                }
                else
                {
                    matches = values.ToList().Where(arr => criteriaFalse(arr,position)).ToList();
                }
                if (matches.Count() == 1)
                {
                    var levelStr = new string(matches[0]);
                    return Convert.ToUInt32(levelStr, 2);
                }
                return new EngineParams(position+1, matches).Level(criteriaTrue, criteriaFalse);
            }
            return 0;
        }

        public uint OxygenLevel()
        {
            return Level((arr, position) => arr[position] == '1', (arr, position) => arr[position] == '0');
        }

        public uint CarbonOxideLevel()
        {
            return Level((arr, position) => arr[position] == '0', (arr, position) => arr[position] == '1');
        }

        public bool GammaFor(int position)
        {
            var entries = values.Count;
            if (entries - bitCounter[position] <= bitCounter[position])
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        public bool EpsilonFor(int position)
        {
            return !GammaFor(position);
        }
    }
}