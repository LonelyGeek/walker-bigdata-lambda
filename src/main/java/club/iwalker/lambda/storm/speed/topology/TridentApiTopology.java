package club.iwalker.lambda.storm.speed.topology;

import club.iwalker.lambda.storm.speed.trident.DisplayFilter;
import club.iwalker.lambda.storm.speed.trident.Function;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.trident.Stream;
import org.apache.storm.trident.TridentTopology;
import org.apache.storm.trident.testing.FixedBatchSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;

/**
 * Created by wangchen on 2017/7/3.
 */
public class TridentApiTopology {

    private static final Logger LOGGER = LogManager.getLogger(TridentApiTopology.class);


    public static void main(String[] args) throws Exception {


        FixedBatchSpout spout = new FixedBatchSpout(new Fields("a", "b", "c"), 3,
                new Values(new Integer[] {1, 2, 3}),
                new Values(new Integer[] {4, 1, 6}),
                new Values(new Integer[] {3, 0, 8}));
        spout.setCycle(Boolean.TRUE);

        TridentTopology topology = new TridentTopology();

        Stream arrays = topology.newStream("spout-array", spout)
                .each(new Fields("b"), new Function(), new Fields("d"))
                .each(new Fields("a", "b", "c", "d"), new DisplayFilter());

        arrays.project(new Fields("a", "b"));
        Config config = new Config();
        config.setDebug(Boolean.TRUE);
//        StormSubmitter.submitTopology(args[0], config, topology.build());
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("trident-api", config, topology.build());
        Utils.sleep(10000);
        cluster.shutdown();
    }
}
