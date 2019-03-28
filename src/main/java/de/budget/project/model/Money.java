package de.budget.project.model;

import de.budget.project.exceptions.UnknownCurrencyCodeException;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.math.RoundingMode.HALF_UP;

public class Money implements Serializable {

    /**
     * Why me
     */
    private static final int[] cents = new int[]{1, 10, 100, 1000};

    private BigDecimal amount;

    private Currency currency;

    //private MathContext DEFAULT_CONTEXT = new MathContext( 2, HALF_UP );

    private MathContext DEFAULT_CONTEXT = new MathContext( 10, RoundingMode.HALF_DOWN );

    public Money(long amount, Currency currency) {
        this.currency = currency;
        this.amount = BigDecimal.valueOf(amount, currency.getDefaultFractionDigits());
    }

    /**
     * Creates a currency object from the long value provided assuming the long value
     * represents the base currency in the least monetary unit. For eg, new Money(500, "GBP")
     * is assumed to mean 5.00 great british pounds
     * @param amount in base monetary unit
     * @param currCode
//     * @throws com.console.core.exceptions.UnknownCurrencyCodeException
     */
    public Money(long amount, String currCode) throws UnknownCurrencyCodeException {
        this( amount, Currency.getInstance(currCode) );
    }

    /**
     * Construct an IMMUTABLE money object from a double. It is assumed that
     * the whole part of the double is the Money with the fractional part representing
     * lowest denominator of the currency. For eg, new Money (50.99, "GBP") is assumed
     * to be 50 pounds and 99 pence.
     * PS. 89.788 will be truncated to 89.78 based on the defaultcurrencydigit of the currency
     * @param amount
     * @param curr
     */
    public Money(double amount, Currency curr) {
        this.currency = curr;
        BigDecimal bd = BigDecimal.valueOf( amount );
        this.amount = bd.setScale(centFactor(), HALF_UP);
    }

    private Money() {
    }

    /**
     * Construct an IMMUTABLE money object from a double. It is assumed that
     * the whole part of the double is the Money with the fractional part representing
     * lowest denominator of the currency. For eg, new Money (50.99, "GBP") is assumed
     * to be 50 pounds and 99 pence.
     * PS. 89.788 will be truncated to 89.78 based on the defaultcurrencydigit of the currency
     * code supplied
     * @param amount
     * @param currCode iso 4217 currency code
//     * @throws com.console.core.exceptions.UnknownCurrencyCodeException
     */
    public Money(double amount, String currCode) throws UnknownCurrencyCodeException {
        this.currency = Currency.getInstance(currCode);
        BigDecimal bd = BigDecimal.valueOf( amount );
        this.amount = bd.setScale( currency.getDefaultFractionDigits(), HALF_UP);
    }

    /**
     * Constructs an IMMUTABLE money from a BigDecimal. the BigDecimal provided is only scaled
     * to used the default digits in currency object represented by the sting parameter
     * @param bigDecimal
     * @param currCode ISO 4217 cuurency code
//     * @throws com.console.core.exceptions.UnknownCurrencyCodeException
     */
    public Money(BigDecimal bigDecimal, String currCode ) throws UnknownCurrencyCodeException {
        this.currency = Currency.getInstance(currCode);
        this.amount = bigDecimal.setScale( currency.getDefaultFractionDigits(), HALF_UP);
    }

    /**
     * Constructs an IMMUTABLE money from a BigDecimal. the BigDecimal provided is only scaled
     * to used the default digits in currency object represented by the sting parameter
//     * @param multiply
     * @param currency
     */
    public Money(BigDecimal bigDecimal, Currency currency) {
        this.currency = currency;
        this.amount = bigDecimal.setScale( currency.getDefaultFractionDigits(), HALF_UP);
    }

    //  public  boolean assertSameCurrencyAs(Money arg) {
//    return  this.currency.getCurrencyCode().equals(arg.currency.getCurrencyCode());
//  }
//
//    public boolean assertSameCurrencyAs(Money money) throws IncompatibleCurrencyException{
//        if ( this.currency == null ) {
//            throw new IncompatibleCurrencyException( "currency.invalid" );
//        }
//        if ( money == null ) {
//            throw new IncompatibleCurrencyException( "currency.invalid" );
//        }
//        Assert.assertEquals("money math mismatch", currency, money.currency);
//        return true;
//    }

    private int centFactor() {
        return cents[ getCurrency().getDefaultFractionDigits() ];
    }

    public BigDecimal amount() {
        return amount;
    }

    public long amountAsLong(){
        return amount.unscaledValue().longValue();
    }

