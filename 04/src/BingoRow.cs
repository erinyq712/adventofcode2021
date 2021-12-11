namespace se.nyquist {
    class BingoRow {
        int counter = 0;

        public bool inc() {
            counter++;
            return bingo();
        }

        public bool bingo() {
            return counter == 5;
        }
    }
}