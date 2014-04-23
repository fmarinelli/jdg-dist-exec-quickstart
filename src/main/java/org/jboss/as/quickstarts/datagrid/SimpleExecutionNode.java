package org.jboss.as.quickstarts.datagrid;

import org.infinispan.manager.EmbeddedCacheManager;
import org.infinispan.remoting.transport.Address;


public class SimpleExecutionNode {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EmbeddedCacheManager manager = InfinispanUtils.buildInfinispanConfiguration();
		manager.start();
		manager.getCache(); // just to "start" the service
		System.out.println("Cache Started:" + manager.getAddress());
		while ( true ) {
			for (Address add : manager.getMembers() )
				System.out.println(add);
			InfinispanUtils.pause(10000L);
		}
	}
}
