package org.apache.maven.surefire.util;

import java.util.Collections;
import java.util.List;

public class IdentityTestListTransformer implements TestListTransformer
{
    public List transformTestList(List testClasses)
    {
        return testClasses == null ? Collections.emptyList() : testClasses;
    }
}
