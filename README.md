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