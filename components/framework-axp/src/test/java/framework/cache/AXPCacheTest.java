package framework.cache;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import static org.testng.Assert.fail;

public class AXPCacheTest
{

  private ICache cache;

  @Test
  public void testPut()
  {
    cache = new AXPCache(true);
    cache.put("key1", "value1");
    cache.put("key2", "value2");
    cache.put("key3", "value3");

    Assertions.assertThat((String) cache.get("key1")).isEqualTo("value1");
    Assertions.assertThat((String) cache.get("key2")).isEqualTo("value2");
    Assertions.assertThat((String) cache.get("key3")).isEqualTo("value3");
  }

  @Test
  public void testPut_withNullKey()
  {
    cache = new AXPCache(true);
    cache.put(null, "value1");
    cache.put("key2", "value2");
    cache.put("key3", "value3");

    Assertions.assertThat(cache.size()).isEqualTo(2);
  }

  @Test
  public void testPut_withNullValue()
  {
    cache = new AXPCache(true);
    cache.put("key1", "value1");
    cache.put("key2", "value2");
    cache.put("key3", "value3");

    //Remove inserted value
    cache.put("key1", null);

    Assertions.assertThat(cache.size()).isEqualTo(2);
    Assertions.assertThat((String) cache.get("key1")).isNull();
    Assertions.assertThat((String) cache.get("key2")).isEqualTo("value2");
    Assertions.assertThat((String) cache.get("key3")).isEqualTo("value3");
  }

  @Test
  public void testGet_withTTL()
  {
    try
    {
      Thread t = new Thread(() -> {
        cache = new AXPCache(100);
        cache.put("key1", "value1");
        cache.put("key2", "value2");
        cache.put("key3", "value3");

        try
        {
          //Sleep until cache get cleared
          Thread.sleep(120);
        }
        catch (InterruptedException e)
        {
          e.printStackTrace();

          fail();
        }
        Assertions.assertThat(cache.get("key1")).isNull();
        Assertions.assertThat(cache.get("key2")).isNull();
        Assertions.assertThat(cache.get("key3")).isNull();
      });
      t.start();
      t.join();
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();

      fail();
    }
  }

  @Test
  public void testPut_withTTL()
  {
    try
    {
      Thread t = new Thread(() -> {
        cache = new AXPCache(true);
        cache.put("key1", "value1", 100);
        cache.put("key2", "value2", 100);
        cache.put("key3", "value3", 100);

        try
        {
          //Sleep until cache get cleared
          Thread.sleep(120);
        }
        catch (InterruptedException e)
        {
          e.printStackTrace();

          fail();
        }
        Assertions.assertThat(cache.get("key1")).isNull();
        Assertions.assertThat(cache.get("key2")).isNull();
        Assertions.assertThat(cache.get("key3")).isNull();
      });
      t.start();
      t.join();
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();

      fail();
    }
  }

  @Test
  public void testRemove()
  {
    cache = new AXPCache(true);
    cache.put("key1", "value1", 100);
    cache.put("key2", "value2", 100);
    cache.put("key3", "value3", 100);

    cache.remove("key1");

    Assertions.assertThat(cache.get("key1")).isNull();
    Assertions.assertThat(cache.get("key2")).isEqualTo("value2");
    Assertions.assertThat(cache.get("key3")).isEqualTo("value3");
  }

  @Test
  public void testClear()
  {
    cache = new AXPCache(true);
    cache.put("key1", "value1", 100);
    cache.put("key2", "value2", 100);
    cache.put("key3", "value3", 100);

    cache.clear();

    Assertions.assertThat(cache.get("key1")).isNull();
    Assertions.assertThat(cache.get("key2")).isNull();
    Assertions.assertThat(cache.get("key3")).isNull();
  }

  @Test
  public void testSize()
  {
    cache = new AXPCache(true);
    cache.put("key1", "value1", 100);
    cache.put("key2", "value2", 100);
    cache.put("key3", "value3", 100);

    Assertions.assertThat(cache.size()).isEqualTo(3);
  }
}