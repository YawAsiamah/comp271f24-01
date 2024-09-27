import java.util.Random;

public class Person implements Comparable<Person>, SillyActions {
    // Constants for default values and magic numbers
    private static final String default_last_name = "LNU"; // Default last name
    private static final String default_first_name = "FNU"; // Default first name
    private static final int default_year_born = 1800; // Default year of birth
    private static final int skip_number = 7; // Number to skip when counting weirdly
    private static final int lottery_number_limit = 49; // Max number for lottery
    private static final int lottery_numbers_count = 6; // Number of lottery numbers
    private static final int count_limit = 10; // Upper limit for counting weirdly
    private static final int poem_word_count = 4; // Number of words in the poem
    private static final Random rand = new Random(); // Reuse one Random object

    // Private fields to store person's info
    private String firstName;
    private String lastName;
    private int yearBorn;

    // Constructor to create a Person with the given info using if-else blocks
    public Person(String firstName, String lastName, int yearBorn) {
        if (firstName != null) {
            this.firstName = firstName;
        } else {
            this.firstName = default_first_name;
        }

        if (lastName != null) {
            this.lastName = lastName;
        } else {
            this.lastName = default_last_name;
        }

        if (yearBorn > 0) {
            this.yearBorn = yearBorn;
        } else {
            this.yearBorn = default_year_born;
        }
    }

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName != null) {
            this.firstName = firstName;
        } else {
            this.firstName = default_first_name;
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName != null) {
            this.lastName = lastName;
        } else {
            this.lastName = default_last_name;
        }
    }

    public int getYearBorn() {
        return yearBorn;
    }

    // Set a valid year (must be <= 2024)
    public void setYearBorn(int yearBorn) {
        if (yearBorn > 0 && yearBorn <= 2024) {
            this.yearBorn = yearBorn;
        } else {
            System.out.println("Invalid year of birth.");
        }
    }

    // Compare people by their birth year (younger first)
    @Override
    public int compareTo(Person other) {
        return Integer.compare(other.getYearBorn(), this.yearBorn);
    }

    // Pick and print a random animal sound
    @Override
    public void makeRandomSound() {
        String[] sounds = {"Bark", "Meow", "Quack", "Buzz", "Roar"};
        int randomIndex = rand.nextInt(sounds.length);
        System.out.println("Random Sound: " + sounds[randomIndex]);
    }

    // Perform a simple dance with basic steps
    @Override
    public void performSillyDance() {
        String[] steps = {"left", "right", "forward", "backward"};
        System.out.println("Silly Dance: Step " + steps[0] + ", Step " + steps[1] + ", Spin, Step " + steps[2] + ", Jump!");
    }

    // Recite the alphabet backwards, skipping one random letter
    @Override
    public String reciteAlphabetBackwards() {
        String alphabet = "ZYXWVUTSRQPONMLKJIHGFEDCBA";
        int skipIndex = rand.nextInt(alphabet.length());
        return alphabet.substring(0, skipIndex) + alphabet.substring(skipIndex + 1);
    }

    // Count from 1 to 10, skipping 7
    @Override
    public void countToTenWeirdly() {
        for (int i = 1; i <= count_limit; i++) {
            if (i != skip_number) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    // Create and return a random whimsical poem about a given topic
    @Override
    public String createWhimsicalPoem(String topic) {
        StringBuilder poem = new StringBuilder("A whimsical poem about " + topic + ":\n");
        for (int i = 0; i < poem_word_count; i++) {
            int randomIndex = rand.nextInt(PoemWords.words.length);
            poem.append(PoemWords.words[randomIndex]).append(" ");
        }
        return poem.toString();
    }

    // Simulate winning the lottery by printing 6 random numbers
    @Override
    public void winStateLottery() {
        System.out.println("Your lottery numbers are:");
        for (int i = 0; i < lottery_numbers_count; i++) {
            System.out.print(rand.nextInt(lottery_number_limit) + 1 + " ");
        }
        System.out.println();
    }

    // Return a simple string description of the Person
    @Override
    public String toString() {
        return String.format("Person[firstName=%s, lastName=%s, yearBorn=%d]", firstName, lastName, yearBorn);
    }

    // Main method to test the Person class
    public static void main(String[] args) {
        Person p = new Person("John", "Doe", 1990);
        p.makeRandomSound();
        p.performSillyDance();
        System.out.println(p.reciteAlphabetBackwards());
        p.countToTenWeirdly();
        System.out.println(p.createWhimsicalPoem("sun"));
        p.winStateLottery();
    }
}

