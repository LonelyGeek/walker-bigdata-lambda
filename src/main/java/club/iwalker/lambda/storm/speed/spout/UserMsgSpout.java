package club.iwalker.lambda.storm.speed.spout;

import org.apache.storm.task.TopologyContext;
import org.apache.storm.trident.operation.TridentCollector;
import org.apache.storm.trident.spout.IBatchSpout;
import org.apache.storm.tuple.Fields;

import java.util.Map;

/**
 * Created by wangchen on 2017/6/30.
 */
public class UserMsgSpout implements IBatchSpout {
    @Override
    public void open(Map conf, TopologyContext context) {

    }

    @Override
    public void emitBatch(long batchId, TridentCollector collector) {

    }

    @Override
    public void ack(long batchId) {

    }

    @Override
    public void close() {

    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }

    @Override
    public Fields getOutputFields() {
        return null;
    }
}
