--------------------------------------------------------------------------

# modern-java-recipes:

--------------------------------------------------------------------------

## Lambda Expressions:-
 
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

## Method References:-

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

```java
System.out::println
```

Class::staticMethod
Refer to static method, as in Math::max

Class::instanceMethod
Invoke the instance method on a reference to an object supplied by the context,
as in String::length
</h4>

--------------------------------------------------------------------------

## Constructor References:-

<h3><u>Note:</u></h3><h4>Problem:
You want to instantiate an object using a method reference as part of a stream pipe‐ line.

Solution:
Use the new keyword as part of a method reference.</h4>

<h3><u>Note:</u></h3><h4>Constructor references can also be used with arrays.
If you want an array of Person instances, Person[], instead of a list,
you can use the toArray method on Stream, whose signature is:

```java
<A> A[] toArray(IntFunction<A[]> generator)
```
</h4>

<h3><u>Note:</u></h3><h4>The toArray method argument creates an array of 
Person references of the proper size and populates it with the instantiated
Person instances.</h4>

--------------------------------------------------------------------------

## Functional Interfaces:-

<h3><u>Note:</u></h3><h4>If you want want to use an existing functional
interface, or write your own, create an interface with a single,
abstract method, and add the
 
```java
@FunctionalInter 
``` 
face annotation.</h4>

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

## The java.util.function Package:-

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

## The java.util.function Predicates:-

<h3><u>Note:</u></h3><h4>Predicates are used primarily to filter streams.
Given a stream of items, the filter method in java.util.stream.Stream takes 
a Predicate and returns a new stream that includes only the items that satisfy 
the given predicate.</h4>

<h3><u>Note:</u></h3><h4>Other methods in the standard library that use 
predicates include:
 
```java
Optional.filter(Predicate<? super T> predicate)
```
If a value is present, and the value matches the given predicate,
returns an Optional describing the value, otherwise returns an empty Optional.

```java
Collection.removeIf(Predicate<? super E> filter)
```
Removes all elements of this collection that satisfy the predicate.

```java 
Stream.allMatch(Predicate<? super T> predicate)
```
Returns true if all elements of the stream satisfy the given predicate.
The methods anyMatch and noneMatch work similarly.
 
```java 
Collectors.partitioningBy(Predicate<? super T> predicate)
```
Returns a Collector that splits a stream into two categories:
those that satisfy the predicate and those that do not.</h4>

<h3><u>Note:</u></h3><h4>Predicates are useful whenever a stream should 
only return certain elements.</h4>

--------------------------------------------------------------------------

## The java.util.function Functions:-

<h3><u>Note:</u></h3><h4>The functional interface java.util.function.Function 
contains the single abstract method apply, which is invoked to transform a 
generic input parameter of type T into a generic output value of type R.</h4>

<h3><u>Note:</u></h3><h4>The most common usage of Function is as an argument 
to the Stream.map method. For example, one way to transform a String into an
integer would be to invoke the length method on each instance.</h4>

<h3><u>Note:</u></h3><h4>The BiFunction interface is defined for two generic 
input types and one generic output type, all of which are assumed to be different. 
If all three are the same, the package includes the BinaryOperator interface.</h4>

--------------------------------------------------------------------------

## Streams:-

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

<h3><u>Note:</u></h3><h4>The annotation

```java
@SafeVarargs
 ```
is part of Java generics. 
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

```java
Stream.of(T... values) and Stream.of(T t)
```

```java
Arrays.stream(T[] array), with overloads for int[], double[], and long[] 
```

```java
Stream.iterate(T seed, UnaryOperator<T> f)
```

```java
Stream.generate(Supplier<T> s)
```

```java
Collection.stream()
```

```java
Using range() and rangeClosed()
```

```java
IntStream.range(int startInclusive, int endExclusive)
```

```java
IntStream.rangeClosed(int startInclusive, int endInclusive) 
```

```java
LongStream.range(long startInclusive, long endExclusive)
```

```java
LongStream.rangeClosed(long startInclusive, long endInclusive)
```
</h4>

<h3><u>Note:</u></h3><h4>Just as mapToInt, mapToLong, and mapToDouble parse 
streams of objects into the associated primitives, the mapToObj method 
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

