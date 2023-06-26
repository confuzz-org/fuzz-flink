package org.apache.flink;

import org.apache.flink.configuration.ConfigOption;
import org.apache.flink.configuration.ConfigOptions;
import org.apache.flink.configuration.Configuration;
import java.time.Duration;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestDebug {

    @Test
    public void test() throws Exception {
        Configuration conf = new Configuration();
        int count = 0;
        String name = conf.getString("myname", "default");
        String age = conf.getString("myage", "0");
        System.out.println("name: " + name + ", age: " + age);
        String str = conf.getString("fs.s3a.select.output.csv.quote.fields", "null");
        String str2 = conf.getString("hadoop.http.staticuser.user", "null");
        System.out.println(str2);
        conf.setString("fake2", "200");
        conf.setString("fake3", "300");
        assertEquals("200", conf.getString("fake2", "null"));
        //System.out.println(str);
        if (str.equals("always")) {
            System.out.println("always");
            count ++;
            // This should fail if the fuzzer is not specificed with -DexpectedException=java.lang.AssertionError
            //assertEquals("300", conf.getString("fake3", "null"));
        } else if (str.equals("asneeded")) {
            System.out.println("asneeded");
            conf.setString("fake-config1","15");
            count --;
            throw new Exception("Fake Bug asneeded");
        } else {
            System.out.println(str);
        }
        //System.out.println("Conf Length : " + conf.size());

	/**
        ConfigOption<String> stringOption =
                ConfigOptions.key("stringOption").stringType().defaultValue("my-beautiful-default");
        assertEquals("my-beautiful-default", conf.get(stringOption));

        ConfigOption<Integer> intOption =
                ConfigOptions.key("intOption").intType().defaultValue(42);
        assertEquals(42, conf.get(intOption).intValue());

        ConfigOption<Long> longOption =
                ConfigOptions.key("longOption").longType().defaultValue(42L);
        assertEquals(42L, conf.get(longOption).longValue());

        ConfigOption<Float> floatOption =
                ConfigOptions.key("floatOption").floatType().defaultValue(42.0f);
        assertEquals(42.0f, conf.get(floatOption).floatValue(), 0.0f);

        ConfigOption<Double> doubleOption =
                ConfigOptions.key("doubleOption").doubleType().defaultValue(42.0);
        assertEquals(42.0, conf.get(doubleOption).doubleValue(), 0.0);

        ConfigOption<Boolean> booleanOption =
                ConfigOptions.key("booleanOption").booleanType().defaultValue(true);
        assertEquals(true, conf.get(booleanOption).booleanValue());

        ConfigOption<String> stringOption2 =
                ConfigOptions.key("stringOption2").stringType().noDefaultValue();
        assertEquals(null, conf.get(stringOption2));

        ConfigOption<Duration> classOption =
                ConfigOptions.key("classOption").durationType().defaultValue(Duration.ofSeconds(42));
		assertEquals(Duration.ofSeconds(42), conf.get(classOption)); */
    }
	
}
