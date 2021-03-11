package rest.client.bug;

import javax.json.Json;
import javax.json.JsonStructure;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.serializer.JsonbSerializer;
import javax.json.bind.serializer.SerializationContext;
import javax.json.stream.JsonGenerator;
import javax.ws.rs.ext.ContextResolver;

public class JsonConfig implements ContextResolver<Jsonb> {
  @Override
  public Jsonb getContext(Class<?> type) {
    JsonbConfig config = new JsonbConfig();
    config.withSerializers(new MySerializer1());
    // config.withSerializers(new MySerializer2());
    return JsonbBuilder.create(config);

  }

  public abstract static class MyBaseSerializer<T> implements JsonbSerializer<T> {
    @Override
    public void serialize(T obj, JsonGenerator generator, SerializationContext ctx) {
      JsonStructure json = Json.createObjectBuilder() /**/
          .add("1111", "1").add("2222", "2").build();
      generator.write(json);
    }
  }

  public static class MySerializer1 extends MyBaseSerializer<TypeFromSomeLib> implements JsonbSerializer<TypeFromSomeLib> {
  }

  public static class MySerializer2 extends MyBaseSerializer<TypeFromSomeLib> {
  }
}
