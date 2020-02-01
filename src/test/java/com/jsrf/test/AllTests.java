package com.jsrf.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * 一次运行多个测试
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        JunitTestStudy.class,
        ParameterizedTestFields.class })

public class AllTests {

}