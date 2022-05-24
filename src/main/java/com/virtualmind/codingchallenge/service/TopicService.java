package com.virtualmind.codingchallenge.service;

import com.virtualmind.codingchallenge.dto.TopicDTO;
import com.virtualmind.codingchallenge.model.Topic;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

public class TopicService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void updateTopic(long topicId, TopicDTO updated) {

        // The following sentence will trigger a `select t.* from topic t where t.id = <topicId>`
        Topic topic = entityManager.find(Topic.class, topicId);

        if (topic != null) {
            topic.setName(updated.getName());
            // above sentence will trigger a `update topic set name = <updated.getName()> were id = <topicId>` query
            // commit
        } else {
            throw new EntityNotFoundException(Topic.class +  " - " +  topicId); // changed code here in order to avoid compilation issues
            // above sentence will trigger a `rollback;` query
        }
    }
}
