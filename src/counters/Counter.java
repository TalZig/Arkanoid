package counters;
/**
 * The type Counte.
 */
public class Counter {
    private int counter;

    /**
     * Instantiates a new Counte.
     *
     * @param num the num
     */
    public Counter(int num) {
        this.counter = num;
    }

    /**
     * Increase.
     *
     * @param number the number
     */
// add number to current count.
    public void increase(int number) {
        this.counter = this.counter + number;
    }

    /**
     * Decrease.
     *
     * @param number the number
     */
// subtract number from current count.
    public void decrease(int number) {
        this.counter = this.counter - number;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
// get current count.
    public int getValue() {
        return this.counter;
    }
}