    public Currency getCurrency() {
        return currency;
    }
    //    common currencies
    public static Money dollars(double amount) {
        Money result = null;
        try {
            result = new Money(amount, "USD");
        } catch (UnknownCurrencyCodeException ex) {
            Logger.getLogger(Money.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static Money dollars(long amount) {
        Money result = null;
        try {
            result = new Money(amount, "USD");
        } catch (UnknownCurrencyCodeException ex) {
            Logger.getLogger(Money.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static Money pounds(double amount) {
        Money result = null;
        try {
            result = new Money(amount, "GBP");
        } catch (UnknownCurrencyCodeException ex) {
            Logger.getLogger(Money.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static Money pounds(long amount) {
        Money result = null;
        try {
            result = new Money(amount, "GBP");
        } catch (UnknownCurrencyCodeException ex) {
            Logger.getLogger(Money.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static Money pounds(BigDecimal amount) {
        Money result = null;
        try {
            result = new Money(amount, "GBP");
        } catch (UnknownCurrencyCodeException ex) {
            Logger.getLogger(Money.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }


    @Override
    public int hashCode() {
        int hash = (int) ( amount.hashCode() ^ (amount.hashCode() >>> 32) );
        return hash;
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof Money && equals((Money) other));
    }

    public boolean equals(Money other) {
        return ( currency.equals(other.currency) && (amount.equals(other.amount)) );
    }

    public Money add(Money other) throws Exception{
//        assertSameCurrencyAs( other );
        return newMoney(amount.add(other.amount, DEFAULT_CONTEXT));
    }

    private int compareTo(Money money) throws Exception {
//        assertSameCurrencyAs( money );
        return amount.compareTo( money.amount );
    }

    public Money multiply(BigDecimal amount) {
        return new Money( this.amount().multiply(amount, DEFAULT_CONTEXT), currency);
    }

    public Money multiply( BigDecimal amount, RoundingMode roundingMode ) {
        MathContext ct = new MathContext( currency.getDefaultFractionDigits(), roundingMode );
        return new Money( amount().multiply(amount, ct), currency);
    }

    private Money newMoney(BigDecimal amount) {
        return new Money( amount, this.currency );
    }

    public Money multiply(double amount) {
        return multiply( new BigDecimal( amount ) );
    }

    public Money subtract(Money other) throws Exception {
//        assertSameCurrencyAs(other);
        return newMoney( amount.subtract(other.amount, DEFAULT_CONTEXT) );
    }

    public int compareTo(Object other) throws Exception {
        return compareTo((Money) other);
    }

    public boolean greaterThan(Money other)throws Exception {
        return (compareTo(other) > 0);
    }

//  public Money[] allocate(int n){
//    Money lowResult = newMoney( amount.unscaledValue().longValue()/n );
//    Money highResult = newMoney(lowResult.amount + 1);
//    Money[] results = new Money[n];
//    int remainder = (int) amount % n;
//
//    for(int i = 0; i < remainder; i++)results[i] = highResult;
//    for(int i = 0; i < n; i++) results[i] = lowResult;
//
//    return results;
//  }
//
//  public Money[]allocate(long[] ratios){
//    long total = 0;
//    for (int i = 0; i < ratios.length; i++) {
//      total += ratios[i];
//    }
//    long remainder = amount;
//    Money[] results = new Money[ratios.length];
//    for (int i = 0; i < results.length; i++) {
//      results[i] = newMoney(amount * ratios[i]/total);
//      remainder -= results[i].amount;
//    }
//    for (int i = 0; i < remainder; i++) {
//      results[i].amount++;
//    }
//    return results;
//
//  }

    public Money divideByNumber( double divisor){
        BigDecimal div = BigDecimal.valueOf( divisor );
        BigDecimal ans = this.amount.divide(div, DEFAULT_CONTEXT);
        return new Money(ans, this.currency);
    }

    public int getQuotient( Money divisor ){
        BigDecimal ans = this.amount.divide(divisor.amount, RoundingMode.DOWN);
        return ans.intValue();
    }

    /**
     * divides toe moneys and return the quotient and Remainder this method has been customised,
     * for my money transfer needs...sorry
     * @param divisor
     * @return
     */
    public int[] getQuotientandRemainder(Money divisor){
        int[] ans = new int[2];
        BigDecimal[] bdArr = this.amount.divideAndRemainder(divisor.amount, DEFAULT_CONTEXT);
        BigDecimal quo = bdArr[0];
        BigDecimal rem = bdArr[1];
        ans[0] = quo.intValue();
        if( rem.compareTo(BigDecimal.ZERO) == 0 ){
            ans[1] =0;
        }else{
            ans[1] = 1;
        }
        return ans;
    }

    public String toFormattedString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        nf.setCurrency( currency );
        nf.setGroupingUsed( true );
        nf.setMaximumFractionDigits( currency.getDefaultFractionDigits() );
        return nf.format( this.amount.doubleValue() );
    }

    /**
     * Returns the ISO-4217 currency code of the currency
     * attached to this money.
     *
     * @return The ISO-4217 currency code.
     */
    public String getCurrencyCode() {
        return currency.getCurrencyCode();
    }

    @Override
    public String toString() {
        return amount.toString();
    }

    /**
     * Returns the precision for this money. The precision is the total number
     * of digits that the value can represent. This includes the integer part.
     * So, 18 would be able to represent:
     *

     * 1234567890.12345678
     *

     * 1234567890123456.78
     *

     * 123456789012345678
     *

     * 0.123456789012345678
     *
     * @return The precision.
     */
    public int precision() {
        return amount.precision();
    }

    /**
     * Returns the 'scale' for this money. The scale is the number of
     * digits that are moved to the fractional part, assuming that all
     * digits are represented by a single integer value. For example:
     *

     * If: 123456789012345678 has scaling 2, it would be :
     *

     * 1234567890123456.78
     *
     * @return The scale value.
     */
    public int scale() {
        return amount.scale();
    }

    /**
     * Returns the sign for the money (negative or positive).
     * -1 if negative, 0 if 0.00 (zero), 1 if positive.
     *
     * @return The sign of the money.
     */
    public int signum() {
        return amount.signum();
    }

//    public static void main(String[] args) throws Exception {
//        Money cash = Money.pounds(20.2);
//        Money result = cash.multiply(3);
//        System.out.println("result="+result.toFormattedString());
//        Money dollars = Money.dollars(10.02);
//        Money res2 = cash.add(Money.pounds(200));
//        System.out.println("result="+res2.toFormattedString());
//
//    }
}

