package com.github.fabriciolfj.grpc.server;


import com.github.fabriciolfj.protobuf.Balance;
import com.github.fabriciolfj.protobuf.BalanceCheckRequest;
import com.github.fabriciolfj.protobuf.BankServiceGrpc;
import io.grpc.stub.StreamObserver;

public class BankService extends BankServiceGrpc.BankServiceImplBase {

    @Override
    public void getBalance(BalanceCheckRequest request, StreamObserver<Balance> responseObserver) {
        int accountNumber = request.getAccountNumber();
        final Balance balance = Balance.newBuilder().setAmount(AccountDataBase.getBalance(accountNumber)).build();

        responseObserver.onNext(balance);
        responseObserver.onCompleted();
    }
}
