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
in the java.util.function package.</h4>

<h3><u>Note:</u></h3><h4>If you want to add a class-level utility method to 
an interface, along with an implementation, make the method static and provide
the implementation in the usual way.</h4>

<h3><u>Note:</u></h3><h4>Utility classes: classes that contain only static 
methods. A typical example is java.util.Collections, which contains methods 
for sorting and searching, wrapping collections in synchronized or unmodifiable 
types, and more.</h4>

<h3><u>Note:</u></h3><h4>In Java 8, you can add static methods to interfaces 
whenever you like. The requirements are:

• Add the static keyword to the method.

• Provide an implementation (which cannot be overridden). In this way they are
like default methods, and are included in the default tab in the Javadocs.

• Access the method using the interface name. Classes do not need to implement
an interface to use its static methods.</h4>

<h3><u>Note:</u></h3><h4>Static methods in interfaces remove the need to create 
separate utility classes, though that option is still available if a design
calls for it.</h4>

<h3><u>Note:</u></h3><h4>The key points to remember are:

• Static methods must have an implementation

• You cannot override a static method

• Call static methods from the interface name

• You do not need to implement an interface to use its static methods</h4>

--------------------------------------------------------------------------

# The java.util.function Package:-

<h3><u>Note:</u></h3><h4>Specifically designed to contain only functional interfaces 
that are reused in the rest of the library.</h4>

</h3><h4>The interfaces in java.util.function fall into four categories: 
(1) consumers, (2) suppliers, (3) predicates, and (4) functions. 
Consumers take a generic argument and return nothing. 
Suppliers take no arguments and return a value.
Predicates take an argument and return a boolean. 
Functions take a single argument and return a value.</h4>

<h3><u>Note:</u></h3><h4>An Optional is a nonnull object that either wraps
a value or is empty. It is typically returned by methods that may reasonably 
expect to have no result, like finding a value in an empty collection.</h4>

--------------------------------------------------------------------------

# The java.util.function Predicates:-

<h3><u>Note:</u></h3><h4>Predicates are used primarily to filter streams.
Given a stream of items, the filter method in java.util.stream.Stream takes 
a Predicate and returns a new stream that includes only the items that satisfy 
the given predicate.</h4>

<h3><u>Note:</u></h3><h4>Other methods in the standard library that use 
predicates include:

• Optional.filter(Predicate<? super T> predicate)
If a value is present, and the value matches the given predicate,
returns an Optional describing the value, otherwise returns an empty Optional.

• Collection.removeIf(Predicate<? super E> filter)
Removes all elements of this collection that satisfy the predicate.

• Stream.allMatch(Predicate<? super T> predicate)
Returns true if all elements of the stream satisfy the given predicate.
The methods anyMatch and noneMatch work similarly.

• Collectors.partitioningBy(Predicate<? super T> predicate)
Returns a Collector that splits a stream into two categories:
those that satisfy the predicate and those that do not.</h4>

<h3><u>Note:</u></h3><h4>Predicates are useful whenever a stream should 
only return certain elements.</h4>

--------------------------------------------------------------------------

# The java.util.function Functions:-

<h3><u>Note:</u></h3><h4>The functional interface java.util.function.Function 
contains the single abstract method apply, which is invoked to transform a 
generic input parameter of type T into a generic output value of type R.</h4>

<h3><u>Note:</u></h3><h4>The most common usage of Function is as an argument 
to the Stream.map method. For example, one way to transform a String into an
integer would be to invoke the length method on each instance.</h4>

<h3><u>Note:</u></h3><h4>The BiFunction interface is defined for two generic 
input types and one generic out‐ put type, all of which are assumed to be different. 
If all three are the same, the pack‐ age includes the BinaryOperator interface.</h4>

--------------------------------------------------------------------------

# Streams:-

<h3><u>Note:</u></h3><h4>A stream is a sequence of elements that does not
save the elements or modify the original source.</h4>

<h3><u>Note:</u></h3><h4>Functional programming in Java often involves 
generating a stream from some source of data, passing the elements through 
a series of intermediate operations (called a pipeline), and completing the 
process with a terminal expression.</h4>

