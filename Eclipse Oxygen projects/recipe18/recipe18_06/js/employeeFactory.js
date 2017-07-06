var oldDate = Java.type("java.util.Date");
var array = Java.type("java.util.ArrayList");
var emp = Java.type("org.java8recipes.chapter18.recipe18_06.Employee");

var empArray = new array();
var emp1 = new emp("Josh", "Juneau", new oldDate());
var emp2 = new emp("Joe", "Blow", new oldDate());
empArray.add(emp1);
empArray.add(emp2);
// Access the array by index
print(empArray[0]);

empArray.forEach(function(employee, index, ar){
    print("Employee: " + employee);
    print("Hire Date: " + employee.hireDate);
});


