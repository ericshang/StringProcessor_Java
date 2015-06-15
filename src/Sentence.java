import java.util.ArrayList;

/**
 *  an entity class represent a sentence
 * @author Shang
 */
public class Sentence implements Comparable<Sentence> {
    private String originalText;
    private ArrayList<Word> words;//Word is of no diffrence from a String obj.

    
    //constructors
    public Sentence(String originalText){
        words = new ArrayList<Word>();
        setOriginalText(originalText);//used to print out
        setWords(originalText);
    }
    
    //getters
    public ArrayList<Word> getWords() {
        return words;
    }
    public String getOriginalText() {
        return originalText;
    }
    
    //setters
    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

    public void setWords(String text) {
        //eg text ="this is not-a-word sentence."
        String cleanedText =this.cleanText(text);
        String[] wordArray = cleanedText.split(" ");//use space to split text into array
        
        for(String str: wordArray){//add word into ArrayList<Word> as an word object
            Word tempWord = new Word(str);
            words.add(tempWord);
        }
    }

    private String cleanText(String str){//delete words and only words, '-',space remains
        String tempText = "";
        boolean isSpaceBeforeChar = false;//used to clean extra space
        
        for(int i =0; i<str.length(); i++){
            char c = str.charAt(i);
            //System.out.println(i+":"+c);
            if(i==0 && c-' '==0){//starting with a space, continue
                isSpaceBeforeChar = (c-' '==0);
                continue;
            }
            if(((c-'a'>=0 && c-'z'<=0) || (c-'A'>=0 && c-'Z'<=0) || c-'-'==0 || c-' '==0)){
                if(isSpaceBeforeChar == true && c-' '==0){
                    isSpaceBeforeChar = (c-' '==0);
                    continue;
                }
                tempText += c;
            }
            isSpaceBeforeChar = (c-' '==0);
        }
        return tempText;
    }
    
    @Override
    public int compareTo(Sentence t) {//in decendent order
        return  t.getWords().size()- this.getWords().size();
    }
    
    @Override
    public String toString(){
        return this.getOriginalText();
    }
    
}
