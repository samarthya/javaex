class Darts {

    final double OUTER = 10.00;
    final double MIDDLE = 5.00;
    final double INNER = 1.00;

    double x;
    double y;

    // Darts
    Darts(double x, double y) {
        this.x = x;
        this.y = y;
    }

    int score() {
        double value = (Math.sqrt(Math.abs(Math.pow(x, 2)) + Math.abs(Math.pow(y, 2))));

        if (value <= OUTER && value > MIDDLE) {
            return 1;
        } else if (value <= MIDDLE && value > INNER) {
            return 5;
        } else if (value < MIDDLE) {
            return 10;
        }

        return 0;
    }

}
