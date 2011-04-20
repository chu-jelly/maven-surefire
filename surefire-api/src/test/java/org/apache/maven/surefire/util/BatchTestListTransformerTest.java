package org.apache.maven.surefire.util;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BatchTestListTransformerTest extends TestCase
{
    public void testTransformTestListBatch() throws Exception
    {
        List tests = createTestList(100);
        List batchedTests = new BatchTestListTransformer(5, 1).transformTestList(tests);
        assertEquals(20, batchedTests.size());
        assertEquals("A1", batchedTests.get(0));
        assertEquals("A5", batchedTests.get(4));
        assertEquals("A20", batchedTests.get(19));
    }

    public void testTransformTestListBatchRest() throws Exception
    {
        List tests = createTestList(109);
        TestListTransformer t = new BatchTestListTransformer(5, 1);
        List batchedTests = t.transformTestList(tests);
        assertEquals(21, batchedTests.size());

        t = new BatchTestListTransformer(5, 2);
        batchedTests = t.transformTestList(tests);
        assertEquals(21, batchedTests.size());
        assertEquals("A22", batchedTests.get(0));

        t = new BatchTestListTransformer(5, 3);
        batchedTests = t.transformTestList(tests);
        assertEquals(21, batchedTests.size());
        assertEquals("A43", batchedTests.get(0));

        t = new BatchTestListTransformer(5, 5);
        batchedTests = t.transformTestList(tests);
        assertEquals(25, batchedTests.size());
        assertEquals("A109", batchedTests.get(24));

    }

    public void testTransformTestListSizeSingleBatch() throws Exception
    {
        List tests = createTestList(2);
        List batchedTests = new BatchTestListTransformer(1, 1).transformTestList(tests);
        assertEquals(2, batchedTests.size());
        assertEquals("A1", batchedTests.get(0));
        assertEquals("A2", batchedTests.get(1));
    }


    public void testTransformTestListSizeEmptyBatch() throws Exception
    {
        List tests = createTestList(2);
        TestListTransformer t = new BatchTestListTransformer(0, 1);
        List batchedTests = t.transformTestList(tests);
        assertEquals(0, batchedTests.size());
    }

    public void testTransformTestListEmptyListIfBatchExceedsSize() throws Exception
    {
        List tests = createTestList(2);
        TestListTransformer t = new BatchTestListTransformer(5, 1);
        List batchedTests = t.transformTestList(tests);
        assertEquals(1, batchedTests.size());

        t = new BatchTestListTransformer(5, 2);
        batchedTests = t.transformTestList(tests);
        assertEquals(1, batchedTests.size());

        t = new BatchTestListTransformer(5, 3);
        batchedTests = t.transformTestList(tests);
        assertEquals(0, batchedTests.size());

        t = new BatchTestListTransformer(5, 5);
        batchedTests = t.transformTestList(tests);
        assertEquals(0, batchedTests.size());

        t = new BatchTestListTransformer(5, 20);
        batchedTests = t.transformTestList(tests);
        assertEquals(0, batchedTests.size());
    }

    public void testTransformTestListInvalidArgumentsNegativeBatchNumber() throws Exception
    {
        try {
            new BatchTestListTransformer(5, -1);
            fail("Negative batch number shouldn't work.");
        } catch (IllegalArgumentException iae) {
            // expected
        }
    }


    public void testTransformTestListInvalidArgumentsNegativeBatchCount() throws Exception
    {
        try {
            new BatchTestListTransformer(-5, 1);
            fail("Negative batch count shouldn't work.");
        } catch (IllegalArgumentException iae) {
            // expected
        }
    }

    private static List createTestList(int n)
    {
        List tests = new ArrayList(n);
        for (int i = 1; i <= n; i++)
        {
            tests.add("A" + i);
        }
        return Collections.unmodifiableList(tests);
    }
}
