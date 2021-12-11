using System.Linq;
using System.Collections.Generic;

namespace se.nyquist {
    class BingoNumber {
        private int number;
        private List<BingoRow> rows;

        public BingoNumber(int number) {
            this.number = number;
            this.rows = new List<BingoRow>();
            IsMarked = false;
        }

        public bool checkDraw(int draw) {
            bool isMatch = (draw == number);
            if (isMatch) {
                IsMarked = true;
                return rows.Any(row => row.inc());
            }
            return false;
        }

        public void AddRow(BingoRow row) {
            rows.Add(row);
        }

        public bool IsMarked {
            get; set;
        }

        public int Value {
            get {
                return number;
            }
        }

        public override string ToString()
        {
            return string.Format("{0,2}{1,3}", number, IsMarked ? "(X)" : "");
        }
    }
}