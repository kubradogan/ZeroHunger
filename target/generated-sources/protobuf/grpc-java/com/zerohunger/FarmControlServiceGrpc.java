package com.zerohunger;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Service 4: Farm Control Service (Bidirectional streaming RPC)
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.63.0)",
    comments = "Source: ClimateControl.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class FarmControlServiceGrpc {

  private FarmControlServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "zerohunger.FarmControlService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.zerohunger.DecisionRequest,
      com.zerohunger.AdvisoryResponse> getInteractMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Interact",
      requestType = com.zerohunger.DecisionRequest.class,
      responseType = com.zerohunger.AdvisoryResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.zerohunger.DecisionRequest,
      com.zerohunger.AdvisoryResponse> getInteractMethod() {
    io.grpc.MethodDescriptor<com.zerohunger.DecisionRequest, com.zerohunger.AdvisoryResponse> getInteractMethod;
    if ((getInteractMethod = FarmControlServiceGrpc.getInteractMethod) == null) {
      synchronized (FarmControlServiceGrpc.class) {
        if ((getInteractMethod = FarmControlServiceGrpc.getInteractMethod) == null) {
          FarmControlServiceGrpc.getInteractMethod = getInteractMethod =
              io.grpc.MethodDescriptor.<com.zerohunger.DecisionRequest, com.zerohunger.AdvisoryResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Interact"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zerohunger.DecisionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zerohunger.AdvisoryResponse.getDefaultInstance()))
              .setSchemaDescriptor(new FarmControlServiceMethodDescriptorSupplier("Interact"))
              .build();
        }
      }
    }
    return getInteractMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static FarmControlServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FarmControlServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FarmControlServiceStub>() {
        @java.lang.Override
        public FarmControlServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FarmControlServiceStub(channel, callOptions);
        }
      };
    return FarmControlServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static FarmControlServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FarmControlServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FarmControlServiceBlockingStub>() {
        @java.lang.Override
        public FarmControlServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FarmControlServiceBlockingStub(channel, callOptions);
        }
      };
    return FarmControlServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static FarmControlServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FarmControlServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FarmControlServiceFutureStub>() {
        @java.lang.Override
        public FarmControlServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FarmControlServiceFutureStub(channel, callOptions);
        }
      };
    return FarmControlServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Service 4: Farm Control Service (Bidirectional streaming RPC)
   * </pre>
   */
  public interface AsyncService {

    /**
     */
    default io.grpc.stub.StreamObserver<com.zerohunger.DecisionRequest> interact(
        io.grpc.stub.StreamObserver<com.zerohunger.AdvisoryResponse> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getInteractMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service FarmControlService.
   * <pre>
   * Service 4: Farm Control Service (Bidirectional streaming RPC)
   * </pre>
   */
  public static abstract class FarmControlServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return FarmControlServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service FarmControlService.
   * <pre>
   * Service 4: Farm Control Service (Bidirectional streaming RPC)
   * </pre>
   */
  public static final class FarmControlServiceStub
      extends io.grpc.stub.AbstractAsyncStub<FarmControlServiceStub> {
    private FarmControlServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FarmControlServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FarmControlServiceStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.zerohunger.DecisionRequest> interact(
        io.grpc.stub.StreamObserver<com.zerohunger.AdvisoryResponse> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getInteractMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service FarmControlService.
   * <pre>
   * Service 4: Farm Control Service (Bidirectional streaming RPC)
   * </pre>
   */
  public static final class FarmControlServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<FarmControlServiceBlockingStub> {
    private FarmControlServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FarmControlServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FarmControlServiceBlockingStub(channel, callOptions);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service FarmControlService.
   * <pre>
   * Service 4: Farm Control Service (Bidirectional streaming RPC)
   * </pre>
   */
  public static final class FarmControlServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<FarmControlServiceFutureStub> {
    private FarmControlServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FarmControlServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FarmControlServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_INTERACT = 0;

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
        case METHODID_INTERACT:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.interact(
              (io.grpc.stub.StreamObserver<com.zerohunger.AdvisoryResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getInteractMethod(),
          io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
            new MethodHandlers<
              com.zerohunger.DecisionRequest,
              com.zerohunger.AdvisoryResponse>(
                service, METHODID_INTERACT)))
        .build();
  }

  private static abstract class FarmControlServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    FarmControlServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.zerohunger.ZeroHungerProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("FarmControlService");
    }
  }

  private static final class FarmControlServiceFileDescriptorSupplier
      extends FarmControlServiceBaseDescriptorSupplier {
    FarmControlServiceFileDescriptorSupplier() {}
  }

  private static final class FarmControlServiceMethodDescriptorSupplier
      extends FarmControlServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    FarmControlServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (FarmControlServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new FarmControlServiceFileDescriptorSupplier())
              .addMethod(getInteractMethod())
              .build();
        }
      }
    }
    return result;
  }
}
