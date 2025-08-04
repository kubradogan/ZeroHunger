package com.zerohunger;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Service 2: Weather Forecast Service (Server-streaming RPC)
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.63.0)",
    comments = "Source: ClimateControl.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class WeatherForecastServiceGrpc {

  private WeatherForecastServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "zerohunger.WeatherForecastService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.zerohunger.RegionRequest,
      com.zerohunger.WeatherResponse> getGetForecastMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetForecast",
      requestType = com.zerohunger.RegionRequest.class,
      responseType = com.zerohunger.WeatherResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.zerohunger.RegionRequest,
      com.zerohunger.WeatherResponse> getGetForecastMethod() {
    io.grpc.MethodDescriptor<com.zerohunger.RegionRequest, com.zerohunger.WeatherResponse> getGetForecastMethod;
    if ((getGetForecastMethod = WeatherForecastServiceGrpc.getGetForecastMethod) == null) {
      synchronized (WeatherForecastServiceGrpc.class) {
        if ((getGetForecastMethod = WeatherForecastServiceGrpc.getGetForecastMethod) == null) {
          WeatherForecastServiceGrpc.getGetForecastMethod = getGetForecastMethod =
              io.grpc.MethodDescriptor.<com.zerohunger.RegionRequest, com.zerohunger.WeatherResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetForecast"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zerohunger.RegionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zerohunger.WeatherResponse.getDefaultInstance()))
              .setSchemaDescriptor(new WeatherForecastServiceMethodDescriptorSupplier("GetForecast"))
              .build();
        }
      }
    }
    return getGetForecastMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static WeatherForecastServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<WeatherForecastServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<WeatherForecastServiceStub>() {
        @java.lang.Override
        public WeatherForecastServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new WeatherForecastServiceStub(channel, callOptions);
        }
      };
    return WeatherForecastServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static WeatherForecastServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<WeatherForecastServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<WeatherForecastServiceBlockingStub>() {
        @java.lang.Override
        public WeatherForecastServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new WeatherForecastServiceBlockingStub(channel, callOptions);
        }
      };
    return WeatherForecastServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static WeatherForecastServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<WeatherForecastServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<WeatherForecastServiceFutureStub>() {
        @java.lang.Override
        public WeatherForecastServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new WeatherForecastServiceFutureStub(channel, callOptions);
        }
      };
    return WeatherForecastServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Service 2: Weather Forecast Service (Server-streaming RPC)
   * </pre>
   */
  public interface AsyncService {

    /**
     */
    default void getForecast(com.zerohunger.RegionRequest request,
        io.grpc.stub.StreamObserver<com.zerohunger.WeatherResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetForecastMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service WeatherForecastService.
   * <pre>
   * Service 2: Weather Forecast Service (Server-streaming RPC)
   * </pre>
   */
  public static abstract class WeatherForecastServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return WeatherForecastServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service WeatherForecastService.
   * <pre>
   * Service 2: Weather Forecast Service (Server-streaming RPC)
   * </pre>
   */
  public static final class WeatherForecastServiceStub
      extends io.grpc.stub.AbstractAsyncStub<WeatherForecastServiceStub> {
    private WeatherForecastServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WeatherForecastServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new WeatherForecastServiceStub(channel, callOptions);
    }

    /**
     */
    public void getForecast(com.zerohunger.RegionRequest request,
        io.grpc.stub.StreamObserver<com.zerohunger.WeatherResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getGetForecastMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service WeatherForecastService.
   * <pre>
   * Service 2: Weather Forecast Service (Server-streaming RPC)
   * </pre>
   */
  public static final class WeatherForecastServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<WeatherForecastServiceBlockingStub> {
    private WeatherForecastServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WeatherForecastServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new WeatherForecastServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<com.zerohunger.WeatherResponse> getForecast(
        com.zerohunger.RegionRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getGetForecastMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service WeatherForecastService.
   * <pre>
   * Service 2: Weather Forecast Service (Server-streaming RPC)
   * </pre>
   */
  public static final class WeatherForecastServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<WeatherForecastServiceFutureStub> {
    private WeatherForecastServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WeatherForecastServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new WeatherForecastServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_GET_FORECAST = 0;

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
        case METHODID_GET_FORECAST:
          serviceImpl.getForecast((com.zerohunger.RegionRequest) request,
              (io.grpc.stub.StreamObserver<com.zerohunger.WeatherResponse>) responseObserver);
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
          getGetForecastMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              com.zerohunger.RegionRequest,
              com.zerohunger.WeatherResponse>(
                service, METHODID_GET_FORECAST)))
        .build();
  }

  private static abstract class WeatherForecastServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    WeatherForecastServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.zerohunger.ZeroHungerProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("WeatherForecastService");
    }
  }

  private static final class WeatherForecastServiceFileDescriptorSupplier
      extends WeatherForecastServiceBaseDescriptorSupplier {
    WeatherForecastServiceFileDescriptorSupplier() {}
  }

  private static final class WeatherForecastServiceMethodDescriptorSupplier
      extends WeatherForecastServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    WeatherForecastServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (WeatherForecastServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new WeatherForecastServiceFileDescriptorSupplier())
              .addMethod(getGetForecastMethod())
              .build();
        }
      }
    }
    return result;
  }
}
