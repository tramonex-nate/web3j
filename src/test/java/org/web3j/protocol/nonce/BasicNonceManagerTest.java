package org.web3j.protocol.nonce;

import org.junit.Before;
import org.junit.Test;
import org.web3j.crypto.SampleKeys;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.utils.Async;

import java.util.concurrent.ExecutionException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BasicNonceManagerTest {
    private Web3j web3j;

    @Before
    public void setUp() {
        web3j = mock(Web3j.class);
    }



    @Test
    void ensureReturnsCorrectNonce() throws ExecutionException, InterruptedException {
        BasicNonceManager basicNonceManager = new BasicNonceManager(web3j);
        EthGetTransactionCount ethGetTransactionCount = new EthGetTransactionCount();
        ethGetTransactionCount.setResult("0x1");
        Request transactionCountRequest = mock(Request.class);

        when(transactionCountRequest.sendAsync()).thenReturn(Async.run(() -> ethGetTransactionCount));

        when(web3j.ethGetTransactionCount(SampleKeys.ADDRESS, DefaultBlockParameterName.LATEST)).thenReturn(transactionCountRequest);
    }

}
