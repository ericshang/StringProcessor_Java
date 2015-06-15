import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Used to process raw text, contains critical information for the task
 * @author Shang
 */
public class TextProcessor {
    private ArrayList<Sentence> sentenceList;      //list of sentences, for task 1
    private ArrayList<Word> wordList;              //list of words, for task 2
    private ArrayList<Sentence> longestSentences;  //task 3
    private ArrayList<Word> mostUsedWord;          //task 4
    private ArrayList<Word> the3rdLongestWords;    //task 5
    
    private String paragraph;                      //the raw text
    public final static String pattern = "[(\\.)+?]|[!?]|[(\\?)?]";// regular experession
    
    //constructor
    public TextProcessor(String paragraph){
        sentenceList = new ArrayList<Sentence>();
        wordList = new ArrayList<Word>();
        mostUsedWord = new ArrayList<Word>();
        the3rdLongestWords =new ArrayList<Word>();
        longestSentences = new ArrayList<Sentence>();
        processRawTextIntoSentence(paragraph); //!important: convert the paragraph into the sentence list and word list
        wordList.sort(null);
        setLongestSentences();
        setMostUsedWord();
        setThe3rdLongestWords();
        
    }
    //getters
    public ArrayList<Sentence> getSentenceList() {
        return sentenceList;
    }

    public ArrayList<Word> getWordList() {
        return wordList;
    }

    public ArrayList<Sentence> getLongestSentences() {
        return longestSentences;
    }

    public ArrayList<Word> getMostUsedWord() {
        return mostUsedWord;
    }

    public ArrayList<Word> getThe3rdLongestWords() {
        return the3rdLongestWords;
    }

    public String getParagraph() {
        return paragraph;
    }
    //setters
    public void setSentenceList(ArrayList<Sentence> sentenceList) {
        this.sentenceList = sentenceList;
    }

    public void setWordList(ArrayList<Word> wordList) {
        this.wordList = wordList;
    }

    public void setLongestSentences() {
        sentenceList.sort(null);//sorted
        int length = sentenceList.get(0).getWords().size();
        for(int i=0; i < sentenceList.size();i++){
            Sentence tempSentence = sentenceList.get(i);
            if(tempSentence.getWords().size()== length){
                longestSentences.add(tempSentence);
            }
        }
    }

    public void setMostUsedWord() {
        HashMap map = new HashMap ();//the word:count pairs
 
        for (Word temp : wordList) {
            String tempStr = temp.toString();
            Integer count = (Integer)map.get(tempStr);
            map.put(tempStr, (count == null) ? 1 : count + 1);
        }
        //sort map by value
        Map<String, Integer> sorted_map = sortMapByValue(map);
        //the max value is: 
        Map.Entry entry = (Map.Entry)sorted_map.entrySet().iterator().next();
        int maxValue = (int)entry.getValue();//the mas value of the words
        ArrayList<Word> tempList = new ArrayList<Word>();
        for (Iterator it = sorted_map.entrySet().iterator(); it.hasNext();) {
            Map.Entry entrys = (Map.Entry)it.next();
            if((int)entrys.getValue()==maxValue ){
                Word tempWord = new Word((String)entrys.getKey());
                tempList.add(tempWord);
            }
        }
        mostUsedWord = tempList;
    }

    public void setThe3rdLongestWords(){
        HashMap map = new HashMap ();//the word:length pairs
        for (Word temp : wordList) {
            String tempStr = temp.toString();
            Integer length = tempStr.length();
            map.put(tempStr, length);
        }
        
        //sort map by value
        Map<String, Integer> sorted_map = sortMapByValue(map);
        //the max value is: 
        Map.Entry entry = (Map.Entry)sorted_map.entrySet().iterator().next();
        
        int maxValue = (int)entry.getValue();//the mas value of the words
        int timesChanged =1;
        int theValueBeforeCurrent = maxValue;
        ArrayList<Word> tempList = new ArrayList<Word>();
        
        for(Iterator iterator = sorted_map.entrySet().iterator(); iterator.hasNext();){
            Map.Entry entrys = (Map.Entry)iterator.next();
            if((int)entrys.getValue()==maxValue){
                continue;
            }
            if((int)entrys.getValue()<theValueBeforeCurrent){
                theValueBeforeCurrent = (int)entrys.getValue();
                timesChanged++;
            }
            if(timesChanged ==3){//found it!
                Word tempWord = new Word((String)entrys.getKey());
                tempList.add(tempWord);
            }
            if(timesChanged>3){
                break; //no need to go on
            }
        }
        the3rdLongestWords = tempList;
    }

    public void setParagraph(String paragraph) {
        this.paragraph = paragraph;
    }
    
    
    private void processRawTextIntoSentence(String paragraph){
        
        if(paragraph.length()<1){
            return;
        }
        String[] splitedRawText = paragraph.split(pattern);
        //populate the sentence list
        for(String str : splitedRawText){
            if(str.length()>0){
                Sentence tempSentence = new Sentence(str);
                sentenceList.add(tempSentence);//populate the sentence list
                ArrayList<Word> wordInTempSentence = tempSentence.getWords();
                wordList.addAll(wordInTempSentence);//add all words into the word list
            }
        }
    }
    
    
    
    private Map<String, Integer> sortMapByValue(Map<String, Integer> oriMap) {  
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();  
        if (oriMap != null && !oriMap.isEmpty()) {  
            List<Map.Entry<String, Integer>> entryList = new ArrayList<Map.Entry<String, Integer>>(oriMap.entrySet());  
            Collections.sort(entryList,  
                    new Comparator<Map.Entry<String, Integer>>() {  
                        public int compare(Entry<String, Integer> entry1,  
                                Entry<String, Integer> entry2) {  
                                int value1 = 0, value2 = 0;  
                                try {  
                                    value1 = entry1.getValue();  
                                    value2 = entry2.getValue();  
                                } catch (NumberFormatException e) {  
                                    value1 = 0;  
                                    value2 = 0;  
                                }  
                            return value2 - value1;  
                        }  
                    });  
            Iterator<Map.Entry<String, Integer>> iter = entryList.iterator();  
            Map.Entry<String, Integer> tmpEntry = null;  
            while (iter.hasNext()) {  
                tmpEntry = iter.next();  
                sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());  
            }  
        }  
        return sortedMap;  
    }
    
}
