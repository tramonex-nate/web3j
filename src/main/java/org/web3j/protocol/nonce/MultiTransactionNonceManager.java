package org.web3j.protocol.nonce;


import org.web3j.abi.datatypes.Address;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MultiTransactionNonceManager implements NonceManager {
    private final Web3j web3j;

    private HashMap<String, BigInteger> blockAdjustments = new HashMap<>();
    private BigInteger currentBlockNumber;

    public MultiTransactionNonceManager(Web3j web3j) {
        this.web3j = web3j;
    }

    @Override
    public BigInteger getNonce(String address) throws InterruptedException, ExecutionException {

        EthBlockNumber blockNumber = web3j.ethBlockNumber().sendAsync().get();
        if (!blockNumber.getBlockNumber().equals(currentBlockNumber)) {
            blockAdjustments.clear();
            currentBlockNumber = blockNumber.getBlockNumber();
        }

        if (!blockAdjustments.containsKey(address)) {
            EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(
                    address, DefaultBlockParameterName.LATEST).sendAsync().get();
            blockAdjustments.put(address, ethGetTransactionCount.getTransactionCount());
        }

        BigInteger result = blockAdjustments.get(address);
        blockAdjustments.put(address, BigInteger.ONE.add(result));

        return result;
    }
}
