package club.iwalker.lambda.storm.speed.topology;

import org.apache.storm.trident.TridentState;
import org.apache.storm.trident.TridentTopology;
import org.apache.storm.trident.operation.builtin.Count;
import org.apache.storm.trident.operation.builtin.FilterNull;
import org.apache.storm.trident.operation.builtin.MapGet;
import org.apache.storm.trident.operation.builtin.Sum;
import org.apache.storm.trident.testing.FixedBatchSpout;
import org.apache.storm.trident.testing.MemoryMapState;
import org.apache.storm.trident.testing.Split;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

/**
 * Created by wangchen on 2017/7/3.
 */
public class WordCountTridentTopology {
    public static void main(String[] args) {


        FixedBatchSpout spout = new FixedBatchSpout(new Fields("sentence"), 3,
                new Values("the cow jumped over the moon"),
                new Values("the man went to the store and bought some candy"),
                new Values("four score and seven years ago"),
                new Values("how many apples can you eat"));

        spout.setCycle(Boolean.TRUE);

        TridentTopology topology = new TridentTopology();
        TridentState wordCounts = topology.newStream("spout1", spout)
                .each(new Fields("sentence"), new Split(), new Fields("word"))
                .groupBy(new Fields("word"))
                .persistentAggregate(new MemoryMapState.Factory(), new Count(), new Fields("count"))
                .parallelismHint(6);


        topology.newDRPCStream("words")
                .each(new Fields("args"), new Split(), new Fields("word"))
                .groupBy(new Fields("word"))
                .stateQuery(wordCounts, new Fields("word"), new MapGet(), new Fields("count"))
                .each(new Fields("count"), new FilterNull())
                .aggregate(new Fields("count"), new Sum(), new Fields("sum"));



        /*TridentState urlToTweeters = topology.newStaticState(getUrlToTweetersState());

        TridentState tweetersToFollowers = topology.newStaticState(getTweeterToFollowersState());

        topology.newDRPCStream("reach")
                .stateQuery(urlToTweeters, new Fields("args"), new MapGet(), new Fields("tweeters"))
                .each(new Fields("tweeters"), new ExpandList(), new Fields("tweeter"))
                .shuffle()
                .stateQuery(tweetersToFollowers, new Fields("tweeter"), new MapGet(), new Fields("followers"))
                .parallelismHint(200)
                .each(new Fields("followers"), new ExpandList(), new Fields("follower"))
                .groupBy(new Fields("follower"))
                .aggPartition(new One(), new Fields("one"))
                .parallelismHint(20)
                .aggregate(new Count(), new Fields("reach"));*/


    }

}
