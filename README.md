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
