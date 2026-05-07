package globant;

/*
A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.
Implement the Trie class:
Trie() Initializes the trie object.
void insert(String word) Inserts the string word into the trie.
boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.
*/
public class Trie {
    private Trie[] node;
    boolean isEnd;

    public Trie() {
        node = new Trie[26];
        isEnd = false;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("apple");
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.startsWith("app"));
        System.out.println(trie.startsWith("appl"));

        trie.insert("app");
        System.out.println(trie.search("app"));
    }

    private void insert(String word) {
        Trie currentNode = this;

        for (char c : word.toCharArray()) {
            int index = c - 'a';

            if (currentNode.node[index] == null) {
                currentNode.node[index] = new Trie();
            }

            currentNode = currentNode.node[index];
        }

        currentNode.isEnd = true;
    }

    private boolean search(String word) {
        Trie node = searchPrefix(word);
        return node != null && node.isEnd;
    }

    private Trie searchPrefix(String word) {
        Trie currentNode = this;

        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (currentNode.node[index] == null) {
                return null;
            } else {
                currentNode = currentNode.node[index];
            }
        }
        return currentNode;
    }

    private boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }
}
