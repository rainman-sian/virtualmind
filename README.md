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
    * [Post.java](src\main\java\com\virtualmind\codingchallenge\model\Post.java)
    * [Topic.java](src\main\java\com\virtualmind\codingchallenge\model\Topic.java)

#### - B 
1. I added my answer as comments in [TopicService.java](src\main\java\com\virtualmind\codingchallenge\service\TopicService.java)

2. The HTTP request would be something like `PUT https://<domain>/topic`

3. It would be a `404 not found`