```java
default IntStream chars()
```

```java
default IntStream codePoints()
```

The difference between the two methods has to do with how Java handles 
UTF-16-encoded characters as opposed to the full Unicode set of code points.
</h4>

<h3><u>Note:</u></h3><h4>If you want to know how many elements are in a stream, 
use either the
 
```java
Stream.count or Collectors.counting
```
methods.</h4>

<h3><u>Note:</u></h3><h4>If you want the count, sum, min, max, and average of a 
stream of numerical values, use the summaryStatistics method in IntStream, 
DoubleStream, and LongStream.</h4>

<h3><u>Note:</u></h3><h4>If you wish to find the first element in a stream 
that satisfies a particular condition, use the 

```java
findFirst or findAny 
```
methods after applying a filter.</h4>

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

<h3><u>Note:</u></h3><h4>The allMatch and noneMatch methods return true 
and the anyMatch method returns false on an empty stream regardless of the 
supplied predicate. Any supplied predicate is not evaluated when the stream 
is empty.</h4>

<h3><u>Note:</u></h3><h4>If you have a stream and you need to transform the 
elements in some way, but you’re not sure whether to use map or flatMap, 
use map if each element is transformed into a single value. 
Use flatMap if each element will be transformed to multiple values and the 
resulting stream needs to be “flattened.”</h4>

<h3><u>Note:</u></h3><h4>Both the map and the flatMap methods on Stream 
take a Function as an argument. 

The signature for map is:

```java
<R> Stream<R> map(Function<? super T,? extends R> mapper)
```

A Function takes a single input and transforms it into a single output. 
In the case of map, a single input of type T is transformed into a single 
output of type R. </h4>

<h3><u>Note:</u></h3><h4>A map operation is done when there is a one-to-one 
relationship between the input parameter and the output type.</h4>

<h3><u>Note:</u></h3><h4>The flatMap method has the following signature:

```java
<R> Stream<R> flatMap(Function<? super T,? extends Stream<? extends R>> mapper)
```

For each generic argument T, the function produces a Stream<R> rather than 
just an R. The flatMap method then “flattens” the resulting stream by 
removing each element from the individual streams and adding them to the output.</h4>

<h3><u>Note:</u></h3><h4>The Function argument to flatMap takes a generic 
input argument, but produces a Stream of output types.</h4>

<h3><u>Note:</u></h3><h4>The two key concepts for flatMap are:

• The Function argument to flatMap produces a Stream of output values.

• The resulting stream of streams is flattened into a single stream of results.

If you keep those ideas in mind, you should find the flatMap method quite helpful.</h4>

<h3><u>Note:</u></h3><h4>If you want to combine two or more streams into 
a single one, the concat method on Stream combines two streams, which works 
if the number of streams is small. Otherwise use flatMap.</h4>

<h3><u>Note:</u></h3><h4>Say you acquire data from several locations, 
and you want to process every element in all of them using streams. 
One mechanism you can use is the concat method in Stream, whose signature is:

```java
static <T> Stream<T> concat(Stream<? extends T> a, Stream<? extends T> b)
```

This method creates a lazily concatenated stream that accesses all the 
elements of the first stream, followed by all the elements of the second stream.</h4>

<h3><u>Note:</u></h3><h4>As the Javadocs say, the resulting stream is 
ordered if the input streams are ordered, and the resulting stream is 
parallel if either of the input streams are parallel. Closing the returned 
stream also closes the underlying input streams.</h4>

<h3><u>Note:</u></h3><h4>Both input streams must hold elements of the same type.</h4>

<h3><u>Note:</u></h3><h4>Use caution when constructing streams from repeated 
concatenation. Accessing an element of a deeply concatenated stream can 
result in deep call chains, or even StackOver flowException.
The idea is that the concat method essentially builds a binary tree of 
streams, which can grow unwieldy if too many are used.</h4>

<h3><u>Note:</u></h3><h4>If you want to process the minimum number of stream 
elements necessary to satisfy a condition, then use Lazy Streams.
Streams are already lazy and do not process elements until a terminal 
condition is reached. Then each element is processed individually. 
If there is a short-circuiting operation at the end, the stream processing 
will terminate whenever all the conditions are satisfied.</h4>

