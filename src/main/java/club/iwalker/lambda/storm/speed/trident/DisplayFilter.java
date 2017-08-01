package club.iwalker.lambda.storm.speed.trident;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.storm.trident.operation.BaseFilter;
import org.apache.storm.trident.tuple.TridentTuple;

/**
 * Created by wangchen on 2017/7/5.
 */
public class DisplayFilter extends BaseFilter {

    private static final Logger LOGGER = LogManager.getLogger(DisplayFilter.class);

    @Override
    public boolean isKeep(TridentTuple tuple) {
        LOGGER.info("======Filter=====" + tuple.toString());
        LOGGER.info("======Filter1=====" + tuple.get(0));
        return false;
    }
}
