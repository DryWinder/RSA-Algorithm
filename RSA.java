import java.math.*;
import java.util.*;

public class RSA {
    private BigInteger p;
    private BigInteger q;
    private BigInteger e;
    private BigInteger n;
    private BigInteger f_n;
    private BigInteger d;

    public RSA(BigInteger p, BigInteger q, BigInteger e) {
        this.p = p;
        this.q = q;
        this.e = e;
        this.n = p.multiply(q);
        this.f_n = (p.subtract(BigInteger.valueOf(1))).multiply(q.subtract(BigInteger.valueOf(1)));
        this.d = e.modInverse(f_n);
    }

    public void pr(BigInteger p, BigInteger q, BigInteger e){
        RSA rsa = new RSA(p, q, e);
        System.out.println(this.n + " " + this.f_n);
    }



    public BigInteger encryptForInt(BigInteger message){
        BigInteger c = (message.pow(e.intValue())).remainder(n);
        return c;
    }
    public BigInteger decryptForInt(BigInteger c){
        BigInteger message;
        message = c.modPow(d,n);
        //System.out.println("Decrypt(c,d,n,message): " + c + " " + d + " " + n + " " + message );
        return message;
    }

    public BigInteger[] strToInt(String message){
        BigInteger[] intMessageArray = new BigInteger[message.length()];
        for (int i = 0; i < message.length(); i++) {
            BigInteger elOfIntArr = BigInteger.valueOf(((int) message.charAt(i)));
            intMessageArray[i] = elOfIntArr;
        }
        return intMessageArray;
    }

    public BigInteger[] encryptForStringMessage(String message){
        BigInteger[] intMessageArr = strToInt(message);
        BigInteger[] encryptedIntMessageArr = new BigInteger[intMessageArr.length];
        for (int i = 0; i < intMessageArr.length; i++) {
            BigInteger EncryptElemOfIntMessage = (intMessageArr[i].pow(e.intValue())).remainder(n);
            encryptedIntMessageArr[i] = EncryptElemOfIntMessage;
        }
        return encryptedIntMessageArr;
    }

    public int[] decryptForStringMessage(BigInteger[] array){
        int[] DecryptedIntMessageArr = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            BigInteger DecryptElemOfIntMessage = decryptForInt(array[i]);
            //System.out.println();
            //System.out.println("BigInt: " + DecryptElemOfIntMessage);
            int elOfDecryptMessage = DecryptElemOfIntMessage.intValue();
            DecryptedIntMessageArr[i] = elOfDecryptMessage;
            //System.out.println("Int: " + elOfDecryptMessage);
        }
        return DecryptedIntMessageArr;
    }

    public BigInteger[] encrypt(RSA rsa, String message){
        System.out.println("Encrypted sequence from message '" + message +"':");
        BigInteger[] enc = rsa.encryptForStringMessage(message);
        for (int i = 0; i < enc.length; i++) {
            System.out.print(enc[i] + ", ");
        }
        return enc;
    }

    public String decrypt(RSA rsa, int[] encryptedSequence){
        char[] DecryptedCharMessageArr = new char[encryptedSequence.length];
        for (int i = 0; i < encryptedSequence.length; i++) {
            char elOfDecryptMessageArr = (char) encryptedSequence[i];
            DecryptedCharMessageArr[i] = elOfDecryptMessageArr;
        }
        String decryptedMessage = new String(DecryptedCharMessageArr);
        return decryptedMessage;
    }

    public static void main(String[] args) {

        RSA rsa = new RSA(BigInteger.valueOf(19), BigInteger.valueOf(29), BigInteger.valueOf(41));

        BigInteger[] encryptedSequence = rsa.encrypt(rsa, "LOCATION");

        //If I given only an encrypted sequence
        /*int[] intEncArr = {95, 243, 535, 373, 126, 346, 243, 393};
        BigInteger[] encArr = new BigInteger[intEncArr.length];
        for (int i = 0; i < intEncArr.length ; i++)
            encArr[i] = BigInteger.valueOf(intEncArr[i]);*/

        int[] decSeq = rsa.decryptForStringMessage(encryptedSequence);
        System.out.println("\nDecrypted sequence: ");
        for (int i = 0; i < decSeq.length; i++) {
            System.out.print(decSeq[i] + ", ");
        }
        System.out.println("\nDecrypted message: " + rsa.decrypt(rsa, decSeq));

        //System.out.println(rsa.decryptForStringMessage(encArr));

        //System.out.println(rsa.decryptForInt(BigInteger.valueOf(4051753)));
    }
}
