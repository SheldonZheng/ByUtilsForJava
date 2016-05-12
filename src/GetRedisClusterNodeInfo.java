import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.BinaryJedisCluster;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;


public class GetRedisClusterNodeInfo {
			public static void main(String[] args) {
				Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
				
				jedisClusterNodes.add(new HostAndPort("192.168.8.108", 7001));
				jedisClusterNodes.add(new HostAndPort("192.168.8.108", 7002));
				jedisClusterNodes.add(new HostAndPort("192.168.8.103", 7001));
				jedisClusterNodes.add(new HostAndPort("192.168.8.103", 7002));
				jedisClusterNodes.add(new HostAndPort("192.168.8.104", 7001));
				jedisClusterNodes.add(new HostAndPort("192.168.8.104", 7002));
				jedisClusterNodes.add(new HostAndPort("192.168.8.105", 7001));
				jedisClusterNodes.add(new HostAndPort("192.168.8.105", 7002));
				
				JedisCluster cluster = new JedisCluster(jedisClusterNodes);
				
				cluster.set("cluster test", "hello jedis cluster!");
		        String value = cluster.get("foo");
		        System.out.println("foo = " + value);
		        
		        cluster.incr("counter");
		        System.out.println("counter = " + cluster.get("counter"));
		        cluster.incr("counter");
		        System.out.println("counter = " + cluster.get("counter"));
		        // get cluster nodes
		        System.out.println("------- cluster nodes --------");
		        
		        Map<String, JedisPool> nodes = cluster.getClusterNodes();
		        Iterator<Map.Entry<String, JedisPool>> iterNodes = nodes.entrySet().iterator();
		        while (iterNodes.hasNext()) 
		        {
		            Map.Entry<String, JedisPool> entry = iterNodes.next();
		            Jedis jedis = entry.getValue().getResource();
		            System.out.println("============");
		            System.out.println(entry.getKey() + "\n" + jedis.info());
		        }
		     
		        System.out.println("------- end --------");
		        
		        try {
					cluster.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
}
