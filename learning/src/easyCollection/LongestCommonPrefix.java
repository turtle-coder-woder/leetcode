package easyCollection;

public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        String longestCommonPrefixString = "";
        if(strs.length == 0){
            return longestCommonPrefixString;
        }else if(strs.length == 1){
            return strs[0];
        }

        longestCommonPrefixString = strs[0];
        for(int i=1;i<strs.length;i++){
            longestCommonPrefixString = longestCommonPrefixOfTwoStrings(longestCommonPrefixString , strs[i]);
        }

        return longestCommonPrefixString;
    }

    String longestCommonPrefixOfTwoStrings(String a, String b){
        StringBuilder longestCommonPrefixStringBw2Strings = new StringBuilder();
        int i=0;
        while(i<a.length() && i<b.length() && a.charAt(i)==b.charAt(i) ){
            longestCommonPrefixStringBw2Strings.append(a.charAt(i++));
        }
        return longestCommonPrefixStringBw2Strings.toString();
    }
}
