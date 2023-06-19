public class IsAnagram {
    public boolean isAnagram(String s, String t) {
        return getEncodedString(s).equals(getEncodedString(t));

    }

    String getEncodedString(String s){
        StringBuilder s1;
        int[] ar = new int[26];
        for(int i=0;i<s.length();i++){
            ar[s.charAt(i)-'a']++;
        }

        char a = 'a';
        s1 = new StringBuilder();
        for(int i=0;i<26;i++){
            s1.append(a);
            s1.append(ar[i]);
            s1.append("#");
            a++;
        }
        return s1.toString();
    }
}
