package globant;

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
        Trie node = searchPrefix(prefix);
        return node != null && node.isEnd;
    }
}
