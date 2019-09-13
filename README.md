# IntervalUnion
Assignment for CPS 305, in where we calculate the intersection and union of sets 

Taken from the PDF of the assignment
"The Problem To Solve
The first project for the course CPS 305 Fall 2018 is to design an immutable class whose objects represent unions of intervals of positive integers. 

An integer interval is defined by its start and end values so that start <= end, and it contains all integers x for which start <= x <= end, the start and end of the interval both being inclusive. An object that represents a union of intervals can contain any number of such individual intervals. However, inside this union, two integer intervals that either are contiguous or that overlap partially or in full are supposed to meld into a single integer interval. Between any two intervals that are part of this union, there must exist at least one integer value that is between them but not part of either interval.

For example, using the canonical string representation (as defined below in the section "Required Methods"), three separate examples of unions of intervals might be

"[1-5,10-12,27,33-40]"
"[4-15,20-31,35-42]"
"[]"

where the first instance consists of four separate intervals (1-5, 10-12, 27-27, 33-40), the second instance consists of three such intervals (4-15, 20-31, 35-42), and the third instance represents an empty union that contains nothing. 

The intersection of two unions of intervals contains the integers that are included in both, encoded in the same representation. For example, the intersection of the first two example intervals would be (again converted to String representation)

"[4-5,10-12,27,35-40]"

Similarly, the union of two unions of intervals contains the integers that are included in either one or both. For example, the union of the first two example intervals would be

"[1-15,20-31,33-42]"
"

Using Java I was able to complete this assignment scoring a 100%.
More information can be seen on this assignment in the PDF located in the file.