<h3><u>Note:</u></h3><h4>If you want to sort objects, use the sorted method 
on Stream with a Comparator, either implemented with a lambda expression 
or generated by one of the static compare methods on the Comparator 
interface.</h4>

--------------------------------------------------------------------------

## Comparators and Collectors:-

<h3><u>Note:</u></h3><h4>If you want to sort objects, use the sorted method
on Stream with a Comparator, either implemented with a lambda expression or
generated by one of the static compare methods on the Comparator interface.</h4>

<h3><u>Note:</u></h3><h4>The sorted method on Stream produces a new, sorted 
stream using the natural ordering for the class. The natural ordering is 
specified by implementing the 

```java
java.util.Comparable interface
```

</h4>

<h3><u>Note:</u></h3><h4>Comparator provides a default method called 

```java
thenComparing
```
 
Just like comparing, it also takes a Function as an argument, again known 
as a keyExtractor.</h4>

<h3><u>Note:</u></h3><h4>After stream processing, if you want to convert 
to a List, Set, or other linear collection, use the toList, toSet, 
or toCollection methods in the Collectors utility class.</h4>

<h3><u>Note:</u></h3><h4>The collect method in Stream has two overloaded versions:

```java
<R,A> R collect(Collector<? super T,A,R> collector) 
<R>   R collect(Supplier<R>supplier,
                BiConsumer<R,? super T> accumulator,
                BiConsumer<R,R> combiner)
```
</h4>

<h3><u>Note:</u></h3><h4>The Java 8 API frequently uses a static method called 
of as a factory method.</h4>

<h3><u>Note:</u></h3><h4>If you wish to specify a particular data structure, 
you should use the Collectors.toCollection method, which takes a Supplier 
as an argument.</h4>

<h3><u>Note:</u></h3><h4>The Collectors class also contains a method to 
create an array of objects. There are two overloads of the toArray method:

```java
Object[] toArray();
<A> A[] toArray(IntFunction<A[]> generator);
```
The former returns an array containing the elements of this stream, 
but without specifying the type. The latter takes a function that produces
a new array of desired type with length equal to the size of the stream,
and is easiest to use with an array constructor reference. </h4>

<h3><u>Note:</u></h3><h4>If you want to add a collection of objects to a Map, 
where the key is one of the object properties and the value is the object
itself, use the toMap method of Collectors, along with Function.identity.</h4>

<h3><u>Note:</u></h3><h4>If you want to sort a Map by key or by value,
use the new static methods in the Map.Entry interface.</h4>

<h3><u>Note:</u></h3><h4>The sorted method on Stream produces a new, 
sorted stream that does not modify the source. The original Map is unaffected.</h4>

<h3><u>Note:</u></h3><h4>If you want to divide a collection of elements 
into categories, use the Collectors.partitioningBy method to split elements 
into those that satisfy a Predicate and those that do not. 
The Collectors.groupingBy method produces a Map of categories, where the 
values are the elements in each category.</h4>

<h3><u>Note:</u></h3><h4>The signature of the two partitioningBy methods are:

```java
static <T> Collector<T,?,Map<Boolean,List<T>>> partitioningBy(
        Predicate<? super T> predicate)
static <T,D,A> Collector<T,?,Map<Boolean,D>> partitioningBy( 
        Predicate<? super T> predicate, Collector<? super T,A,D> downstream)
```

The signature for the groupingBy method is:

```java
static <T,K> Collector<T,?,Map<K,List<T>>> groupingBy(
Function<? super T,? extends K> classifier)
```

The groupingBy method performs an operation like a “group by” statement 
in SQL. It returns a Map where the keys are the groups and the values are 
lists of elements in each group.
</h4>

<h3><u>Note:</u></h3><h4>If you want to postprocess the collections returned 
by a groupingBy or partitioningBy operation, use one of the static utility 
methods from the java.util.stream.Collectors class.</h4>

<h3><u>Note:</u></h3><h4>The purpose of a downstream collector is to 
postprocess the collection of objects produced by an upstream operation, 
like partitioning or grouping.</h4>

