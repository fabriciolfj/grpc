package client;

import com.github.fabriciolfj.protobuf.Balance;
import com.github.fabriciolfj.protobuf.BalanceCheckRequest;
import com.github.fabriciolfj.protobuf.BankServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BankClient {

    private BankServiceGrpc.BankServiceBlockingStub bankServiceBlockingStub;

    @BeforeAll
    public void setup() {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 6565)
            .usePlaintext()
            .build();

        this.bankServiceBlockingStub = BankServiceGrpc.newBlockingStub(managedChannel);
    }

    @Test
    public void balanceTest() {
        BalanceCheckRequest balanceCheckRequest = BalanceCheckRequest
                .newBuilder()
                .setAccountNumber(11)
                .build();
        Balance balance = this.bankServiceBlockingStub.getBalance(balanceCheckRequest);
        System.out.println(balance);
    }
}
