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

import java.util.Arrays;
import java.util.Map;

/**
 * Created by wangchen on 2017/7/3.
 */
public class SplitWordBolt extends BaseRichBolt {

    private static final Logger LOGGER = LogManager.getLogger(SplitWordBolt.class);

    private OutputCollector _collector;

    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        _collector = collector;
    }

    @Override
    public void execute(Tuple input) {
        String sentence = input.getStringByField("word");
        LOGGER.debug("receive word：" + sentence);
        Arrays.stream(sentence.split(" ")).forEach(o -> {
            _collector.emit(new Values(o));
        });


    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word"));
    }
}
