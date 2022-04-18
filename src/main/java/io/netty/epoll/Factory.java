package io.netty.epoll;

import io.micronaut.http.netty.channel.EventLoopGroupConfiguration;
import io.micronaut.http.netty.channel.NettyThreadFactory;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.util.concurrent.DefaultThreadFactory;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

import java.util.concurrent.ThreadFactory;

@io.micronaut.context.annotation.Factory
public class Factory {

    @Inject()
    @Named(NettyThreadFactory.NAME)
    private ThreadFactory nioThreadFactory;

    @Singleton
    @Named(EventLoopGroupConfiguration.DEFAULT)
    public ThreadFactory getThreadFactory(EventLoopGroupConfiguration configuration){
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxx : "+Epoll.isAvailable());
        if(Epoll.isAvailable()){
            return new DefaultThreadFactory(configuration.getName() + "-" + DefaultThreadFactory.toPoolName(EpollEventLoopGroup.class));
        }else return nioThreadFactory;
    }
}
