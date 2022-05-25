# Virtualmind coding challenge
## Rainman Sián [[rainman.sian@gmail.com](mailto:rainman.sian@gmail.com)]


### 1) SQL

#### - A
~~~~sql
select * 
from topic 
order by name limit 10;
~~~~

#### - B
~~~~sql
select t.name, count(p.topic_id) as posts 
from topic t inner join post p on t.id = p.topic_id
group by t.name
having posts >= 5;
~~~~

#### - C
~~~~sql
select t.*
from topic t left join post p on t.id = p.topic_id
where p.topic_id is null;
~~~~

### 2) JAVA

#### - A models:
Check my models in the following java classes:
* [Post.java](src/main/java/com/virtualmind/codingchallenge/model/Post.java)
* [Topic.java](src/main/java/com/virtualmind/codingchallenge/model/Topic.java)

#### - B 
1. I added my answer as comments in [TopicService.java](src/main/java/com/virtualmind/codingchallenge/service/TopicService.java)

2. The HTTP request would be something like `PUT https://<domain>/topic`

3. It would be a `404 not found`

#### - C

- Check my answer in [PostService.java](src/main/java/com/virtualmind/codingchallenge/service/PostService.java) at `listPostTitlesAndTopics` function.

- For my fix, check `listPostTitlesAndTopics2` function also in [PostService.java](src/main/java/com/virtualmind/codingchallenge/service/PostService.java). Notice that this function uses [PostRepository.java](src/main/java/com/virtualmind/codingchallenge/repository/PostRepository.java).


#### - D

- Check my answer in [DateBucket.java](src/main/java/com/virtualmind/codingchallenge/utils/DateBucket.java) at javadoc section of `bucketsize` function

- Refactored the method in [DateBucket.java](src/main/java/com/virtualmind/codingchallenge/utils/DateBucket.java) called `bucketSizeStreamsVersion` function


#### - E

> One of the most common patterns I have used lately is the "Addapter pattern". Mostly because my project uses a lot of third API endpoints; In some scenarios we use the data as it comes from the API, but in anothers, the data is expected in different form.

> So, to solve this, we have defined a few Adapter interfaces, we adapt the data as to is expected for the different consumers we have. This is not a complex patter, but we have found it very useful and easy to maintain.

### 3) Modeling

#### - A Class diagram:

![Alt](/src/main/resources/static/img/3-a.png "Class Diagram")

#### - B Class diagram:

> I would create an Factory class that would have the responsibility  of create all difirent kind of expenses.

> I would store the expenses as a list in Building class.

![Alt](/src/main/resources/static/img/3-b.png "Class Diagram")

#### - C Class diagram:

> I would create an ExpenseManager class that would have the responsibility  of the settlement process that would contain the following functions:
    
- `calculateTotalExpenditure(fromDate, toDate, expensesList): BigDecimal` This will calculate the total expenditure of a period between `fromDate` and `endDate`. This function also recibe the list of expenses (`expensesList`) from `Building` class. The result of this function will be the total amount of expenses.

- `calculateTotalExpenditureByApartment(totalExpenditure, nApartment): BigDecimal` This will calculate the corresponding amout for each apartment. The calculation is based on the total expenditure `totalExpenditure`(from previous function) and the number of apartments `nApartment`

- `issueBill(owner:Owner) : Bill` This will issue the bill for each `owner`. The actually bill creation will be delegate to the `Bill` class

- `notify(owner:Owner): Void` This will notify the owners. This method would use another class (not modeled in diagram) that creates an instace of the type of required notification, e.g.: EmailNotification, SMSNotificaion, PushNotification, etc.

![Alt](/src/main/resources/static/img/3-c.png "Class Diagram")