<h3><u>Note:</u></h3><h4>Streams can only be used once. After a stream has 
passed through zero or more intermediate operations and reached a 
terminal operation, it is finished. To process the values again, 
you need to make a new stream.</h4>

<h3><u>Note:</u></h3><h4>The @SafeVarargs annotation is part of Java generics. 
It comes up when you have an array as an argument, because it is possible to
assign a typed array to an Object array and then violate type safety with an 
added element. The @SafeVarargs annotation tells the compiler that the developer
promises not to do that.</h4>

<h3><u>Note:</u></h3><h4>Streams do not process any data until a terminal
expression is reached, as collect or forEach.</h4>

<h3><u>Note:</u></h3><h4>The new java.util.stream.Stream interface in Java 8 
provides several static methods for creating streams. Specifically,
you can use the static methods Stream.of, Stream.iterate, and Stream.generate</h4>

<h3><u>Note:</u></h3><h4>UnaryOperator is a function whose single input 
and output types are the same. This is useful when you have a way to produce 
the next value of the stream from the current value.</h4>

<h3><u>Note:</u></h3><h4>There are three child interfaces of Stream specifically 
for working with primitives: IntStream, LongStream, and DoubleStream. 
IntStream and LongStream each have two additional factory methods for creating
streams, range and rangeClosed.</h4>

<h3><u>Note:</u></h3><h4>rangeClosed includes the end value, and range doesn’t. 
Each returns a sequential, ordered stream that starts at the first argument 
and increments by one after that.</h4>

<h3><u>Note:</u></h3><h4>The boxed() method is necessary for Collectors to convert 
primitives to List<T></h4>

<h3><u>Note:</u></h3><h4>To summarize, here are the methods to create streams:

• Stream.of(T... values) and Stream.of(T t)

• Arrays.stream(T[] array), with overloads for int[], double[], and long[] • Stream.iterate(T seed, UnaryOperator<T> f)

• Stream.generate(Supplier<T> s)

• Collection.stream()

• Using range and rangeClosed:

— IntStream.range(int startInclusive, int endExclusive)

— IntStream.rangeClosed(int startInclusive, int endInclusive) 

— LongStream.range(long startInclusive, long endExclusive)

— LongStream.rangeClosed(long startInclusive, long endInclusive)</h4>

<h3><u>Note:</u></h3><h4>Just as mapToInt, mapToLong, and mapToDouble parse 
streamsof objects into the associated primitives, the mapToObj method 
from IntStream, LongStream, and Double Stream converts primitives to
instances of the associated wrapper classes.</h4>

<h3><u>Note:</u></h3><h4>If you want to produce a single value from stream 
operations, use the reduce method to accumulate calculations on each element.</h4>

<h3><u>Note:</u></h3><h4>The functional paradigm in Java often uses a process
known as map-filter-reduce. The map operation transforms a stream of one type 
(like a String) into another (like an int, by invoking the length method). 
Then a filter is applied to produce a new stream with only the desired 
elements in it (e.g., strings with length below a certain threshold).
Finally, you may wish to provide a terminal operation that generates a 
single value from the stream (like a sum or average of the lengths).</h4>

<h3><u>Note:</u></h3><h4>Reduction operations like sum, count, max, min, 
and average do what you would expect. The only interesting part is that 
some of them return Optionals, because if there are no elements in the 
stream (perhaps after a filtering operation) the result is undefined or null.</h4>

<h3><u>Note:</u></h3><h4>The collect method is used to convert a stream 
into a collection, usually in combination with one of the static helper 
methods in the Collectors class, like toList or toSet. 
That version of collect does not exist on the primitive streams.</h4>

<h3><u>Note:</u></h3><h4>While using concat with reduce works, 
it is inefficient because String concatenation creates and destroys objects. 
A better approach would be to use the collect method with a Collector.</h4>

<h3><u>Note:</u></h3><h4>If you want to check that a sort is correct, 
use the reduce method to check each pair of elements.</h4>

