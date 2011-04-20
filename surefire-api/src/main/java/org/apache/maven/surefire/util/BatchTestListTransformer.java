package org.apache.maven.surefire.util;

import org.apache.maven.surefire.testset.BatchParameters;

import java.util.Collections;
import java.util.List;

/**
 * This transformer creates batches of tests out of the input list.
 * <p/>
 * The input list has to be stable across test invocations otherwise it would be
 * possible to miss tests and to run the same test multiple times.
 */
public class BatchTestListTransformer implements TestListTransformer
{
    private final int numberOfBatches;
    private final int batchNumber;

    public BatchTestListTransformer(BatchParameters batchParameters)
    {
        this(batchParameters.getNumberOfBatches(), batchParameters.getBatchNumber());
    }

    public BatchTestListTransformer(int numberOfBatches, int batchNumber)
    {
        if(numberOfBatches < 0 || batchNumber < 0) throw new IllegalArgumentException("Invalid batch setting. Number of batches and batch number have to be positive");
        this.numberOfBatches = numberOfBatches;
        this.batchNumber = batchNumber;
    }

    public List transformTestList(List testClasses)
    {
        if (testClasses == null || numberOfBatches < 1)
        {
            return Collections.emptyList();
        }
        final int size = testClasses.size();

        int batchSize = Math.max(1, size / numberOfBatches);
        final int from = Math.max(0, (batchNumber - 1) * batchSize);

        // The last batch contains the remainder of tests to run
        if (batchNumber == numberOfBatches)
        {
            batchSize += size % numberOfBatches;
        }
        final int to = Math.min(size, from + batchSize);

        if (from > to)
        {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(testClasses.subList(from, to));
    }
}
