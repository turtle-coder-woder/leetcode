package p150problems.other;

class Trie {
    static class Node {
        public char character;
        public boolean isWord;
        public int totalWords = 0;
        public Node[] next = new Node[26];

        Node(char x, boolean isWord) {
            this.character = x;
            this.isWord = isWord;
        }

        public Node() {
            isWord = false;
            totalWords = 0;
        }
    }

    Node[] childrens;
    Node[] prevPointer;

    public Trie() {
        childrens = new Node[26];
    }

    public void insert(String word) {
        if (word.equals("")) {
            return;
        }
        Node[] ptr = childrens;
        Node[] prevPtr = childrens;
        char[] wordArray = word.toCharArray();
        for (char c : wordArray) {
            int index = getIndex(c);
            if (ptr[index] == null) {
                ptr[index] = new Node();
            }
            ptr[index].character = c;
            ptr[index].totalWords++;
            prevPtr = ptr;
            ptr = ptr[index].next;
        }

        prevPtr[getIndex(wordArray[wordArray.length - 1])].isWord = true;

    }

    public boolean search(String word) {
        if (word.equals("")) {
            return false;
        }

        if (!startsWith(word)) {
            return false;
        }

        return prevPointer[getIndex(word.charAt(word.length() - 1))].isWord;
    }

    public boolean startsWith(String prefix) {
        Node[] ptr = childrens;
        prevPointer = childrens;
        for (int i = 0; i < prefix.length(); i++) {
            char currentCharacter = prefix.charAt(i);
            int index = getIndex(currentCharacter);
            if (ptr == null || ptr[index] == null ||
                    (ptr[index].character != currentCharacter && ptr[index].totalWords > 0)) {
                return false;
            }
            prevPointer = ptr;
            ptr = ptr[index].next;
        }
        return true;
    }

    private int getIndex(char c) {
        return c - 'a';
    }


    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("boddy");
        trie.insert("bobby");
        trie.insert("zen");
        trie.insert("axios");

        trie.insert("ape");
        System.out.println(trie.search("ape"));   // return True

        trie.delete("ape");
        System.out.println(trie.search("ape"));   // return false

        System.out.println(trie.search("apple"));   // return True
        System.out.println(trie.search("app"));     // return False
        System.out.println(trie.startsWith("app")); // return True
        trie.insert("app");
        System.out.println(trie.search("app"));     // return True
        System.out.println(trie.findNumberInSortedWay("apple"));     // return 2
    }

    private int findNumberInSortedWay2(String word) {
        if (word.length() == 0) {
            return 0;
        }

        Node[] ptr = childrens;
        prevPointer = childrens;
        int countOfWord = 0;
        int wordIndex = 0;
        while (wordIndex < word.length()) {
            char currentChar = word.charAt(wordIndex++);
            int index = getIndex(currentChar);
            for (int i = 0; i < index; i++) {
                if (ptr[i] != null) {
                    countOfWord += ptr[i].totalWords;
                }
            }
            prevPointer = ptr;
            if (ptr[index] == null || ptr[index].totalWords <= 0) {
                return -1;
            }
            ptr = ptr[index].next;
        }

        int lastCharIndex = getIndex(word.charAt(word.length() - 1));
        if (prevPointer[lastCharIndex].isWord) {
            countOfWord += prevPointer[lastCharIndex].totalWords;
        } else {
            countOfWord = -1;
        }

        return countOfWord;
    }

    private int findNumberInSortedWay(String word) {
        if (word == null || word.length() == 0) {
            return -1;
        }

        Node[] ptr = childrens;
        int countOfWord = 0;

        for (int pos = 0; pos < word.length(); pos++) {
            int index = getIndex(word.charAt(pos));

            // Count all words starting with smaller characters at this level
            for (int i = 0; i < index; i++) {
                if (ptr[i] != null) {
                    countOfWord += ptr[i].totalWords;
                }
            }

            // Current path does not exist => word not present
            if (ptr[index] == null || ptr[index].totalWords <= 0) {
                return -1;
            }

            Node currentNode = ptr[index];

            // If a shorter word ends here, it comes before current longer word
            if (pos < word.length() - 1 && currentNode.isWord) {
                countOfWord++;
            }

            ptr = currentNode.next;
        }

        // Final node must itself be a word
        int lastIndex = getIndex(word.charAt(word.length() - 1));
        Node lastNode = prevPointerSafe(word);

        if (lastNode == null || !lastNode.isWord) {
            return -1;
        }

        return countOfWord + 1; // include the word itself
    }

    private Node prevPointerSafe(String word) {
        Node[] ptr = childrens;
        Node current = null;

        for (int i = 0; i < word.length(); i++) {
            int index = getIndex(word.charAt(i));
            if (ptr[index] == null || ptr[index].totalWords <= 0) {
                return null;
            }
            current = ptr[index];
            ptr = current.next;
        }
        return current;
    }

    private void delete(String word) {
        if (!search(word)) {
            return;
        }

        Node[] ptr = childrens;
        prevPointer = childrens;
        for (char currentChar : word.toCharArray()) {
            int index = getIndex(currentChar);
            ptr[index].totalWords--;
            prevPointer = ptr;
            ptr = ptr[index].next;
        }
        prevPointer[getIndex(word.charAt(word.length() - 1))].isWord = false;
    }

}