package com.icode.concurrency.example.publish;

import com.icode.concurrency.annoations.NotThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

@NotThreadSafe
public class UnsafePublish {

    private static final Logger logger = LoggerFactory.getLogger(UnsafePublish.class);

    private String[] states = {"a", "b", "c"};

    public String[] getStates(){
        return states;
    }

    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();
        logger.info("{}", Arrays.toString(unsafePublish.getStates()));

        unsafePublish.getStates()[0] = "d";
        logger.info("{}", Arrays.toString(unsafePublish.getStates()));
    }
}
