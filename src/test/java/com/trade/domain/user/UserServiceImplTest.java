package com.trade.domain.user;

import com.trade.common.ResourceNotFoundException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
     private static final long USER_ID = 1L;

        @Mock
        private UserRepo userRepo;
        @Mock
        private UserMapper mapper;

        @InjectMocks
        private UserServiceImpl service;

        @Rule
        public ExpectedException exception = ExpectedException.none();

        @Test
        public void resourceNotFoundExceptionShouldBeThrownWhenUserNotFound() {
            when(userRepo.findOne(USER_ID)).thenReturn(null);

            exception.expect(ResourceNotFoundException.class);
            exception.expectMessage("Resource with id 1 not found");

            service.findOne(USER_ID);
        }

        @Test
        public void resourceNotFoundExceptionShouldBeThrownWhenResourceMapperReturnsNull() {
            when(userRepo.findOne(USER_ID)).thenReturn(new User());
            when(mapper.map(any(User.class))).thenReturn(null);

            exception.expect(ResourceNotFoundException.class);
            exception.expectMessage("Resource with id 1 not found");

            service.findOne(USER_ID);
        }

        @Test
        public void resourceNotFoundExceptionShouldBeThrownWhenResourceIdNull() {
            exception.expect(ResourceNotFoundException.class);
            exception.expectMessage("Resource id is null");

            service.findOne(null);
        }
    }
