using System;
using System.IO;
using se.nyquist;
using System.Linq;
using System.Collections.Generic;
using System.Text.RegularExpressions; 

namespace _04
{
    class Program
    {
        List<BingoBoard> boards;
        int[] numbers;

        private Program() {
            boards = new List<BingoBoard>();
            var input = File.OpenText("input.txt");
            numbers = input.ReadLine().Split(",").Select(s => Convert.ToInt32(s)).ToArray();
            while(! input.EndOfStream)
            {
                int counter = 0;
                input.ReadLine();
                var board = new BingoBoard();
                while(counter < 5) {
                    var rows = input.ReadLine().Trim();
                    var intRows = Regex.Split(rows,@"\s+").Select(s => Convert.ToInt32(s)).ToArray();
                    board.AddRow(counter, intRows);
                    counter++;
                }
                boards.Add(board);
            }
        }

        static void Main(string[] args) {
        
            Program app = new Program();
            // app.FindWinner();05
            app.FindLoser();
        }

        private void FindWinner() {
            for(int i = 0; i <  numbers.Length; i++) {
                var winners = boards.Where(b => b.Draw(numbers[i])).ToList();
                foreach (var board in winners) {
                    System.Console.WriteLine(board);
                    int sumUnmarkedNumbers = board.GetUnmarkedNumbers().Select(n => n.Value).Sum();
                    int result = numbers[i]*sumUnmarkedNumbers;
                    System.Console.WriteLine("Result: " + result);
                }
            }
        }

        private void FindLoser() {
            List<BingoBoard> current = boards;
            List<BingoBoard> remaining = new List<BingoBoard>();
            for(int i = 0; i <  numbers.Length; i++) {
                current.ForEach(b => {
                    if (! b.Draw(numbers[i])) {
                        remaining.Add(b);                                            
                    }
                });
                if (remaining.Count() == 0) {
                    current.ForEach(board => {
                        System.Console.WriteLine(board);
                        int sumUnmarkedNumbers = board.GetUnmarkedNumbers().Select(n => n.Value).Sum();
                        int result = numbers[i]*sumUnmarkedNumbers;
                        System.Console.WriteLine("Result: " + result);
                    });
                    current = remaining;
                    break;
                } else {
                    current = remaining;
                    remaining = new List<BingoBoard>();
                }
            }
        }
    }
}
