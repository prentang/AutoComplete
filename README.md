# AutoComplete
The purpose of this project is to write a program to implement autocomplete for a given set of n strings and nonnegative weights.


Autocomplete is an important feature of many modern applications. As the user types, the program predicts the complete
query (typically a word or phrase) that the user intends to type. Autocomplete is most effective when there are a limited
number of likely queries. For example, the Internet Movie Database uses it to display the names of movies as the user types;
search engines use it to display suggestions as the user enters web search queries; cell phones use it to speed up text input.


In these examples, the application predicts how likely it is that the user is typing each query and presents to the user a list of
the top-matching queries, in descending order of weight. These weights are determined by historical data, such as box office
revenue for movies, frequencies of search queries from other Google users, or the typing history of a cell phone user. For the
purposes of this assignment, you will have access to a set of all possible queries and associated weights (and these queries
and weights will not change).

The performance of autocomplete functionality is critical in many systems. For example, consider a search engine which runs
an autocomplete application on a server farm. According to one study, the application has only about 50ms to return a list
of suggestions for it to be useful to the user. Moreover, in principle, it must perform this computation for every keystroke
typed into the search bar and for every user !

I will implement autocomplete by sorting the queries in lexicographic order; using binary search to
find the set of queries that start with a given prefix; and sorting the matching queries in descending order by weight.
