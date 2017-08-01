package club.iwalker.lambda.storm.speed.trident;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.storm.trident.operation.BaseFunction;
import org.apache.storm.trident.operation.TridentCollector;
import org.apache.storm.trident.tuple.TridentTuple;
import org.apache.storm.tuple.Values;


/**
 * Created by wangchen on 2017/7/3.
 */
public class Function extends BaseFunction {
    private static final Logger LOGGER = LogManager.getLogger(Function.class);
    @Override
    public void execute(TridentTuple tuple, TridentCollector collector) {
        Integer value = tuple.getInteger(0);
        LOGGER.info("Function Value：" + value);
        for (int i = 0; i < value; i ++) {
            Values values = new Values(i);
            LOGGER.info("values===" + values);
            collector.emit(values);
        }
    }
}
