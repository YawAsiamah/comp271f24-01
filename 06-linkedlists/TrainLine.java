import java.util.ArrayList;

/**
 * Represents a line of train stations with methods to add stations and display them.
 */
public class TrainLine {
    // Properties
    private String name;  // Train line's name
    private TrainStation head;  // First station
    private TrainStation tail;  // Last station
    private int numberOfStations;  // Count of stations

    // Constants
    private static final int MAX_LINE_LENGTH = 80;
    private static final int MAX_VERTICAL_SEGMENTS = 5;
    private static final String ARROW = " --> ";

    // Constructor
    public TrainLine(String name) {
        this.name = name;
        this.head = null;
        this.tail = null;
        this.numberOfStations = 0;
    }

    // Adds a station to the end of the train line
    public void add(String stationName) {
        TrainStation newStation = new TrainStation(stationName);
        if (head == null) {
            head = newStation;
        } else {
            tail.setNext(newStation);
        }
        tail = newStation;
        numberOfStations++;
    }

    // Generates a string of the train line in a snake-like pattern
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("         1         2         3         4         5         6         7         8\n");
        sb.append("12345678901234567890123456789012345678901234567890123456789012345678901234567890\n\n");

        TrainStation current = head;
        boolean horizontal = true;
        boolean downward = true;

        while (current != null) {
            if (horizontal) {
                current = printHorizontal(current, sb, downward);
                horizontal = false;
            } else {
                current = printVertical(current, sb, downward);
                downward = !downward;
                horizontal = true;
            }
        }

        sb.append("                                 null\n");
        return sb.toString();
    }

    // Prints horizontal segment and returns next station
    private TrainStation printHorizontal(TrainStation start, StringBuilder sb, boolean leftToRight) {
        StringBuilder line = new StringBuilder();
        int charCount = 0;

        TrainStation current = start;
        while (current != null && charCount < MAX_LINE_LENGTH) {
            String stationName = current.getName();
            int length = stationName.length() + (current.getNext() != null ? ARROW.length() : 0);
            if (charCount + length > MAX_LINE_LENGTH) break;

            line.append(leftToRight ? stationName + ARROW : ARROW + stationName);
            charCount += length;
            current = current.getNext();
        }

        sb.append(leftToRight ? line + " --+\n" : " +--" + line + "\n");
        return current;
    }

    // Prints vertical segment and returns next station
    private TrainStation printVertical(TrainStation start, StringBuilder sb, boolean downward) {
        TrainStation current = start;
        for (int i = 0; i < MAX_VERTICAL_SEGMENTS && current != null; i++) {
            sb.append(downward ? "      +-- " : "                                 |\n      +-- ")
              .append(current.getName()).append("\n");
            current = current.getNext();
        }
        return current;
    }

    public static void main(String[] args) {
        TrainLine redLineSB = new TrainLine("Red Line SB");
        String[] stationNames = {
            "Howard", "Jarvis", "Morse", "Loyola", "Granville", "Thorndale",
            "Bryn Mawr", "Argyle", "Wilson", "Sheridan", "Addison", 
            "Belmont", "Fullerton", "North/Clyburn", "Clark/Division", 
            "Chicago", "Roosevelt", "Harrison", "Jackson", "Monroe", 
            "Cermak-Chinatown", "Sox-35th", "47th", "Garfield", "63rd", 
            "69th", "79th", "87th", "95th/Dan Ryan"
        };

        for (String station : stationNames) {
            redLineSB.add(station);
        }

        System.out.println(redLineSB);
    }
}

/**
 * Represents a train station in the train line.
 */
class TrainStation {
    private String name;  // Station name
    private TrainStation next;  // Next station

    public TrainStation(String name) {
        this.name = name;
        this.next = null;
    }

    public String getName() {
        return this.name;
    }

    public TrainStation getNext() {
        return this.next;
    }

    public void setNext(TrainStation next) {
        this.next = next;
    }
}

/*
 * Pseudo Code:
 * 
 * 1. Create TrainLine with a name.
 *    - Initialize name, head, tail, and numberOfStations.
 * 
 * 2. Add stations using `add()`:
 *    - Create a new TrainStation.
 *    - If head is null, set new station as head.
 *    - Otherwise, link the new station to the current tail.
 *    - Update tail to the new station.
 *    - Increase the count of stations.
 * 
 * 3. Generate train line display in `toString()`:
 *    - Print headers.
 *    - Loop through stations:
 *      - If horizontal, call `printHorizontal()`.
 *      - If vertical, call `printVertical()`.
 *      - Toggle direction after each segment.
 *    - End with "null".
 * 
 * 4. PrintHorizontal():
 *    - Print stations in a line until reaching max length.
 *    - Return next station.
 * 
 * 5. PrintVertical():
 *    - Print stations vertically up or down.
 *    - Return next station.
 * 
 * 6. Main:
 *    - Create a TrainLine.
 *    - Add stations.
 *    - Print the train line in snake-like format.
 */
