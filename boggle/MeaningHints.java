package boggle;

/**
 * Class to implement the Meaning Hint which will give the client the meaning of a word from board as a Hint.
 */
public class MeaningHints extends Hints{
    /** MeaningHints Constructor, inheriting the Hints constructor
     * ----------------------
     * @param dict  The Dictionary of the Game
     *
     */
    public MeaningHints(Dictionary dict){
        super(dict);
    }
    /**
     * updates the score of the player asking for MeaningHints
     * by reducing a small amount from its score.(20 percent)
     *
     * @return Score decreased by taking this type of hint.
     */
    public double updateScore(){
        return -0.20;
    }
    /**
     * Provides the meaning of the word of the grid using the adapter dictionary.
     * @param word random word in the grid.
     * @return the meaning of the word
     *
     */
    public String getWordMeaning(String word){
      String res= this.dict.get_word_meanings().get(word);
        System.out.println("Your Meaning hint is:"+ res);
        return res;
    }
}
