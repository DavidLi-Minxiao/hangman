//David (Min Xiao) Li, 260564820
public class Hangman{  
  private Letter[] letters;
  private char[] guesses = new char[26];
  private boolean gameOver = false;
  private int maxNumGuesses = 0;
  private int numGuessesMade = 0;
  private boolean victory = false;
  
  //constructor overlaoad A with default amount of guesses
  public Hangman (String guessThisWord){
    String upperCase = guessThisWord.toUpperCase();
    this.letters = new Letter[upperCase.length()];
    for (int i = 0; i < upperCase.length(); i++){
      this.letters[i] = new Letter(upperCase.charAt(i));
    }
    maxNumGuesses = 8;
  }
  
  //constructor overload B with custom amount of guesses
  public Hangman (String guessThisWord, int maxGuesses){
    this.letters = new Letter[guessThisWord.length()];
    String upperCase = guessThisWord.toUpperCase();
    for (int i = 0; i < upperCase.length(); i++){
      this.letters[i] = new Letter(upperCase.charAt(i));
    }
    this.maxNumGuesses = maxGuesses;
  }
  
  //guesses
  private boolean guess (char currentGuess){
    boolean isFound = false;
    
    //checks if character is valid
    if (Character.isLetter(currentGuess) != true){
      System.out.println ("The character entered is invalid. Please try again!");
      return false;
    }
    
    //converts to upper case
    currentGuess = Character.toUpperCase(currentGuess);
    
    //checks if the guess has been made
    for (int i = 0; i < numGuessesMade; i++){
      if (currentGuess == guesses[i]){
        System.out.println ("This letter has been guessed already! Please try again!");
        return false;
      }
    }
    
    //runs through the mystery words array to check if the guess has been entered
    for (int i = 0; i < letters.length; i++){
      if (currentGuess == letters[i].getValue()){
        if (letters[i].isGuessed() == true){
          System.out.println ("This letter has been guessed already! Please try again!");
          return false;
        }
        else{
          letters[i].reveal();
        }
        isFound = true;
      }
    }
      
    //runs through the mystery word to check if the letters are there
    if (isFound == false){
      System.out.println ("The letter you have guessed is not in the word/phrase!");
      guesses [numGuessesMade] = currentGuess;
      numGuessesMade = numGuessesMade + 1;
    }
    else if (isFound == true){
      System.out.println ("The letter " + currentGuess + " was in the word!");
    }
    return true;
  }
    
  //displays the board
  private void displayBoard (){
    System.out.print ("The word so far: ");
    for (int i = 0; i < letters.length; i++){
      if (letters[i].isGuessed() == true){
        System.out.print (letters[i].getValue() + " ");
      }
      else if (letters[i].getValue() == ' '){
        System.out.print ("  ");
      }
      else{
        System.out.print ("_ ");
      }
    }
    System.out.println ("");
    
    //prints the letters that haven't been guessed only if they're present
    System.out.print ("The following incorrect letters have been guessed: ");
    for (int i = 0; i < numGuessesMade; i++){
      System.out.print (guesses[i] + " ");
    }
    System.out.println ("");
    System.out.println ("There are " + (maxNumGuesses - numGuessesMade) + " incorrect guesses remaining.");
    System.out.println ("");
  }
      
  //main game
  public void playGame (){
    
    //main loop for repeated checks
    while (gameOver == false){
      displayBoard();
      
      boolean validity = false;
      
      //takes input and makes sure input is right
      while (validity == false){
        System.out.print ("Please enter your guess: ");
        java.util.Scanner input = new java.util.Scanner(System.in);
        char myGuess = input.next().charAt(0);
        validity = guess(myGuess);
        System.out.println ("");
      }
      
      //quits loop if the victory or defeat conditions are met
      gameOver = true;
      victory = true;
      for (int i = 0; i < letters.length; i++){
        if (letters[i].isGuessed() == false){
          gameOver = false;
          victory = false;
          break;
        }
      }
      if (maxNumGuesses == numGuessesMade){
        gameOver = true;
      }
    }
    
    //declares defeat or victory and displays the word
    if (gameOver == true && victory == true){
      System.out.println ("Congratulations, you've won Hangman!");
    }
    else{
      System.out.println ("Sorry, you lose! Try again next time!");
    }
    System.out.print ("The word was: ");
    for (int i = 0; i < letters.length; i++){
        System.out.print (letters[i].getValue() + " ");
      }
  }
  
  //main class to test  
  public static void main (String[] args){
    System.out.println ("Welcome to Hangman!");
    System.out.println ("The word(s) have been generated for you.");
    Hangman h = new Hangman ("david", 3);
    h.playGame();
  }
  
}
