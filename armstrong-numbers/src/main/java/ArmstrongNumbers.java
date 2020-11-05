class ArmstrongNumbers {
    /**
     * 1 2 3
     * | | | - 3
     * | | - 20 +
     * |- 100 +
     * @param numberToCheck
     * @return
     */
    boolean isArmstrongNumber(int numberToCheck) {
//        if (numberToCheck < 10) {
//            return true;
//        }

        String str = Integer.toString(numberToCheck);
        int power = str.length();

        double d = str.chars().map(Character::getNumericValue).mapToDouble( x -> Math.pow(x, power )).sum();
        System.out.println( " NUmber: " + d + " == " + numberToCheck);
        return d == numberToCheck;
    }

}
