frequency-counter
=================

### Stage 1. Simple implementation
[Here](https://github.com/radik/frequency-counter/tree/v0.1) you can see simple implementation of a frequency counting algorithm. The core idea is to iterate through all items and count their frequency.

Main drawbacks:
* Hard coded for Strings. What if we'll need count frequency of objects with different type?
* After the iterator's exhausted, all items stored in memory. What if iterator has more different objects than we can store in RAM?
* This implementation is not scalable - all work happens in one thread.

### Stage 2. Generalization
[Here](https://github.com/radik/frequency-counter/tree/v0.2) you can see generic implementation of a frequency counting algorithm. The first drawback from previous implementation is resolved. Second and third are still actual.
