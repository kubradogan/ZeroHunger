package com.zerohunger;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Service 1: Soil Monitor Service (Unary RPC)
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.63.0)",
    comments = "Source: ClimateControl.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class SoilMonitorServiceGrpc {

  private SoilMonitorServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "zerohunger.SoilMonitorService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.zerohunger.SensorRequest,
      com.zerohunger.SoilStatusResponse> getCheckSoilMoistureMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CheckSoilMoisture",
      requestType = com.zerohunger.SensorRequest.class,
      responseType = com.zerohunger.SoilStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.zerohunger.SensorRequest,
      com.zerohunger.SoilStatusResponse> getCheckSoilMoistureMethod() {
    io.grpc.MethodDescriptor<com.zerohunger.SensorRequest, com.zerohunger.SoilStatusResponse> getCheckSoilMoistureMethod;
    if ((getCheckSoilMoistureMethod = SoilMonitorServiceGrpc.getCheckSoilMoistureMethod) == null) {
      synchronized (SoilMonitorServiceGrpc.class) {
        if ((getCheckSoilMoistureMethod = SoilMonitorServiceGrpc.getCheckSoilMoistureMethod) == null) {
          SoilMonitorServiceGrpc.getCheckSoilMoistureMethod = getCheckSoilMoistureMethod =
              io.grpc.MethodDescriptor.<com.zerohunger.SensorRequest, com.zerohunger.SoilStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CheckSoilMoisture"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zerohunger.SensorRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zerohunger.SoilStatusResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SoilMonitorServiceMethodDescriptorSupplier("CheckSoilMoisture"))
              .build();
        }
      }
    }
    return getCheckSoilMoistureMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SoilMonitorServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SoilMonitorServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SoilMonitorServiceStub>() {
        @java.lang.Override
        public SoilMonitorServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SoilMonitorServiceStub(channel, callOptions);
        }
      };
    return SoilMonitorServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SoilMonitorServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SoilMonitorServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SoilMonitorServiceBlockingStub>() {
        @java.lang.Override
        public SoilMonitorServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SoilMonitorServiceBlockingStub(channel, callOptions);
        }
      };
    return SoilMonitorServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SoilMonitorServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SoilMonitorServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SoilMonitorServiceFutureStub>() {
        @java.lang.Override
        public SoilMonitorServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SoilMonitorServiceFutureStub(channel, callOptions);
        }
      };
    return SoilMonitorServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Service 1: Soil Monitor Service (Unary RPC)
   * </pre>
   */
  public interface AsyncService {

    /**
     */
    default void checkSoilMoisture(com.zerohunger.SensorRequest request,
        io.grpc.stub.StreamObserver<com.zerohunger.SoilStatusResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckSoilMoistureMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service SoilMonitorService.
   * <pre>
   * Service 1: Soil Monitor Service (Unary RPC)
   * </pre>
   */
  public static abstract class SoilMonitorServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return SoilMonitorServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service SoilMonitorService.
   * <pre>
   * Service 1: Soil Monitor Service (Unary RPC)
   * </pre>
   */
  public static final class SoilMonitorServiceStub
      extends io.grpc.stub.AbstractAsyncStub<SoilMonitorServiceStub> {
    private SoilMonitorServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SoilMonitorServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SoilMonitorServiceStub(channel, callOptions);
    }

    /**
     */
    public void checkSoilMoisture(com.zerohunger.SensorRequest request,
        io.grpc.stub.StreamObserver<com.zerohunger.SoilStatusResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckSoilMoistureMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service SoilMonitorService.
   * <pre>
   * Service 1: Soil Monitor Service (Unary RPC)
   * </pre>
   */
  public static final class SoilMonitorServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<SoilMonitorServiceBlockingStub> {
    private SoilMonitorServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SoilMonitorServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SoilMonitorServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.zerohunger.SoilStatusResponse checkSoilMoisture(com.zerohunger.SensorRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckSoilMoistureMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service SoilMonitorService.
   * <pre>
   * Service 1: Soil Monitor Service (Unary RPC)
   * </pre>
   */
  public static final class SoilMonitorServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<SoilMonitorServiceFutureStub> {
    private SoilMonitorServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SoilMonitorServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SoilMonitorServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.zerohunger.SoilStatusResponse> checkSoilMoisture(
        com.zerohunger.SensorRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckSoilMoistureMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CHECK_SOIL_MOISTURE = 0;

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
        case METHODID_CHECK_SOIL_MOISTURE:
          serviceImpl.checkSoilMoisture((com.zerohunger.SensorRequest) request,
              (io.grpc.stub.StreamObserver<com.zerohunger.SoilStatusResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getCheckSoilMoistureMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.zerohunger.SensorRequest,
              com.zerohunger.SoilStatusResponse>(
                service, METHODID_CHECK_SOIL_MOISTURE)))
        .build();
  }

  private static abstract class SoilMonitorServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SoilMonitorServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.zerohunger.ZeroHungerProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SoilMonitorService");
    }
  }

  private static final class SoilMonitorServiceFileDescriptorSupplier
      extends SoilMonitorServiceBaseDescriptorSupplier {
    SoilMonitorServiceFileDescriptorSupplier() {}
  }

  private static final class SoilMonitorServiceMethodDescriptorSupplier
      extends SoilMonitorServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    SoilMonitorServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (SoilMonitorServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SoilMonitorServiceFileDescriptorSupplier())
              .addMethod(getCheckSoilMoistureMethod())
              .build();
        }
      }
    }
    return result;
  }
}
