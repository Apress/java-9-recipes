var somePosition = new org.java8recipes.chapter18.recipe18_08.PositionType({
  hourlyWage: function(hours, wage){
      return hours * wage;
  }
});

print(somePosition instanceof Java.type("org.java8recipes.chapter18.recipe18_08.PositionType"));
var bigDecimal = Java.type("java.math.BigDecimal");

print(somePosition.hourlyWage(new bigDecimal(40), new bigDecimal(12.75)));
