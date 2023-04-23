package org.apache.flink.configuration;


import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;
import edu.illinois.confuzz.internal.ConfuzzGenerator;

import java.io.*;

public class ConfigurationGenerator extends Generator<Configuration> {
    private static Configuration generatedConf = null;
    private static String setMethodName = "generatorSet";
    private static Class<?>[] setParameterTypes = {String.class, Object.class};

    /**
     * Constructor for Configuration Generator
     */
    public ConfigurationGenerator() throws IOException {
        super(Configuration.class);
    }

    public static Configuration getGeneratedConfig() {
        if (generatedConf == null) {
            return null;
        }
        return new Configuration(generatedConf);
    }

    /**
     * This method is invoked to generate a Configuration object
     *
     * @param random
     * @param generationStatus
     *
     * @return
     */
    @Override
    public Configuration generate(SourceOfRandomness random, GenerationStatus generationStatus) {
        Configuration conf = new Configuration(true);
        try {
            generatedConf = (Configuration) ConfuzzGenerator.generate(
                    random,
                    conf,
                    conf.getClass(),
                    setMethodName,
                    setParameterTypes);
            return generatedConf;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
