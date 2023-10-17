package com.codewithdurgesh.blog;

public class Test {
    public static String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();

        String temp = "";

        for (int i=0; i<s.length();i++) {
            char ch = s.charAt(i);
            if (ch == ' ') {
                if (!temp.equals("")) {
                    sb.insert(0,temp+" ");
                }
                temp = "";
            }
            else
                temp += ch;
        }
        return sb.toString().substring(0,sb.length()-1);
    }

    public static void main(String[] args) {
        String s1="   Welcome to   Geeks For Geeks   ";
        System.out.println("Before reversing length of string : "+s1.length());
        String ans1=reverseWords(s1);
        System.out.println("After reversing length of string : "+ans1.length());
        System.out.println("\""+ans1+"\"\n");

        String s2="   I Love Java   Programming      ";
        System.out.println("Before reversing length of string : "+s2.length());
        String ans2=reverseWords(s2);
        System.out.println("After reversing length of string : "+ans2.length());
        System.out.println("\""+ans2+"\"");
    }
}
