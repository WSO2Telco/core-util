package com.wso2telco.core.mi.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.assertj.core.api.Assertions;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Copyright (c) 2016, WSO2.Telco Inc. (http://www.wso2telco.com) All Rights Reserved.
 * <p>
 * WSO2.Telco Inc. licences this file to you under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class HibernateAbstractDAOTest {

    @Mock private Transaction transactionMock;
    @Mock private Session sessionMock;
    @Mock private HibernateAbstractDAO hibernateDaoImpl;

    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        when(sessionMock.beginTransaction()).thenReturn(transactionMock);
        when(hibernateDaoImpl.getSession()).thenReturn(sessionMock);
        doCallRealMethod().when(hibernateDaoImpl).saveOrUpdate(anyObject());
        doCallRealMethod().when(hibernateDaoImpl).saveOrUpdateList(anyListOf(Object.class));
    }

    @Test
    public void testSaveOrUpdate_shouldSaveObjectAndCommitTransaction_whenThereIsNoExceptionThrown() throws Exception {
        Object objectMock = Mockito.mock(Object.class);
        hibernateDaoImpl.saveOrUpdate(objectMock);

        verify(sessionMock, times(1)).beginTransaction();
        verify(sessionMock, times(1)).saveOrUpdate(objectMock);
        verify(transactionMock, times(1)).commit();
    }

    @Test
    public void testSaveOrUpdate_shouldRollbackTransaction_whenExceptionThrown() {
        doThrow(new RuntimeException()).when(sessionMock).saveOrUpdate(anyObject());

        Object objectMock = Mockito.mock(Object.class);
        Assertions.assertThat(Assertions.catchThrowable(() -> hibernateDaoImpl.saveOrUpdate(objectMock))).isInstanceOf(RuntimeException.class);

        verify(transactionMock, times(1)).rollback();
    }

    @Test
    public void testSaveOrUpdateList_shouldSaveObjectListAndCommitTransaction_whenThereIsNoExceptionThrown() throws Exception {
        List<Object> objectList = new ArrayList<>();
        IntStream.rangeClosed(1, 3).forEach(i -> objectList.add(Mockito.mock(Object.class)));
        hibernateDaoImpl.saveOrUpdateList(objectList);

        verify(sessionMock, times(1)).beginTransaction();
        verify(sessionMock, times(objectList.size())).saveOrUpdate(anyObject());
        verify(transactionMock, times(1)).commit();
    }

    @Test
    public void testSaveOrUpdateList_shouldRollbackTransaction_whenExceptionThrown() {
        doThrow(new RuntimeException()).when(sessionMock).saveOrUpdate(anyObject());

        List<Object> objectList = new ArrayList<>();
        IntStream.rangeClosed(1, 3).forEach(i -> objectList.add(Mockito.mock(Object.class)));
        Assertions.assertThatThrownBy(() -> hibernateDaoImpl.saveOrUpdateList(objectList)).isInstanceOf(RuntimeException.class);

        verify(transactionMock, times(1)).rollback();
    }
}