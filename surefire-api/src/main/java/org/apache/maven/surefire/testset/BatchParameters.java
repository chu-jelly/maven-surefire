package org.apache.maven.surefire.testset;

public class BatchParameters
{
    public static BatchParameters EMPTY_PARAMETERS = new BatchParameters(0, 0, false);

    public static class Builder {
        private int numberOfBatches;
        private int batchNumber;
        private boolean batchingEnabled;
        public Builder numberOfBatches(int n) { numberOfBatches = n; return this; }
        public Builder batchNumber(int n) { batchNumber = n; return this; }
        public Builder batchingEnabled(boolean b) {batchingEnabled = b; return this; }
        public BatchParameters build() {
            return new BatchParameters(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private final int numberOfBatches;
    private final int batchNumber;
    private final boolean batchingEnabled;

    private BatchParameters(Builder builder)
    {
        this(builder.numberOfBatches, builder.batchNumber, builder.batchingEnabled);
    }

    private BatchParameters(int numberOfBatches, int batchNumber, boolean batchingEnabled)
    {
        this.numberOfBatches = numberOfBatches;
        this.batchNumber = batchNumber;
        this.batchingEnabled = batchingEnabled;
    }

    public int getNumberOfBatches()
    {
        return numberOfBatches;
    }

    public int getBatchNumber()
    {
        return batchNumber;
    }

    public boolean isBatchingEnabled()
    {
        return batchingEnabled;
    }

    public String toString()
    {
        return "BatchParameters{" +
                "numberOfBatches=" + numberOfBatches +
                ", batchNumber=" + batchNumber +
                ", batchingEnabled=" + batchingEnabled +
                '}';
    }
}