<h3><u>Note:</u></h3><h4>If you want to see the individual elements of a 
stream as they are processed, invoke the peek intermediate operation wherever 
you need it in a stream pipeline.</h4>

<h3><u>Note:</u></h3><h4>According to the Javadocs, the peek method 
“returns a stream consisting of the elements of this stream, additionally 
performing the provided action on each element as they are consumed from 
the resulting stream.” Recall that a Consumer takes a single input but 
returns nothing, so any provided Consumer will not corrupt each value as 
it streams by.</h4>

<h3><u>Note:</u></h3><h4>Since peek is an intermediate operation, 
the peek method can be added multiple times if you wish.</h4>

<h3><u>Note:</u></h3><h4>Unfortunately, there’s no easy way to make the
peek code optional, so this is a convenient step to use for debugging but 
should be removed in production code.</h4>

<h3><u>Note:</u></h3><h4>Rather than loop over individual characters of a 
String, you would like to use the idiomatic Stream processing techniques.
Use the default methods chars and codePoints from the java.lang.CharSequence 
interface to convert a String into an IntStream. To convert back to a String, 
use the overload of the collect method on IntStream that takes a Supplier, 
a BiConsumer representing an accumulator, and a BiConsumer representing a combiner.</h4>

<h3><u>Note:</u></h3><h4>String is not part of the Collections framework, 
and therefore does not implement Iterable, so there is no stream factory method 
to convert one into a Stream.</h4>

<h3><u>Note:</u></h3><h4>Stream methods in java.lang.CharSequence:

— default IntStream chars()

— default IntStream codePoints()

The difference between the two methods has to do with how Java handles 
UTF-16-encoded characters as opposed to the full Unicode set of code points.
</h4>

<h3><u>Note:</u></h3><h4>If you want to know how many elements are in a stream, 
use either the Stream.count or Collectors.counting methods.</h4>

<h3><u>Note:</u></h3><h4>If you want the count, sum, min, max, and average of a 
stream of numerical values, use the summaryStatistics method in IntStream, 
DoubleStream, and LongStream.</h4>

<h3><u>Note:</u></h3><h4>If you wish to find the first element in a stream 
that satisfies a particular condition, use the findFirst or findAny method 
after applying a filter.</h4>

<h3><u>Note:</u></h3><h4>The findFirst and findAny methods in java.util.stream.Stream
return an Optional describing the first element of a stream.
Neither takes an argument, implying that any mapping or filtering 
operations have already been done.</h4>

<h3><u>Note:</u></h3><h4>The findAny method returns an Optional describing 
some element of the stream, or an empty Optional if the stream is empty. 
In this case, the behavior of the operation is explicitly nondeterministic, 
meaning it is free to select any element of the stream. 
This allows optimization in parallel operations.</h4>

<h3><u>Note:</u></h3><h4>The InterruptedException is the only exception in
Java that it is OK to catch and ignore.</h4>

<h3><u>Note:</u></h3><h4>Both findFirst and findAny are short-circuiting, 
terminal operations. A short-circuiting operation may produce a finite stream 
when presented with an infinite one. A terminal operation is short-circuiting 
if it may terminate in finite time even when presented with infinite input.</h4>

<h3><u>Note:</u></h3><h4>Streams are lazy, meaning they will only process 
as many elements as are necessary to satisfy the pipeline.</h4>

<h3><u>Note:</u></h3><h4>The sequential stream only needs to access one element,
which it then returns, short-circuiting the process. The parallel stream 
fires up eight different threads, finds one element, and shuts them all down. 
The parallel stream therefore accesses many values it doesn’t need.</h4>

<h3><u>Note:</u></h3><h4>The key concept is that of encounter order with streams. 
If the stream has an encounter order, then findFirst will always return the 
same value. The findAny method is allowed to return any element, 
making it more appropriate for parallel operations.</h4>

<h3><u>Note:</u></h3><h4>If you wish to determine if any elements in a stream
match a Predicate, or if all match, or if none match, use the methods 
anyMatch, allMatch, and noneMatch on the Stream interface, each of which 
returns a boolean.</h4>


--------------------------------------------------------------------------