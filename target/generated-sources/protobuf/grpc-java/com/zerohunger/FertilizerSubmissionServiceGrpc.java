package com.zerohunger;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Service 3: Fertilizer Submission Service (Client-streaming RPC)
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.63.0)",
    comments = "Source: ClimateControl.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class FertilizerSubmissionServiceGrpc {

  private FertilizerSubmissionServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "zerohunger.FertilizerSubmissionService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.zerohunger.FertilizerInput,
      com.zerohunger.FertilizerAnalysis> getSendFertilizerHistoryMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SendFertilizerHistory",
      requestType = com.zerohunger.FertilizerInput.class,
      responseType = com.zerohunger.FertilizerAnalysis.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.zerohunger.FertilizerInput,
      com.zerohunger.FertilizerAnalysis> getSendFertilizerHistoryMethod() {
    io.grpc.MethodDescriptor<com.zerohunger.FertilizerInput, com.zerohunger.FertilizerAnalysis> getSendFertilizerHistoryMethod;
    if ((getSendFertilizerHistoryMethod = FertilizerSubmissionServiceGrpc.getSendFertilizerHistoryMethod) == null) {
      synchronized (FertilizerSubmissionServiceGrpc.class) {
        if ((getSendFertilizerHistoryMethod = FertilizerSubmissionServiceGrpc.getSendFertilizerHistoryMethod) == null) {
          FertilizerSubmissionServiceGrpc.getSendFertilizerHistoryMethod = getSendFertilizerHistoryMethod =
              io.grpc.MethodDescriptor.<com.zerohunger.FertilizerInput, com.zerohunger.FertilizerAnalysis>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SendFertilizerHistory"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zerohunger.FertilizerInput.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zerohunger.FertilizerAnalysis.getDefaultInstance()))
              .setSchemaDescriptor(new FertilizerSubmissionServiceMethodDescriptorSupplier("SendFertilizerHistory"))
              .build();
        }
      }
    }
    return getSendFertilizerHistoryMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static FertilizerSubmissionServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FertilizerSubmissionServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FertilizerSubmissionServiceStub>() {
        @java.lang.Override
        public FertilizerSubmissionServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FertilizerSubmissionServiceStub(channel, callOptions);
        }
      };
    return FertilizerSubmissionServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static FertilizerSubmissionServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FertilizerSubmissionServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FertilizerSubmissionServiceBlockingStub>() {
        @java.lang.Override
        public FertilizerSubmissionServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FertilizerSubmissionServiceBlockingStub(channel, callOptions);
        }
      };
    return FertilizerSubmissionServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static FertilizerSubmissionServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FertilizerSubmissionServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FertilizerSubmissionServiceFutureStub>() {
        @java.lang.Override
        public FertilizerSubmissionServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FertilizerSubmissionServiceFutureStub(channel, callOptions);
        }
      };
    return FertilizerSubmissionServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Service 3: Fertilizer Submission Service (Client-streaming RPC)
   * </pre>
   */
  public interface AsyncService {

    /**
     */
    default io.grpc.stub.StreamObserver<com.zerohunger.FertilizerInput> sendFertilizerHistory(
        io.grpc.stub.StreamObserver<com.zerohunger.FertilizerAnalysis> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getSendFertilizerHistoryMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service FertilizerSubmissionService.
   * <pre>
   * Service 3: Fertilizer Submission Service (Client-streaming RPC)
   * </pre>
   */
  public static abstract class FertilizerSubmissionServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return FertilizerSubmissionServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service FertilizerSubmissionService.
   * <pre>
   * Service 3: Fertilizer Submission Service (Client-streaming RPC)
   * </pre>
   */
  public static final class FertilizerSubmissionServiceStub
      extends io.grpc.stub.AbstractAsyncStub<FertilizerSubmissionServiceStub> {
    private FertilizerSubmissionServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FertilizerSubmissionServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FertilizerSubmissionServiceStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.zerohunger.FertilizerInput> sendFertilizerHistory(
        io.grpc.stub.StreamObserver<com.zerohunger.FertilizerAnalysis> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getSendFertilizerHistoryMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service FertilizerSubmissionService.
   * <pre>
   * Service 3: Fertilizer Submission Service (Client-streaming RPC)
   * </pre>
   */
  public static final class FertilizerSubmissionServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<FertilizerSubmissionServiceBlockingStub> {
    private FertilizerSubmissionServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FertilizerSubmissionServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FertilizerSubmissionServiceBlockingStub(channel, callOptions);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service FertilizerSubmissionService.
   * <pre>
   * Service 3: Fertilizer Submission Service (Client-streaming RPC)
   * </pre>
   */
  public static final class FertilizerSubmissionServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<FertilizerSubmissionServiceFutureStub> {
    private FertilizerSubmissionServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FertilizerSubmissionServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FertilizerSubmissionServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_SEND_FERTILIZER_HISTORY = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEND_FERTILIZER_HISTORY:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.sendFertilizerHistory(
              (io.grpc.stub.StreamObserver<com.zerohunger.FertilizerAnalysis>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getSendFertilizerHistoryMethod(),
          io.grpc.stub.ServerCalls.asyncClientStreamingCall(
            new MethodHandlers<
              com.zerohunger.FertilizerInput,
              com.zerohunger.FertilizerAnalysis>(
                service, METHODID_SEND_FERTILIZER_HISTORY)))
        .build();
  }

  private static abstract class FertilizerSubmissionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    FertilizerSubmissionServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.zerohunger.ZeroHungerProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("FertilizerSubmissionService");
    }
  }

  private static final class FertilizerSubmissionServiceFileDescriptorSupplier
      extends FertilizerSubmissionServiceBaseDescriptorSupplier {
    FertilizerSubmissionServiceFileDescriptorSupplier() {}
  }

  private static final class FertilizerSubmissionServiceMethodDescriptorSupplier
      extends FertilizerSubmissionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    FertilizerSubmissionServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (FertilizerSubmissionServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new FertilizerSubmissionServiceFileDescriptorSupplier())
              .addMethod(getSendFertilizerHistoryMethod())
              .build();
        }
      }
    }
    return result;
  }
}
