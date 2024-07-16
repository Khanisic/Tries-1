
// Time Complexity : o(N*M) -> Length of the string
// Space Complexity : O(N)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


class Solution { 
    class TrieNode{ // creating the Trie class
        boolean isEnd; // helpfup to know if we at the end of a word
        TrieNode[] children; // array of TrieNodes, 26

        public TrieNode(){
            isEnd = false;
            children = new TrieNode[26];
        }
    }

    TrieNode root = new TrieNode();

    public void insert(String word){ // inserting all the words in the Trie
        TrieNode curr = root;
        for(char c : word.toCharArray()){
            if(curr.children[c - 'a'] == null){
                curr.children[c - 'a'] = new TrieNode();
            }
            curr = curr.children[c - 'a'];
        }
        curr.isEnd = true; // setting last character to true
    }

    String max = new String();

    public String longestWord(String[] words) {
        for(String word : words){  // inserting all the words in the Trie
            insert(word);
        }
        dfs(root,new StringBuilder()); // dfs with a string to capture the longest string
        return max;
    }

    private void backtrack(TrieNode curr , StringBuilder path){
       
        if(path.length() > max.length()){ // new longest found
            maxStr = path.toString();
        }

        for(int i = 0 ; i < 26 ; i++){
            if(curr.children[i] != null && curr.children[i].isEnd){ // .isEnd to check if the longest word can be created letter by letter
                int len = path.length();
                //action
                path.append((char)(i + 'a')); // add to the path
                //recurse
                backtrack(curr.children[i], path); // go down further in the trie
                //backtrack
                path.setLength(len); // remove from path
            }
        }
    }
}