package spelling;

import java.util.LinkedList;
import java.util.List;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should ignore the word's case.
	 * That is, you should convert the string to all lower case as you insert it. */
	public boolean addWord(String word)
	{//TODO: Implement this method.
		String wordToAdd = word.toLowerCase();
		TrieNode node = root;
		for (int i = 0; i < wordToAdd.length(); i++) {
			char c = wordToAdd.charAt(i);
			if (node.getValidNextCharacters().contains(c)) {
				node = node.getChild(c);
			} else {
				node = node.insert(c);
			}
		}
		if (!node.endsWord()) {
			node.setEndsWord(true);
			size++;
			return true;
		}
	    return false;
 
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) 
	{
		String lowerCase = s.toLowerCase();
		TrieNode node = root;
		for (int i = 0; i < lowerCase.length(); i++) 
		{
			if (node.getValidNextCharacters().contains(lowerCase.charAt(i)))  
				node = node.getChild(lowerCase.charAt(i));
			 else  
				return false;		 
		}
		if (node.endsWord())  
			return true;
		else
			return false;
	}

	/** 
	 *  * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     * @param text The text to use at the word stem
     * @param n The maximum number of predictions desired.
     * @return A list containing the up to n best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
    	 
    	 //Trying to find the stem in Trie
      	 String prefixToCheckLowerCase = prefix.toLowerCase();
    	 int completionsCount = 0;
    	 List<String> completions = new LinkedList<String>();
    	 TrieNode traversal = root;
    	 for (int i = 0; i < prefixToCheckLowerCase.length(); i++)
    	 {
    		 if (traversal.getValidNextCharacters().contains(prefixToCheckLowerCase.charAt(i)))
    		{
 				traversal = traversal.getChild(prefixToCheckLowerCase.charAt(i));
 			} 
    		 //Means  stem not found, returns an empty list
    		 else
 				return completions;
    	 }
    	 //If current word is an end word, increment the counter and add it to compeltions list
    	 if (traversal.endsWord()) 
    	 {
    		 completionsCount=1;
    		 completions.add(traversal.getText());
    	 }
    	 
    	 List<TrieNode> nodesToBeSearched = new LinkedList<TrieNode>();
    	 List<Character> ChildCharaterList = new LinkedList<Character>(traversal.getValidNextCharacters());
    	 //Filling the list with children of the current node, first level of of the breadth first search 
    	 for (int i=0; i<ChildCharaterList.size(); i++) 
    	 {
    		 nodesToBeSearched.add(traversal.getChild(ChildCharaterList.get(i)));
    	 }
    	 //while loop for the linked list elements and see if any compeltions exists , inside it we will also check each node children and add them to the list!!!
    	 while (nodesToBeSearched!=null  && nodesToBeSearched.size()>0 && completionsCount < numCompletions)
    	 {
    		 TrieNode trieNode = nodesToBeSearched.remove(0);
    		 if (trieNode.endsWord()) 
    		 {
    			 completionsCount++;
    			 completions.add(trieNode.getText());
    		 }
    		 
    		 List<Character> subTrieNodeCholdren = new LinkedList<Character>(trieNode.getValidNextCharacters());
    		 //Adding all next level tries to the linked list , kinda recursive!!!
        	 for (int i=0; i<subTrieNodeCholdren.size();i++) 
        	 {
        		 nodesToBeSearched.add(trieNode.getChild(subTrieNodeCholdren.get(i)));
        	 }
    	 }
         return completions;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}