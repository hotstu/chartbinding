package github.hotstu.chartbindingdemo;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import timber.log.Timber;

/**
 * @author hglf
 * @since 2018/10/25
 */
public class ChartMapping<T> {
    public static class PieData {
        public String[] legend;
        public float[] value;
    }
    public static class BarData {
        public String[] legend;
        public float[] value;
    }
    public static class LineData {
        public String[] legend;
        public float[] value;
    }

    public String label;
    public String type;
    public T data;

    public static class Deserializer implements JsonDeserializer<ChartMapping> {
        @Override
        public ChartMapping deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jo = json.getAsJsonObject();
            String type = jo.get("type").getAsString();
            String label = jo.get("label").getAsString();
            ChartMapping mapping = new ChartMapping();
            mapping.label = label;
            mapping.type = type;
            if ("pie".equals(type)) {
                mapping.data = context.deserialize(jo.get("series"), PieData.class);
            } else if("bar".equals(type)) {
                mapping.data = context.deserialize(jo.get("series"), BarData.class);
            } else if("line".equals(type)){
                mapping.data = context.deserialize(jo.get("series"), LineData.class);
            }
            else {
                Timber.w("不支持的类型:" + type);
            }
            return mapping;
        }
    }



}
