package client;

import com.github.fabriciolfj.grpc.server.BalanceStreamObserver;
import com.github.fabriciolfj.grpc.server.MoneyStreamingResponse;
import com.github.fabriciolfj.protobuf.Balance;
import com.github.fabriciolfj.protobuf.BalanceCheckRequest;
import com.github.fabriciolfj.protobuf.BankServiceGrpc;
import com.github.fabriciolfj.protobuf.DepositRequest;
import com.github.fabriciolfj.protobuf.WithdrawRequest;
import com.google.common.util.concurrent.Uninterruptibles;
import com.google.protobuf.DescriptorProtos;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BankClient {

    private BankServiceGrpc.BankServiceBlockingStub bankServiceBlockingStub;
    private BankServiceGrpc.BankServiceStub bankServiceStub;

    @BeforeAll
    public void setup() {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 6565)
            .usePlaintext()
            .build();

        this.bankServiceBlockingStub = BankServiceGrpc.newBlockingStub(managedChannel);
        this.bankServiceStub = BankServiceGrpc.newStub(managedChannel);
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

    //blocking
    @Test
    public void withdrawTest() {
        WithdrawRequest withdrawRequest = WithdrawRequest.newBuilder()
                .setAccountNumber(7)
                .setAmount(40)
                .build();
        this.bankServiceBlockingStub
                .withdraw(withdrawRequest)
                .forEachRemaining(money -> System.out.println("Received: " + money.getValue()));

    }

    //async
    @Test
    public void withdrawTestAsync() throws InterruptedException {
        WithdrawRequest withdrawRequest = WithdrawRequest.newBuilder()
                .setAccountNumber(4)
                .setAmount(20)
                .build();
        CountDownLatch latch = new CountDownLatch(1);
        this.bankServiceStub.withdraw(withdrawRequest, new MoneyStreamingResponse(latch));
        latch.await();
    }

    //client streaming
    @Test
    public void caschStreamingRequest() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        var streamObserver = this.bankServiceStub.cashDeposit(new BalanceStreamObserver(latch));

        for (int i = 0; i < 10; i++) {
            var deposit = DepositRequest.newBuilder()
                    .setAccountNumber(8)
                    .setAmount(10)
                    .build();

            streamObserver.onNext(deposit);
        }
        streamObserver.onCompleted();
        latch.await();
    }
}
