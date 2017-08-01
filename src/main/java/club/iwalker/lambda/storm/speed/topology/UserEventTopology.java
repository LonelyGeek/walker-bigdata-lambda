package club.iwalker.lambda.storm.speed.topology;

import club.iwalker.lambda.storm.speed.spout.UserEventSpout;
import org.apache.storm.topology.TopologyBuilder;

/**
 * Created by wangchen on 2017/6/30.
 */
public class UserEventTopology {
    public static void main(String[] args) {

        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("spout", new UserEventSpout());
    }
}
