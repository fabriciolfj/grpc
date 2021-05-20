package com.github.fabriciolfj.grpc.server;


import com.github.fabriciolfj.protobuf.Balance;
import com.github.fabriciolfj.protobuf.BalanceCheckRequest;
import com.github.fabriciolfj.protobuf.BankServiceGrpc;
import com.github.fabriciolfj.protobuf.DepositRequest;
import com.github.fabriciolfj.protobuf.Money;
import com.github.fabriciolfj.protobuf.WithdrawRequest;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

public class BankService extends BankServiceGrpc.BankServiceImplBase {

    @Override
    public void getBalance(BalanceCheckRequest request, StreamObserver<Balance> responseObserver) {
        int accountNumber = request.getAccountNumber();
        final Balance balance = Balance.newBuilder().setAmount(AccountDataBase.getBalance(accountNumber)).build();

        responseObserver.onNext(balance);
        responseObserver.onCompleted();
    }

    @Override
    public void withdraw(WithdrawRequest request, StreamObserver<Money> responseObserver) {
        int accountNumber = request.getAccountNumber();
        int amount = request.getAmount(); //10, 20, 30
        int balance = AccountDataBase.getBalance(accountNumber);

        if (balance <  amount) {
            var status = Status.FAILED_PRECONDITION.withDescription("No enough money. You have only " + balance);
            responseObserver.onError(status.asRuntimeException());
            return;
        }

        //all the validations passed
        for(int i = 0; i < (amount /10); i++) {
            var money = Money.newBuilder()
                    .setValue(10)
                    .build();
            AccountDataBase.deductBalanc(accountNumber, 10);
            responseObserver.onNext(money);
        }

        responseObserver.onCompleted();

    }

    @Override
    public StreamObserver<DepositRequest> cashDeposit(StreamObserver<Balance> responseObserver) {
        return new CaschStreamingRequest(responseObserver);
    }

}
