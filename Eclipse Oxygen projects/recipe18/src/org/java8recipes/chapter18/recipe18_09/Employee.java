

package org.java8recipes.chapter18.recipe18_09;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Juneau
 */
public class Employee {
    private int age;
    private String first;
    private String last;
    private String position;
    private Date hireDate;
    
    public Employee(){
        
    }
    
    public Employee(String first,
                    String last,
                    Date hireDate){
        this.first = first;
        this.last = last;
        this.hireDate = hireDate;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the first
     */
    public String getFirst() {
        return first;
    }

    /**
     * @param first the first to set
     */
    public void setFirst(String first) {
        this.first = first;
    }

    /**
     * @return the last
     */
    public String getLast() {
        return last;
    }

    /**
     * @param last the last to set
     */
    public void setLast(String last) {
        this.last = last;
    }

    /**
     * @return the position
     */
    public String getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * @return the hireDate
     */
    public Date getHireDate() {
        return hireDate;
    }

    /**
     * @param hireDate the hireDate to set
     */
    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }
    
    public String toString(){
        return first + " " + last;
    }
    
    public BigDecimal grossPay(BigDecimal hours, BigDecimal rate){
        return hours.multiply(rate);
    }
}
