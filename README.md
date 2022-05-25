This program implements RSA Algorithm ( https://en.wikipedia.org/wiki/RSA_(cryptosystem) )
By creating RSA Class, which has such BigInteger variables:
p, q, n = (p*q), e, f_n = (p-1)*(q-1), d = e^(-1)mod(f_n) - This means: solve for d the equation d⋅e ≡ 1 (mod f_n(n));

Program operates with large numbers, that sometimes bigger than int and long, so it's a point to use BigInteger class.
Program takes some values from user - (p, q, e) to create Object of RSA class (RSA rsa)
Also we need user to give message he wants to encrypt - there are two types:
1. int message - stands for int number to encrypt&decrypt
2. string message - message gets splited in chars -> to int by ASCII table. Each int gets encrypted/decrypted 
program implements two types of methods, for each type of message - there are special parts in method's names for them:
1. "ForIntMessage" methods - for int message:
-BigInteger encryptForInt(BigInteger message) - computes c(encrypted message) variable;
-BigInteger decryptForInt(BigInteger c) - decodes c returning inputed message;

2. "ForStringMessage" - for string message
-BigInteger[] encryptForStringMessage(String message) - encodes string message by transorming string message into BigInt array , using strToInt() method, returns Bigint array;

-BigInteger[] encrypt(RSA rsa, String message) - encodes string message, returns BigInt array
-int[] decryptForStringMessage(BigInteger[] array) - decryptes every element in BigInt array of encoded values, using decryptForInt() method;
-String decrypt(RSA rsa, int[] encryptedSequence) - returns decoded string message;


3. -BigInteger[] strToInt(String message) - transorms string message into BigInt array;

Usage of program you can check in RSA.main();
Good luck!
