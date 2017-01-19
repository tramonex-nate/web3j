package org.web3j.protocol.nonce;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

public interface NonceManager {
    BigInteger getNonce(String address) throws InterruptedException, ExecutionException;
}
