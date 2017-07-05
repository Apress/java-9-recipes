var Employee = Java.type("org.java8recipes.chapter18.recipe18_09.Employee");
var bigDecimal = Java.type("java.math.BigDecimal");
var Developer = Java.extend(Employee, {
    grossPay: function(hours, rate){
        var bonus = 500;
        return hours.multiply(rate).add(new bigDecimal(bonus));
    }
});

var javaDev = new Developer();
javaDev.first = "Joe";
javaDev.last = "Dynamic";
print(javaDev + "'s gross pay for the week is: " + javaDev.grossPay(new bigDecimal(60), new bigDecimal(80)));


