package club.iwalker.lambda.storm.speed.bolt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangchen on 2017/7/3.
 */
public class SumWordBolt extends BaseRichBolt {

    private static final Logger LOGGER = LogManager.getLogger(SplitWordBolt.class);


    Map<String, Integer> counts = new HashMap<>();

    private OutputCollector _collector;

    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        _collector = collector;
    }

    @Override
    public void execute(Tuple input) {
        String word = input.getStringByField("word");
        if (counts.containsKey(word)) {
            counts.put(word, (counts.get(word) + 1));
        }else {
            counts.put(word, 1);
        }
        _collector.emit(new Values(word, counts.get(word)));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word", "count"));
    }
}
