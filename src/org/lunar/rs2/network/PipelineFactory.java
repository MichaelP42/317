package org.lunar.rs2.network;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;

/**
 * A factory of pipline's.
 * 
 * @author Michael P
 *
 */
public final class PipelineFactory implements ChannelPipelineFactory {

	/*
	 * (non-Javadoc)
	 * @see org.jboss.netty.channel.ChannelPipelineFactory#getPipeline()
	 */
	@Override
	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline =  Channels.pipeline();
		pipeline.addLast("handler", new ChannelHandler());
		return pipeline;
	}

}
