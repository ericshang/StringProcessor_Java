import java.util.ArrayList;

/**
 *
 * @author Shang
 */
public class IntergenCodingExercise {
    
    //Please change this to the text
    public final static String  text = "Intergen is New Zealand's most experienced provider of Microsoft based business solutions. "
            + "We focus on delivering business value in our solutions and work closely with Microsoft to ensure we have the best possible understanding of their technologies and directions." 
            + "Intergen is a Microsoft Gold Certified Partner with this status recognising us as an “elite business partner” "
            + "for implementing solutions based on our capabilities and experience with Microsoft products.";
    
    //!important: Do not change this one.
    public final static String pattern = "[(\\.)+?]|[!?]|[(\\?)?]";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        TextProcessor processor = new TextProcessor(text);
        ArrayList<Sentence> allSentences = processor.getSentenceList();
        ArrayList<Word> allWords = processor.getWordList();
        allSentences.sort(null);
        
        //task 1
        System.out.println("Number of sentences:" + allSentences.size()); 
        
        //task 2
        System.out.println("Number of words:" + allWords.size()); 

        //task 3
        System.out.print("Sentence with most words:");
        ArrayList<Sentence> longestSentences = processor.getLongestSentences();
        for(int i=0; i < longestSentences.size(); i++){
            Sentence s = longestSentences.get(i);
            String tempStr = s.getOriginalText();
            if(s.getOriginalText().length()>30){
                tempStr = tempStr.substring(0,30)+"...";
            }
            String note ="";
            if(longestSentences.size()>1 && i< longestSentences.size()-1){
                note = ",";
            }
            System.out.print(" \""+tempStr+"\""+note);
        }
        System.out.println();
        
        //task 4
        System.out.print("Most frequently used word(s):");
        for(Word word: processor.getMostUsedWord()){
            System.out.println(" '"+word+"'");
        }
        
        //task 5
        System.out.print("3rd longest word(s):");
        ArrayList<Word> The3rdLongestWordList = processor.getThe3rdLongestWords();
        int the3rdWordsLength = The3rdLongestWordList.get(0).getText().length();
        
        for(int i=0; i < The3rdLongestWordList.size();i++ ){
            String note = "";
            if(i<The3rdLongestWordList.size()-1){
                note=",";
            }
            System.out.print("'"+The3rdLongestWordList.get(i)+"'"+note);
        }
        String charactersStr =the3rdWordsLength>1 ?" characters":" character";
        String isOrAre = The3rdLongestWordList.size()>1 ?
                            " are "+the3rdWordsLength:
                            " is "+ the3rdWordsLength;
        System.out.println( isOrAre + charactersStr + " long. ");
        
        
    }
    
}
