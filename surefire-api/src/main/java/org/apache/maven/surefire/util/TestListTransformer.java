package org.apache.maven.surefire.util;

import java.util.List;

public interface TestListTransformer
{

    /**
     * Return a list of test classes. This allows us to modify the list of test classes before they get executed.
     *
     * @param testClasses the list of classes to test
     * @return List<Class>, never returns null
     */
    List transformTestList(List testClasses);

}
