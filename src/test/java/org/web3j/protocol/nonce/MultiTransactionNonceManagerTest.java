package org.web3j.protocol.nonce;


import org.junit.Before;
import org.web3j.protocol.Web3j;

import static org.mockito.Mockito.mock;

public class MultiTransactionNonceManagerTest {
    private Web3j web3j;

    @Before
    public void setUp() {
        web3j = mock(Web3j.class);
    }
}
