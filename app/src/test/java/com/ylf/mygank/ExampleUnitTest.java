package com.ylf.mygank;

import com.ylf.mygank.rxjava.demo.RXUseDemo;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() throws Exception {
        RXUseDemo rxUseDemo = new RXUseDemo();
        rxUseDemo.test();
        //assertEquals(4, rxUseDemo.getResult(2, 2));
    }

}