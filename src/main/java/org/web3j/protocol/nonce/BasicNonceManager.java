package org.web3j.protocol.nonce;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

public class BasicNonceManager implements NonceManager {
    private final Web3j web3j;

    public BasicNonceManager(Web3j web3j) {
        this.web3j = web3j;
    }

    @Override
    public BigInteger getNonce(String address) throws InterruptedException, ExecutionException {
        EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(
                address, DefaultBlockParameterName.LATEST).sendAsync().get();

        return ethGetTransactionCount.getTransactionCount();
    }
}
