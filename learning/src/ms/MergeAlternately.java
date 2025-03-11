package ms;

public class MergeAlternately {
    public String mergeAlternately(String word1, String word2) {
        StringBuilder sb = new StringBuilder();
        int i=0;
        int j=0;
        char first[] = word1.toCharArray();
        char second[] = word2.toCharArray();
        for(;i<first.length;i++){
            sb.append(first[i]);
            Character secondChar = getSecondChar(j,second);
            if(secondChar!=null){
                sb.append(secondChar);
                j++;
            }
        }

        for(;j<second.length;j++){
            char c = second[j];
            sb.append(c);
        }
        return sb.toString();

    }

    Character getSecondChar(int pos, char[] second){
        if(pos<second.length){
            return second[pos];
        }
        return null;
    }
}
