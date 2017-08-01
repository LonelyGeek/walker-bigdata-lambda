package club.iwalker.lambda.storm.speed.topology;

import club.iwalker.lambda.storm.speed.spout.RandomSentenceSpout;
import club.iwalker.lambda.storm.speed.bolt.SplitWordBolt;
import club.iwalker.lambda.storm.speed.bolt.SumWordBolt;
import org.apache.storm.Config;
import org.apache.storm.StormSubmitter;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

/**
 * Created by wangchen on 2017/7/3.
 */
public class WordCountTopology {

    public static void main(String[] args) throws Exception {
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("spout", new RandomSentenceSpout(), 2);
        builder.setBolt("split-bolt", new SplitWordBolt(), 2).shuffleGrouping("spout");
        builder.setBolt("sum-bolt", new SumWordBolt(), 2).fieldsGrouping("split-bolt", new Fields("word"));
        Config config = new Config();
        config.setDebug(Boolean.TRUE);

//        if (ArrayUtils.isNotEmpty(args)) {
//        if (args != null && args.length > 0) {
            StormSubmitter.submitTopology(args[0], config, builder.createTopology());
        /*}else {
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("word-count", config, builder.createTopology());
            Utils.sleep(10000);
            cluster.shutdown();
        }*/
    }
}
