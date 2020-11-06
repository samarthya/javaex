class ArmstrongNumbers {

    boolean isArmstrongNumber(int numberToCheck) {
        String str = Integer.toString(numberToCheck);
        int power = str.length();
        double d = str.chars().map(Character::getNumericValue).mapToDouble( x -> Math.pow(x, power )).sum();
        return d == numberToCheck;
    }

}