<h3><u>Note:</u></h3><h4>If you want to determine the maximum or minimum 
value in a stream, you have several choices: the maxBy and minBy methods 
on BinaryOperator, the max and min methods on Stream, or the maxBy and 
minBy utility methods on Collectors.</h4>

<h3><u>Note:</u></h3><h4>If you want to create an immutable list, set, 
or map using the Stream API, use the new static method collectingAndThen 
in the Collectors class.</h4>

<h3><u>Note:</u></h3><h4>Functional programming favors using immutable 
objects wherever possible.</h4>

<h3><u>Note:</u></h3><h4>Creating Immutable Collections using Stream API,
can be replaced with a very simple set of factory methods: List.of, Set.of, 
and Map.of with Java 9.</h4>

<h3><u>Note:</u></h3><h4>If you need to implement java.util.stream.Collector 
manually, because none of the factory methods in the java.util.stream.Collectors 
class give you exactly what you need, provide lambda expressions or method 
references for the Supplier, accumulator, combiner, and finisher functions 
used by the Collector.of factory methods, along with any desired characteristics.</h4>

<h3><u>Note:</u></h3><h4>

* A Supplier is used to create the container where temporary results are accumulated.
 
* A BiConsumer adds a single element to the accumulator. 

* A BinaryOperator means that both input types and the output type are the same.

</h4>

<h3><u>Note:</u></h3><h4>Inside a lambda expression, if you want to access 
a variable defined outside it. Local variables accessed inside lambda 
expressions must be final or “effectively final.” Attributes can be both 
accessed and modified.</h4>

<h3><u>Note:</u></h3><h4>Code in inner classes can access and modify the 
private attributes of the outer class.</h4>

<h3><u>Note:</u></h3><h4>Technically, a function along with the accessible 
variables defined in its environment is called a closure. By that definition, 
Java is in somewhat of a gray area—local variables are accessible but cannot
be modified. You could argue that Java 8 lambdas are actually closures, 
in that they are closed over values rather than variables.</h4>

<h3><u>Note:</u></h3><h4>You can access local variables but not modifying them.</h4>

<h3><u>Note:</u></h3><h4>You can’t invoke 

```java
collect(Collectors.toList())
```

on a collection of primitives.</h4>

<h3><u>Note:</u></h3><h4>If you want to add or replace elements in a Map
only if they already exist or are absent, or other related operations, 
use one of the many new default methods in the 

```java
java.util.Map interface
```

like 

```java
computeIfAbsent, computeIfPresent, replace, merge
```

and so on.</h4>

<h3><u>Note:</u></h3><h4>The replace method works like the put method,
but only if the key already exists. If not, the replace method does nothing.</h4>

<h3><u>Note:</u></h3><h4>The getOrDefault method solves the occasionally 
annoying fact that calling get on a Map with a key that doesn’t exist
returns null. That’s helpful, but the method only returns the default, 
it doesn’t also add it to the map.</h4>

<h3><u>Note:</u></h3><h4>If you have a class that implements two interfaces, 
each of which contains the same default method with different implementations, 
implement the method in your class. Your implementation can still use the 
provided defaults from the interfaces through the 

```java
super 
```

keyword.</h4>

<h3><u>Note:</u></h3><h4>Default methods provide an implementation, which is
then inherited by the class. This allows interfaces to add new methods without
breaking existing class implementations.</h4>

<h3><u>Note:</u></h3><h4>If you want to iterate over a collection or map,
use the 

```java
forEach method
```

which was added as a default method to both Iterable and Map.
From the Javadocs, its signature is:

```java
default void forEach(Consumer<? super T> action)
```

* The argument to forEach is of type Consumer, one of the functional interfaces 
added to the java.util.function package. 

* A Consumer represents an operation that takes a single generic parameter 
and returns no result. As the docs say, “unlike most other functional interfaces,
Consumer is expected to operate via side effects.”

</h4>

<h3><u>Note:</u></h3><h4>A pure function operates without side effects, 
so applying the function with the same parameters always gives the 
same result. In functional programming, this is known as "referential transparency:,
where a function can be replaced by its corresponding value.</h4>

<h3><u>Note:</u></h3><h4>The Map interface also has a forEach method, 
added as a default. In this case, the signature takes a BiConsumer:

