/*
* Islam mohamed
* Eddom Mikaeel
* Ali Khawari 
* */

import java.util.*;
import java.nio.charset.StandardCharsets;

class Solution {


    private static byte[] hexToByteArray(String s) {
        int length = s.length();
        if(length % 2 != 0){
            s = "0" + s;
            length++;
        }
        byte[] data = new byte[length/2];
        for (int i =0; i <length; i+=2){
            data[i/2] = (byte) ((Character.digit(s.charAt(i),16) <<4) + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    public static String xorHex(String a, String b){
        char[] chars = new char[a.length()];
        for(int i=0; i <chars.length; i++){
            chars[i] = toHex(fromHex(a.charAt(i)) ^fromHex(b.charAt(i)));
        }
        return new String(chars);
    }

    private static char toHex(int x){
        if(x <0 || x >15){
            throw new IllegalArgumentException();
        }
        return "0123456789ABCDEFGHIJK".charAt(x);
    }
    private static int fromHex(char c){
        if(c>= '0' && c <= '9'){
            return c - '0';
        }
        if(c >= 'A' && c <= 'K'){
            return c - 'A' + 10;
        }
        if(c >= 'a' && c <= 'k'){
            return c - 'a' + 10;
        }
        throw new IllegalArgumentException();
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String message1 = in.nextLine();
        String message2 = in.nextLine();
        String message3 = in.nextLine();
        in.close();
        String bobKey = xorHex(message1, message2);
        String aliceKey = xorHex(message2, message3);
        String theMessage = xorHex(message1, aliceKey);
        byte[] bytes = hexToByteArray(theMessage);
        String clearMessage = new String(bytes, StandardCharsets.UTF_8);
        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");
        System.out.println(clearMessage);
    }
}
