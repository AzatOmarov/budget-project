package de.budget.project.exceptions;

public class UnknownCurrencyCodeException extends Exception {
    // Reason for exception
    private String reason = null;

    /**
     * Create a new unknown currency code exception.
     *
     * @param reason for the exception
     */
    public UnknownCurrencyCodeException(String reason) {
        this.reason = reason;
    }

    /**
     * Return the reason this exception was raised.
     *
     * @return the reason why the string isn't a valid currency code
     */
    public String getReason() {
        return reason;
    }

    /**
     * Convert the exception to a string
     *
     * @return string version of the exception
     */
    public String toString() {
        return getReason();
    }
}
