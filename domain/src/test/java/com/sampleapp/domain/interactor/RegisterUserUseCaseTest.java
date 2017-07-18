package com.sampleapp.domain.interactor;

import com.sampleapp.domain.data.executor.PostExecutionThread;
import com.sampleapp.domain.data.executor.WorkExecutionThread;
import com.sampleapp.domain.repository.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class RegisterUserUseCaseTest {

    private static final String USER_NAME = "Alice";
    private RegisterUserUseCase registerUserUseCase;

    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private WorkExecutionThread mockThreadExecutor;
    @Mock
    private PostExecutionThread mockPostExecutionThread;


    @Before
    public void setUp() {
        registerUserUseCase = new RegisterUserUseCase(mockUserRepository, mockThreadExecutor, mockPostExecutionThread);
    }

    @Test
    public void testRegisterUserUseCaseObservable() {
        registerUserUseCase.buildUseCaseObservable(new RegisterUserUseCase.Parameters(USER_NAME));

        verify(mockUserRepository).addUser(USER_NAME);
        verifyNoMoreInteractions(mockUserRepository);
        verifyZeroInteractions(mockPostExecutionThread);
        verifyZeroInteractions(mockThreadExecutor);
    }
}
