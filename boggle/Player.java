
package boggle;

import java.util.*;

public abstract class Player {
    /**
     * The board on which boggle game is played.
     */
    private Grid board;

    /**
     * set of words the player finds in a given round.
     */
    private Set<String> playerWords = new HashSet<String>();
    /**
     * scanner used to interact with the user via console.
     */
    public Scanner scanner;

    /**
     * the player's score for the current round.
     */
    private int score;

    /**
     * All the possible combination of words present on the board.
     */
    public Map<String, ArrayList<Position>> allWords;
    /**
     * Number of hints the player has taken.
     */
    private int hintcounter;
    /**
     * Dictionary to generate hint
     */
    private Hints typeHint;

    public Player(Map<String, ArrayList<Position>> allWords) {
        this.allWords = allWords;
        this.hintcounter = 0;
    }

    /*
     * This method helps to implement the rounds played by the Player.
     * This method prints out the board, and prompts the player for a word.
     * If the addWords then try to add the word to the list of words that player guessed.
     * If the user inputs nothing, then the function stops.
     *
     */
    abstract void makeMove();

    /*
     * This method acts as helper method to the makeMove().
     * The addWords method takes the word input by the user and checks
     *  - if it is a valid word, and
     *  - if it has not been already guessed by the user
     * Once,the condition is satisfied, then the word is added to the list
     * else an appropriate message is displayed.
     *
     * @param String representation of the word that the user has guessed
     *
     */
    abstract void addWord(String word);

    /*
     * This method helps to set the score of the player,
     * whenever prompted by the GameStats
     *
     * @param An integer representation of the score.
     *
     */
    public void setScore(int score) {
        this.score = score;
    }

    /*
     * This getter method returns the list of all
     * the words that the player has found.
     *
     * @return list of the words found by this player
     *
     */
    public Set<String> getPlayerWords() {
        return this.playerWords;
    }


    /*
     * This getter method returns the score of the player
     *
     * @return an integer representation of the score
     *
     */
    public int getScore() {
        return this.score;
    }

    /*
     * This method re-sets the score of the player.
     *
     */
    public void resetScore() {
        this.score = 0;
    }
    /*
     * This method generates the specific type of hint asked by the player.
     *@return The hint for the player.
     */
    public String askHints() {
        if (this.hintcounter > 5) {
            System.out.println("Sorry you are out of hints, You cannot take anymore in this round");
            return null;
        }
        Scanner sc = new Scanner(System.in);
        String hint;
        System.out.println("What type of hint do you want? Press \"LH\" for Letter Hints and \"MH\" for Meaning Hint");
        hint = sc.nextLine().toLowerCase();
        ArrayList<String> words = new ArrayList<>(this.allWords.keySet());
        TreeSet<String> setofWords = new TreeSet<>(words);
        Random r = new Random();
        if (hint.equals("lh")) {
            this.hintcounter += 1;
            this.score -= 1;
            String randomWordFromGrid;
            do {
                randomWordFromGrid = words.get(r.nextInt(words.size()));
            } while (playerWords.contains(randomWordFromGrid));
            LetterHints letterHint = new LetterHints(new Dictionary(setofWords));
            return letterHint.getHint(randomWordFromGrid);
        } else if (hint.equals("mh")) {
            this.hintcounter += 1;
            this.score -= 1;
            MeaningHints meaningHint = new MeaningHints(new Dictionary(setofWords));
            String randomWordFromGrid;
            while (true) {
                randomWordFromGrid = words.get(r.nextInt(words.size()));
                if (!playerWords.contains(randomWordFromGrid)) {
                    if (meaningHint.getHint(randomWordFromGrid) != null) {
                        break;
                    }
                }
            }
            return meaningHint.getHint(randomWordFromGrid);
        }
        System.out.println("Invalid Input");
        return null;
    }
}


