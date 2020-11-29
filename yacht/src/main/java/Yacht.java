class Yacht {

    private int count;

    Yacht(int[] dice, YachtCategory yachtCategory) {
        count = yachtCategory.sum(dice);
    }

    int score() {
        return this.count;
    }

}
