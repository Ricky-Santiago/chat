package dh;

import java.math.BigInteger;
import java.security.SecureRandom;

public class DHKeyExchange {
    private BigInteger p;
    private BigInteger g;
    private BigInteger privateKey;
    private BigInteger publicKey;
    private BigInteger sharedKey;

    public static final BigInteger DEFAULT_P = new BigInteger("23");
    public static final BigInteger DEFAULT_G = new BigInteger("5");

    public DHKeyExchange(BigInteger p, BigInteger g) {
        this.p = p;
        this.g = g;
        generatePrivateKey();
        generatePublicKey();
    }

    private void generatePrivateKey() {
        SecureRandom random = new SecureRandom();
        privateKey = new BigInteger(p.bitLength() - 1, random);
    }

    private void generatePublicKey() {
        publicKey = g.modPow(privateKey, p);
    }

    public void generateSharedKey(BigInteger otherPublicKey) {
        sharedKey = otherPublicKey.modPow(privateKey, p);
    }

    public BigInteger getPublicKey() {
        return publicKey;
    }

    public BigInteger getSharedKey() {
        return sharedKey;
    }
}
