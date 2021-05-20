package com.github.fabriciolfj.grpc.server;

import com.github.fabriciolfj.protobuf.TransferRequest;
import com.github.fabriciolfj.protobuf.TransferResponse;
import com.github.fabriciolfj.protobuf.TransferServiceGrpc;
import io.grpc.stub.StreamObserver;

public class TransferService extends TransferServiceGrpc.TransferServiceImplBase {

    @Override
    public StreamObserver<TransferRequest> transfer(StreamObserver<TransferResponse> responseObserver) {
        return super.transfer(responseObserver);
    }
}
