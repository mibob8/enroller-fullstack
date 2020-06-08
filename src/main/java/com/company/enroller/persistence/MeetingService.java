package com.company.enroller.persistence;

import com.company.enroller.model.Meeting;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component("meetingService")
public class MeetingService {

    DatabaseConnector connector;
    Transaction transaction;

    public MeetingService() {
        connector = DatabaseConnector.getInstance();
        transaction = connector.getSession().beginTransaction();
    }

    public Collection<Meeting> getAll() {
        String hql = "FROM Meeting";
        Query query = connector.getSession().createQuery(hql);
        return query.list();
    }

    public Meeting addMeeting(Meeting meeting){
        connector.getSession().save(meeting);
        transaction = connector.getSession().beginTransaction();
        transaction.commit();
        return meeting;
    }

    public Meeting findById(long id){
        String hql = "FROM Meeting WHERE id = " + id;
        return (Meeting) connector.getSession().createQuery(hql).uniqueResult();
    }

    public void deleteMeeting(Meeting meeting){
        connector.getSession().delete(meeting);
        transaction = connector.getSession().beginTransaction();
        transaction.commit();
    }
}
