using System.Collections.Generic;
using System.Linq;

namespace se.nyquist {
    class BingoBoard {
        private BingoNumber[,] numbers;     
        private BingoRow[] verticalRows;        
        private BingoRow[] horizontalRows;
        public BingoBoard() {
            this.numbers = new BingoNumber[5,5];
            this.verticalRows = new BingoRow[5] { new BingoRow(), new BingoRow(), new BingoRow(), new BingoRow(), new BingoRow()};
            this.horizontalRows = new BingoRow[5] { new BingoRow(), new BingoRow(), new BingoRow(), new BingoRow(), new BingoRow()};

        }

        public void AddRow(int row, int[] numbers) {
            for(int i = 0; i < 5; i++) {
                var number = new BingoNumber(numbers[i]);
                number.AddRow(horizontalRows[row]);
                number.AddRow(verticalRows[i]);
                this.numbers[row,i] = number;
            }
        }

        public bool Draw(int number) {
            for(int i = 0; i < 5; i++) {
                for(int j = 0; j < 5; j++) {
                    if (this.numbers[i,j].checkDraw(number)) {
                        return true;
                    }
                }
            }
            return false;
        }

        public List<BingoNumber> GetUnmarkedNumbers() {
            return numbers.Cast<BingoNumber>().Where(n => ! n.IsMarked).ToList();          
        }

        public override string ToString()
        {
            string output = "";
            for(int i = 0; i < 5; i++) {
                for(int j = 0; j < 5; j++) {
                    output += string.Format("{0,2:D2} ", numbers[i,j]);
                }
                output += System.Environment.NewLine;
            }
            return output;
        }
    }
}