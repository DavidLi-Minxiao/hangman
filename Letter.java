//David (Min Xiao) Li, 260564820
public class Letter{
  private char value;
  private boolean isGuessed;
  
  //constructors
  public Letter (char value){
    this.value = value;
    this.isGuessed = false;
  }
  
  //methods
  public char getValue (){
    return this.value;
  }
  
  public boolean isGuessed (){
    return this.isGuessed;
  }
  
  public void reveal (){
    this.isGuessed = true;
  }
}
    
                                    