```java
default void forEach(BiConsumer<? super K, ? super V> action)
```

* BiConsumer is another of the new interfaces in the java.util.function package. 
It represents a function that takes two generic arguments and returns void.

</h4>

<h3><u>Note:</u></h3><h4>If you want to create a log message, but only if 
the log level ensures it will be seen, use the new logging overloads in 
the Logger class that take a Supplier.</h4>

<h3><u>Note:</u></h3><h4>Overloaded logging methods java.util.logging.Logger:

```java
void config(String msg)
void config(Supplier<String> msgSupplier)

void fine(String msg)
void fine(Supplier<String> msgSupplier)

void finer(String msg)
void finer(Supplier<String> msgSupplier)

void finest(String msg)
void finest(Supplier<String> msgSupplier)

void info(String msg)
void info(Supplier<String> msgSupplier)

void warning(String msg)
void warning(Supplier<String> msgSupplier)

void severe(String msg)
void severe(Supplier<String> msgSupplier)
```
</h4>

<h3><u>Note:</u></h3><h4>Converting the log argument to a Supplier by 
simply adding () -> in front of it means that the get method on the 
Supplier will only be invoked if the message will be used.</h4>

<h3><u>Note:</u></h3><h4>The technique of replacing an argument with a 
Supplier of the same type is known as "deferred execution", and can be used 
in any context where object creation might be expensive.</h4>

<h3><u>Note:</u></h3><h4>If you want to apply a series of small, independent 
functions consecutively, use the composition methods defined as defaults 
in the Function, Consumer, and Predicate interfaces.</h4>

<h3><u>Note:</u></h3><h4>Composition methods in the Predicate interface:

```java
default Predicate<T>    and(Predicate<? super T> other)
default Predicate<T>    negate()
default Predicate<T>    or(Predicate<? super T> other)
```

</h4>

<h3><u>Note:</u></h3><h4>If you have a lambda expression that throws a 
checked exception, and the abstract method in the functional interface you 
are implementing does not declare that exception, add a try/catch block to 
the lambda expression, or delegate to an extracted method to handle it.</h4>

<h3><u>Note:</u></h3><h4>If you have a lambda expression that throws an 
exception, but you wish to use a generic wrapper that catches all checked 
exceptions and rethrows them as unchecked, create special exception classes 
and add a generic method to accept them and return lambdas without exceptions.</h4>

--------------------------------------------------------------------------

## The Optional Type:-

<h3><u>Note:</u></h3><h4>The Java 8 API introduced a new class called 

```java
java.util.Optional<T>
```

</h4>

<h3><u>Note:</u></h3><h4>Optional is designed to communicate to the user 
when a returned value may legitimately be null. This situation can arise 
whenever a stream of values is filtered by some condition that happens to 
leave no elements remaining.</h4>

<h3><u>Note:</u></h3><h4>In the Stream API, the following methods return 
an Optional if no elements remain in the stream:

```java
reduce, min, max, findFirst, findAny
```

</h4>

<h3><u>Note:</u></h3><h4>An instance of Optional can be in one of two states:

* A reference to an instance of type T.

* Empty. 

The former case is called present, and the latter is known as empty
(as opposed to null).</h4>

<h3><u>Note:</u></h3><h4>While Optional is a reference type, it should never
be assigned a value of null. Doing so is a serious error.</h4>

<h3><u>Note:</u></h3><h4>If you need to return an Optional from an existing
value, use 

```java
optional.of, Optional.ofNullable, or Optional.empty
```

</h4>

<h3><u>Note:</u></h3><h4>Like many other new classes in the Java 8 API, 
instances of Optional are immutable.
The API refers to Optional as a value-based class, meaning instances:

* Are final and immutable (though they may contain references to mutable objects).

* Have no public constructors, and thus must be instantiated by factory methods.

* Have implementations of equals, hashCode, and toString that are based only on their state.

</h4>

<h3><u>Note:</u></h3><h4>Instances of Optional are immutable, but the objects
they wrap may not be. If you create an Optional that contains an instance 
of a mutable object, you can still modify the instance.</h4>

