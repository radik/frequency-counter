frequency-counter
=================

### Stage 1. Simple implementation
[v0.1 tag](https://github.com/radik/frequency-counter/tree/v0.1) snapshot after implementation of simple frequency counting algorithm. The core idea is to iterate through all items and count their frequency.

Main drawbacks:
* Hard coded for Strings. What if we'll need count frequency of objects with different type?
* After the iterator's exhausted, all items stored in memory. What if iterator has more different objects than we can store in RAM?
* This implementation is not scalable - all work happens in one thread.

### Stage 2. Generalization
[v0.2 tag](https://github.com/radik/frequency-counter/tree/v0.2) snapshot after generic implementation of a frequency counting algorithm. The first drawback from previous implementation is resolved. Second and third are still actual.

### Stage 3. Stored frequency counter
[v0.3 tag](https://github.com/radik/frequency-counter/tree/v0.3) snapshot after implementation of a frequency counting algorith, which uses external SQL-database for counting elements frequency. Class needs working java.sql.Connection for database usage. Internally used plain jdbc and sql statements. These statements tested on PostgreSQL and sqlite3. Next step for this approach - wrap database routine with some abstraction (I didn't do that, this is just some thoughts).

### Stage 4. Parallelization
[v0.4 tag](https://github.com/radik/frequency-counter/tree/v0.4) snapshot after implementation of a frequency counting algorith, which uses ForkJoinPull for parallelizing computing. This approach allows some scaling. Next step for this approach - real map-reduce or using actors model (akka.io library).

### Stage 5. Profiling and improving
[v0.4.1 tag](https://github.com/radik/frequency-counter/tree/v0.4.1) snapshot - improving StoredFrequencyCounter after profiling. After profiling StoredFrequencyCounter class, I found that prepared statements created for each 'read' method call. Moving them to class fields gave 30% speed improving.
