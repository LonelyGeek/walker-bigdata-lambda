package club.iwalker.lambda.storm.speed.bolt;

import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;

/**
 * Created by wangchen on 2017/6/30.
 */
public class UserEventEntityBolt extends BaseBasicBolt {
    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }
}