<h3><u>Note:</u></h3><h4>You can’t modify the Optional instance itself,
because there are no methods available to do so.</h4>

<h3><u>Note:</u></h3><h4>The static factory methods to create an Optional are:

```java
empty, of, and ofNullable
```

whose signatures are:

```java
static <T> Optional<T> empty()
static <T> Optional<T> of(T value)
static <T> Optional<T> ofNullable(T value)
```

</h4>

<h3><u>Note:</u></h3><h4>The empty method returns, naturally enough, 
an empty Optional. The of method returns an Optional that wraps the specified
value or throws an exception if the argument is null.</h4>

<h3><u>Note:</u></h3><h4>Incidentally, the classes OptionalInt, OptionalLong, 
and OptionalDouble wrap primitives that can never be null, so they only have 
an of method:

```java
static OptionalInt of(int value) 
static OptionalLong of(longvalue) 
static OptionalDouble of(double value)
```

Instead of get, the getter methods on those classes are getAsInt,
getAsLong, and getAsDouble.</h4>

<h3><u>Note:</u></h3><h4>If you want to extract a contained value from an
Optional, use the get method, but only if you’re sure a value exists inside 
the Optional. Otherwise use one of the variations of orElse. You can also 
use ifPresent if you only want to execute a Consumer when a value is present.</h4>

<h3><u>Note:</u></h3><h4>You should never call get on an Optional unless 
you’re sure it contains a value or you risk throwing NoSuchElementException.</h4>

<h3><u>Note:</u></h3><h4>The orElse method returns the contained value if 
one is present, or a supplied default otherwise. It’s therefore a convenient 
method to use if you have a fallback value in mind.</h4>

<h3><u>Note:</u></h3><h4>There are a few variations of orElse:

```java
orElse(T other) 
```

returns the value if present, otherwise it returns the default value, other.

```java
orElseGet(Supplier<? extends T> other) 
```

returns the value if present, otherwise it invokes the Supplier and returns the result.

```java
orElseThrow(Supplier<? extends X> exceptionSupplier) 
```

returns the value if present, otherwise throws the exception created by the Supplier.

</h4>

<h3><u>Note:</u></h3><h4>The difference between orElse and orElseGet is that 
the former returns a string that is always created, whether the value exists 
in the Optional or not, while the latter uses a Supplier, which is only 
executed if the Optional is empty.</h4>

<h3><u>Note:</u></h3><h4>Using a Supplier as a method argument is an example
of _deferred_ or _lazy execution_. It allows you to avoid invoking the get 
method on the Supplier until necessary.</h4>

<h3><u>Note:</u></h3><h4>If you want to avoid wrapping an Optional inside
another Optional, use the flatMap method in Optional.</h4>

<h3><u>Note:</u></h3><h4>The signature of the flatMap method in Optional is:

```java
<U> Optional<U> flatMap(Function<? super T, Optional<U>> mapper)
```

</h4>

<h3><u>Note:</u></h3><h4>If you want to apply a function to a collection of
Optional instances, but only if they contain a value, use the map method 
of Optional.</h4>

--------------------------------------------------------------------------

## File I/O

<h3><u>Note:</u></h3><h4>All the methods in the Files class are static.</h4>

<h3><u>Note:</u></h3><h4>If you want to process the contents of a text file 
using streams, use the static lines method in either java.io.BufferedReader
or java.nio .file.Files to return the contents of a file as a stream.</h4>

<h3><u>Note:</u></h3><h4>If your source of data is not a File, the BufferedReader 
class also has a lines method.</h4>

<h3><u>Note:</u></h3><h4>since Stream implements AutoCloseable, when the 
try-with-resources block closes the stream, it will then close the 
underlying BufferedReader.</h4>

<h3><u>Note:</u></h3><h4>If you want to process all the files in a directory
as a Stream, use the static Files.list method.</h4>

<h3><u>Note:</u></h3><h4>Executing the method Files.list on a non-directory 
resource results in a NotDirectoryException.</h4>

<h3><u>Note:</u></h3><h4>If you need to perform a depth-first traversal of 
the filesystem, use the Files.walk method.</h4>

<h3><u>Note:</u></h3><h4>If you want to find files in a file tree that satisfy
given properties, use the static Files.find method in the java.nio.file package.</h4>

