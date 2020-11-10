import java.util.Random;

class DnDCharacter {

    private int STREAM_SIZE = 4;
    private int MAX_VALUE = 7;



    /**
     * This character has, among other things, six abilities;
     * strength,
     * dexterity,
     * constitution,
     * intelligence,
     * wisdom and
     * charisma.
     */

    private Random random;

    int strength;
    int dexterity;
    int constitution;
    int intelligence;
    int wisdom;
    int charisma;
    int hitpoints;
    int constitutionModifier;

    /**
     * Refer: https://docs.oracle.com/javase/8/docs/api/index.html?java/util/Random.html
     * @return
     */
    int sumOfTries() {
        return random.ints(STREAM_SIZE, 1, MAX_VALUE).sorted().skip(1).sum();
    }

    public DnDCharacter() {
        this.random = new Random();
        this.charisma = this.sumOfTries();
        this.wisdom = this.sumOfTries();
        this.intelligence = this.sumOfTries();
        this.constitution = this.sumOfTries();
        this.dexterity = this.sumOfTries();
        this.strength = this.sumOfTries();
    }

    int ability() {
        return sumOfTries();
    }

    //Modifier
    int modifier(int input) {
        return Math.floorDiv(input-10, 2);
    }

    int getStrength() {
        return strength;
    }

    int getDexterity() {
        return dexterity;
    }

    // You find your character's constitution modifier by subtracting 10 from your character's constitution, divide by 2 and round down.
    int getConstitution() {
        return this.constitution;
    }

    int getIntelligence() {
        return intelligence;
    }

    int getWisdom() {
        return wisdom;
    }

    int getCharisma() {
        return charisma;
    }

    int getHitpoints() {
        this.hitpoints = 10 + Math.floorDiv(this.constitution - 10,2);
        return hitpoints;
    }

}
