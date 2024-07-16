// Time Complexity : o(N)
// Space Complexity : O(N)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No



class Solution {
    class TrieNode {
        boolean isEnd;
        TrieNode[] children;

        public TrieNode() {
            isEnd = false;
            children = new TrieNode[26];
        }
    }

    TrieNode root;

    public void insert(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (curr.children[c - 'a'] == null) {
                curr.children[c - 'a'] = new TrieNode();
            }
            curr = curr.children[c - 'a'];
        }

        curr.isEnd = true;
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        if(sentence == null || sentence.length() == 0){
            return sentence;
        }
        root = new TrieNode();
        for(String word : dictionary){
            insert(word);
        }
        String[] strArray = sentence.split(" ");
        StringBuilder answer = new StringBuilder();
        for(int i = 0; i < strArray.length; i++){
            if(i!=0){
                answer.append(" ");
            }
            String word = strArray[i];
            TrieNode curr = root;
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < word.length(); j++){
                char c = word.charAt(j);
                if(curr.children[c-'a'] == null || curr.isEnd == true){
                    break;
                }
                sb.append(c);
                curr = curr.children[c-'a'];
            }
            if(curr.isEnd){
                answer.append(sb);
            }else{
                answer.append(word);
            }
        }
        return answer.toString();
    }
}