--------------------------------------------------------------------------

## The java.time Package:-

[Date-Time library](https://docs.oracle.com/javase/tutorial/datetime/TOC.html)

<h3><u>Note:</u></h3><h4>If you want to use the new date and time classes 
in the java.time package, work with the factory methods in classes like 
Instant, Duration, Period, LocalDate, LocalTime, LocalDateTime, 
ZonedDateTime, and others.</h4>

<h3><u>Note:</u></h3><h4>The classes in Date-Time all produce immutable 
instances, so they are thread safe. They also do not have public constructors, 
so each is instantiated using factory methods.</h4>

<h3><u>Note:</u></h3><h4>The month field in all the of methods is overloaded 
to accept a Month enum, like Month.JANUARY, or an integer that starts at 1. 
Since integer constants in Calendar start at 0 (that is, Calendar.JANUARY is 0),
watch out for off-by-one errors. Use the Month enum wherever possible.</h4>

<h3><u>Note:</u></h3><h4>There are two types of zone IDs:

* Fixed offsets, relative to UTC/Greenwich, like -05:00

* Geographical regions, like America/Chicago.

</h4>

<h3><u>Note:</u></h3><h4>You can get the current value of the ZoneId from 
the static systemDefault method. The complete list of available region IDs 
comes from the static getAvailableZoneIds method:

```java
Set<String> regionNames = ZoneId.getAvailableZoneIds();
System.out.println("There are " + regionNames.size() + " region names");
```

</h4>

<h3><u>Note:</u></h3><h4>Date-Time **is** method the **immutable** equivalent 
of a **set** method.</h4>

<h3><u>Note:</u></h3><h4> 

```java
ZonedDateTime.withZoneSameInstant()
```

method allows you to take one Zoned DateTime and find out what it would be 
in another time zone.</h4>

<h3><u>Note:</u></h3><h4>There are two enums in the package: Month and DayOfWeek.
Month has constants for each month in the standard calendar (JANUARY through
DECEMBER). Month also has many convenient methods.</h4>

<h3><u>Note:</u></h3><h4>Because the java.time classes are immutable,
any instance method that seems to modify one, like plus, minus, or with, 
produces a new instance.</h4>

<h3><u>Note:</u></h3><h4>The DayOfWeek enum has constants representing the 
seven weekdays, from MONDAY through SUNDAY. Again the int value for each 
follows the ISO standard, so that MONDAY is 1 and SUNDAY is 7.</h4>

<h3><u>Note:</u></h3><h4>If you want to modify an existing instance of one
of the Date-Time classes, if you need a simple addition or subtraction, 
use one of the plus or minus methods. Otherwise use the with method.</h4>

<h3><u>Note:</u></h3><h4>One of the features of the new Date-Time API is 
that all of the instances are immutable. Once you’ve created a LocalDate, 
LocalTime, LocalDateTime, or ZonedDateTime, it can no longer be changed.
This has the great advantage of making them thread safe.</h4>

<h3><u>Note:</u></h3><h4>what if you want to make a new instance based on 
the existing one? The LocalDate class has several methods for adding and 
subtracting values from dates. Specifically, there are:

```java
LocalDate plusDays(long daysToAdd)
LocalDate plusWeeks(long weeksToAdd)
LocalDate plusMonths(long monthsToAdd)
LocalDate plusYears(long yearsToAdd)
```

Each method returns a new LocalDate, which is a copy of the current date 
with the specified value added to it.

The LocalTime class has similar methods:

```java
LocalTime plusNanos(long nanosToAdd)
LocalTime plusSeconds(long secondsToAdd)
LocalTime plusMinutes(long minutesToAdd)
LocalTime plusHours(long hoursToAdd)
```

Again, each returns a new instance, which is a copy of the original with the
added amount. LocalDateTime has all the methods for both LocalDate and LocalTime.</h4>

<h3><u>Note:</u></h3><h4>The classes also have two additional plus and minus 
methods. Here are the signatures for those methods in LocalDateTime:

```java
LocalDateTime plus(long amountToAdd, TemporalUnit unit)
LocalDateTime plus(TemporalAmount amountToAdd)

LocalDateTime minus(long amountToSubtract, TemporalUnit unit)
LocalDateTime minus(TemporalAmount amountToSubtract)
```

The corresponding methods in LocalDate and LocalTime are the same, with 
the corresponding return types. Interestingly enough, the minus versions 
just call the plus versions with the amounts negated.</h4>

<h3><u>Note:</u></h3><h4>For the methods that take a TemporalAmount, the 
argument is usually a Period or a Duration, but may be any type implementing
the TemporalAmount interface. That interface has methods called addTo and
subtractFrom:

```java
Temporal addTo(Temporal temporal)
Temporal subtractFrom(Temporal temporal)
```

If you follow the call stack, invoking minus delegates to plus with a negated 
argument, which delegates to TemporalAmount.addTo(Temporal), which calls back
to plus(long, TemporalUnit), which actually does the work.</h4>

<h3><u>Note:</u></h3><h4>When the API calls for TemporalUnit, remember that
the provided implementation class is ChronoUnit, which has many convenient
constants.</h4>

<h3><u>Note:</u></h3><h4>There are a series of with methods on each class 
that can be used to change one field at a time.
The signatures range from withNano to withYear, with a few interesting ones
thrown in. Here is the set from LocalDateTime:

```java
LocalDateTime withNano(int nanoOfSecond)
LocalDateTime withSecond(int second) 
LocalDateTime withMinute(int minute) 
LocalDateTime withHour(int hour) 
LocalDateTime withDayOfMonth(int dayOfMonth) 
LocalDateTime withDayOfYear(int dayOfYear) 
LocalDateTime withMonth(int month) 
LocalDateTime withYear(int year)
```

</h4>

<h3><u>Note:</u></h3><h4>Given a temporal value, you want to adjust it to a 
new one based on your own logic, or you want to retrieve information about it,
create a TemporalAdjuster or formulate a TemporalQuery.</h4>

<h3><u>Note:</u></h3><h4>The TemporalAdjuster interface provides methods 
that take a Temporal value and return an adjusted one. 
The TemporalAdjusters class contains a set of adjusters as static methods 
you might find convenient:

```java
static TemporalAdjuster firstDayOfNextMonth() 
static TemporalAdjuster firstDayOfNextYear() 
static TemporalAdjuster firstDayOfYear()
static TemporalAdjuster firstInMonth(DayOfWeek dayOfWeek) 
static TemporalAdjuster lastDayOfMonth()
static TemporalAdjuster lastDayOfYear()
static TemporalAdjuster lastInMonth(DayOfWeek dayOfWeek)
static TemporalAdjuster next(DayOfWeek dayOfWeek)
static TemporalAdjuster nextOrSame(DayOfWeek dayOfWeek) 
static TemporalAdjuster previous(DayOfWeek dayOfWeek) 
static TemporalAdjuster previousOrSame(DayOfWeek dayOfWeek)
```

</h4>

<h3><u>Note:</u></h3><h4>TemporalAdjuster is a functional interface, 
whose single abstract method is:

```java
Temporal adjustInto(Temporal temporal)
```

</h4>

<h3><u>Note:</u></h3><h4>The TemporalQuery interface has only a single 
abstract method:

```java
R queryFrom(TemporalAccessor temporal)
```

</h4>

<h3><u>Note:</u></h3><h4>If you want to convert from java.util.Date or 
java.util.Calendar to the new classes in the java.time package, use the 
Instant class as a bridge, or use java.sql.Date and java.sql.Timestamp 
methods, or even strings or integers for the conversion.</h4>

<h3><u>Note:</u></h3><h4>Conversion methods in java.sql.Date:

```java
LocalDate toLocalDate()
static Date valueOf(LocalDate date)

```

Conversion methods in java.sql.Timestamp:

```java
LocalDateTime toLocalDateTime()
static Timestamp valueOf(LocalDateTime dateTime)
```

</h4>

[Year_2038_problem](https://en.wikipedia.org/wiki/Year_2038_problem)

<h3><u>Note:</u></h3><h4>Converting between calendar instances and the new 
java.time package can be done with the toInstant method, adjusting for the 
time zone </h4>

--------------------------------------------------------------------------

