# Private methods in interfaces : 

Beginning with JDK 9, an interface can include a private method. A private interface method 
can be called only by a default method or another private method defined by the same interface. 
Because a private interface method is specified private, it cannot be used by code outside 
the interface in which it is defined. This restriction includes subinterfaces because a private 
interface method is not inherited by a sub interface.The key benefit of a private interface method 
is that it lets two or more default methods use a common piece of code, thus avoiding code duplication.

Using private methods in interfaces have four rules :

Private interface method cannot be abstract.

Private method can be used only inside interface.

Private static method can be used inside other static and non-static interface methods.

Private non-static methods cannot be used inside private static methods.
