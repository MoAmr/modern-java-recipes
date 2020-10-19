--------------------------------------------------------------------------

# modern-java-recipes:

--------------------------------------------------------------------------

# Lambda Expressions:-
 
<h3><u>Note:</u></h3><h4>A functional interface is an interface with a single abstract
method (SAM). A class implements any interface by providing implementations 
for all the methods in it. This can be done with a top-level class, an inner
class, or even an anonymous inner class.</h4>

<h3><u>Note:</u></h3><h4>A lambda expression must match the argument types and return
type in the signature of the single abstract method in the interface. 
This is called being compatible with the method signature. The lambda expression
is thus the implementation of the interface method, and can also be assigned to a
reference of that interface type.</h4>

<h3><u>Note:</u></h3><h4>Lambda expressions can only be assigned to
functional interface references.</h4>

<h3><u>Note:</u></h3><h4>Lambda expressions never exist alone. 
There is always a context for the expression, which indicates the 
functional interface to which the expression is assigned.
A lambda can be an argument to a method, a return type from a method,
or assigned to a reference. In each case, the type of the assignment 
must be a functional interface.</h4>

--------------------------------------------------------------------------

# Method References:-

<h3><u>Note:</u></h3><h4>You want to use a method reference to access
an existing method and treat it like a lambda expression. 
Use the double-colon notation to separate an instance reference or class 
name from the method.(((”(double colon) notation in method references”)))</h4>

<h3><u>Note:</u></h3><h4>If you write a lambda expression that consists of 
one line that invokes a method, consider using the equivalent method
reference instead.</h4>

<h3><u>Note:</u></h3><h4>Method references can be used with static 
methods as well.</h4>

<h3><u>Note:</u></h3><h4>There are three forms of the method reference syntax,
and one is a bit misleading:

object::instanceMethod
Refer to an instance method using a reference to the supplied object, as in
System.out::println

Class::staticMethod
Refer to static method, as in Math::max

Class::instanceMethod
Invoke the instance method on a reference to an object supplied by the context,
as in String::length
</h4>

--------------------------------------------------------------------------

# Constructor References:-

<h3><u>Note:</u></h3><h4>Problem:
You want to instantiate an object using a method reference as part of a stream pipe‐ line.

Solution:
Use the new keyword as part of a method reference.</h4>

<h3><u>Note:</u></h3><h4>Constructor references can also be used with arrays.
If you want an array of Person instances, Person[], instead of a list,
you can use the toArray method on Stream, whose signature is:
<A> A[] toArray(IntFunction<A[]> generator)</h4>

<h3><u>Note:</u></h3><h4>The toArray method argument creates an array of 
Person references of the proper size and populates it with the instantiated
Person instances.</h4>

--------------------------------------------------------------------------

# Functional Interfaces:-

<h3><u>Note:</u></h3><h4>If you want want to use an existing functional
interface, or write your own, create an interface with a single,
abstract method, and add the @FunctionalInter face annotation.</h4>

<h3><u>Note:</u></h3><h4>A functional interface in Java 8 is an interface
with a single, abstract method. As such, it can be the target for a lambda
expression or method reference.</h4>

<h3><u>Note:</u></h3><h4>All methods in an interface are public,
so you can leave out the access modifier, just as you can leave out the
abstract keyword.</h4>

<h3><u>Note:</u></h3><h4>Functional interfaces can have default and static
methods as well. Both default and static methods have implementations, 
so they don’t count against the single abstract method requirement.</h4>

<h3><u>Note:</u></h3><h4>The rules for functional interfaces say that
methods from Object don’t count against the single abstract method limit, 
so Comparator is still a functional interface.</h4>

<h3><u>Note:</u></h3><h4>Many of the existing interfaces in Java have been
enhanced with default methods in order to maintain backward compatibility.</h4>

<h3><u>Note:</u></h3><h4> Normally when you add a new method to an interface,
you break all the existing implementations. By adding a new method as a default, 
all the existing implementations inherit the new method and still work.
This allowed the library maintainers to add new default methods throughout
the JDK without breaking existing implementations.</h4>

<h3><u>Note:</u></h3><h4>Predicate is one of the new functional interfaces 
in the java.util.function package,</h4>

--------------------------------------------------------------------------