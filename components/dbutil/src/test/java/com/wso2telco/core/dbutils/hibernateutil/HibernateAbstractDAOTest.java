package com.wso2telco.core.dbutils.hibernateutil;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.*;

public class HibernateAbstractDAOTest {

    @Mock HibernateAbstractDAO hibernateAbstractDAO;

    @BeforeMethod
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        doCallRealMethod().when(hibernateAbstractDAO).save(anyObject());
        doCallRealMethod().when(hibernateAbstractDAO).saveOrUpdate(anyObject());
        doCallRealMethod().when(hibernateAbstractDAO).saveList(anyList());
        doCallRealMethod().when(hibernateAbstractDAO).saveOrUpdateList(anyList());

    }


    @Test
    public void testSave_whenThereIsNoException() throws Exception {
        Session session = Mockito.mock(Session.class);
        Transaction transaction =Mockito.mock(Transaction.class);
        when(session.beginTransaction()).thenReturn(transaction);
        when(hibernateAbstractDAO.getSession()).thenReturn(session);
        hibernateAbstractDAO.save(anyObject());
        verify(session,times(1)).save(anyObject());
        verify(transaction,times(1)).commit();

    }

    @Test
    public void testSaveOrUpdate_whenThereIsNoException() throws Exception {
        Session session = Mockito.mock(Session.class);
        Transaction transaction =Mockito.mock(Transaction.class);
        when(session.beginTransaction()).thenReturn(transaction);
        when(hibernateAbstractDAO.getSession()).thenReturn(session);
        hibernateAbstractDAO.saveOrUpdate(anyObject());
        verify(session,times(1)).saveOrUpdate(anyObject());

    }

    @Test
    public void testSaveList_whenThereIsNoException_ShouldCallTimesEqualToListLength() throws Exception {
        Session session = Mockito.mock(Session.class);
        Transaction transaction =Mockito.mock(Transaction.class);
        when(session.beginTransaction()).thenReturn(transaction);
        when(hibernateAbstractDAO.getSession()).thenReturn(session);
        List<Object> ls = anyList();
        hibernateAbstractDAO.saveList(ls);
        verify(session,times(ls.size())).save(anyObject());
    }

    @Test
    public void testSaveOrUpdateList() throws Exception {
        Session session = Mockito.mock(Session.class);
        Transaction transaction =Mockito.mock(Transaction.class);
        when(session.beginTransaction()).thenReturn(transaction);
        when(hibernateAbstractDAO.getSession()).thenReturn(session);
        List<Object> ls = anyList();
        hibernateAbstractDAO.saveOrUpdateList(ls);
        verify(session,times(ls.size())).save(anyObject());
    }
}