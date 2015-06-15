/**
 *  an entity class represents a word (no different as a string, but easy to understand)
 * @author Shang
 */
public class Word implements Comparable<Word>{
    String text;
    int numOfChar;
    
    //constructor
    public Word(String word){
        setText(word);
        setNumOfChar(word);
    }

    //getters
    public String getText() {
        return text;
    }

    public int getNumOfChar() {
        return numOfChar;
    }

    //setters
    public void setText(String text) {
        this.text = text;
    }

    public void setNumOfChar(String word) {
        if(word.length()>0);
            this.numOfChar = word.length();
    }

    @Override
    public int compareTo(Word t) {
        return t.getNumOfChar()-this.getNumOfChar();
    }
    public String toString(){
        return text;
    }
}
