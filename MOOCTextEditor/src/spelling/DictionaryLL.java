package spelling;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * A class that implements the Dictionary interface using a LinkedList
 *
 */
public class DictionaryLL implements Dictionary 
{

	private LinkedList<String> dict;
	
    // TODO: Add a constructor


    /** Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
    public boolean addWord(String word) {
    	// TODO: Implement this method
    	// TODO: Implement this method
    	 if (dict==null)
    	 {
    		 dict = new LinkedList<>();
    	 }
    	
    	String wordToAdd = word.toLowerCase();
    	if (dict.contains(wordToAdd)) {
    		return false;
    	} else {
    		dict.add(wordToAdd);
    		return true;
          
    	}
    	 
    
    }


    /** Return the number of words in the dictionary */
    public int size()
    {
    	int counter=0;
     	if(dict!=null && !dict.isEmpty())
     	{
    	Iterator i= dict.iterator();
    	while(i.hasNext())
    	{
    		i.next();
    		counter++;
    	}
     	}
        return counter;
    }

    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {
    	//TODO: Implement this method
    	String sToCheck = s.toLowerCase();
    	if(dict!=null && !dict.isEmpty())
    	{
    	if (dict.contains(sToCheck)) {
    		return true;
    	} else {
    		return false;
    	}
    	}
    	return false;
    }

    
}
