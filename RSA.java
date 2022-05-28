import org.jetbrains.annotations.NotNull;

import java.math.*;

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


    public BigInteger encryptForInt(BigInteger message){
        BigInteger c = (message.pow(e.intValue())).remainder(n);
        return c;
    }
    public BigInteger decryptForInt(BigInteger c){
        BigInteger message;
        message = c.modPow(d,n);

        return message;
    }

    private BigInteger[] strToInt(String message){
        BigInteger[] intMessageArray = new BigInteger[message.length()];
        for (int i = 0; i < message.length(); i++) {
            BigInteger elOfIntArr = BigInteger.valueOf(((int) message.charAt(i)));
            intMessageArray[i] = elOfIntArr;
        }
        return intMessageArray;
    }

    private BigInteger[] encryptForStringMessage(String message){
        BigInteger[] intMessageArr = strToInt(message);
        BigInteger[] encryptedIntMessageArr = new BigInteger[intMessageArr.length];
        for (int i = 0; i < intMessageArr.length; i++) {
            BigInteger EncryptElemOfIntMessage = (intMessageArr[i].pow(e.intValue())).remainder(n);
            encryptedIntMessageArr[i] = EncryptElemOfIntMessage;
        }
        return encryptedIntMessageArr;
    }

    private int[] decryptForStringMessage(BigInteger[] array){
        int[] DecryptedIntMessageArr = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            BigInteger DecryptElemOfIntMessage = decryptForInt(array[i]);
            int elOfDecryptMessage = DecryptElemOfIntMessage.intValue();
            DecryptedIntMessageArr[i] = elOfDecryptMessage;
        }
        return DecryptedIntMessageArr;
    }

    public BigInteger[] encrypt(@NotNull RSA rsa, String message){
        System.out.println("Encrypted sequence from message '" + message +"':");
        BigInteger[] enc = rsa.encryptForStringMessage(message);
        for (int i = 0; i < enc.length; i++) {
            System.out.print(enc[i] + ", ");
        }
        return enc;
    }

    public String decrypt(@NotNull RSA rsa, int[] encryptedSequence){
        char[] DecryptedCharMessageArr = new char[encryptedSequence.length];
        for (int i = 0; i < encryptedSequence.length; i++) {
            char elOfDecryptMessageArr = (char) encryptedSequence[i];
            DecryptedCharMessageArr[i] = elOfDecryptMessageArr;
        }
        String decryptedMessage = new String(DecryptedCharMessageArr);
        return decryptedMessage;
    }

    private BigInteger[] intToBigIntArray(int[] array){
        BigInteger[] encArr = new BigInteger[array.length];
        for (int i = 0; i < array.length ; i++) {
            encArr[i] = BigInteger.valueOf(array[i]);
        }
        return encArr;
    }
    public void infoRSA(@NotNull RSA rsa, BigInteger[] encArr){
        System.out.println("p = " + rsa.p + " q = " + rsa.q + " n = " + rsa.n + " e = " + rsa.e);
        System.out.println("Public key {e, n} = " + "{" + rsa.e + ", " + rsa.n + "}");
        System.out.println("Private key {d, n} = " + "{" + rsa.d + ", " + rsa.n + "}");
        System.out.println("F_n = " + rsa.f_n + " d = " + rsa.d );

        System.out.println("Encrypted sequence: ");
        for (int i = 0; i < encArr.length ; i++) {
            System.out.print(encArr[i] + ", ");
        }

        int[] decSeq = rsa.decryptForStringMessage(encArr);
        System.out.println("\nDecrypted sequence: ");
        for (int i = 0; i < decSeq.length; i++) {
            System.out.print(decSeq[i] + ", ");
        }

        System.out.println("\nDecrypted message: " + rsa.decrypt(rsa, decSeq));
    }
    public static void main(String[] args) {
        RSA rsa = new RSA(BigInteger.valueOf(5), BigInteger.valueOf(47), BigInteger.valueOf(37));
        int[] intEncArr = {82, 41, 190, 14, 98, 63, 145, 157};
        BigInteger[] encArr = rsa.intToBigIntArray(intEncArr);
        rsa.infoRSA(rsa, encArr);
    }
}
