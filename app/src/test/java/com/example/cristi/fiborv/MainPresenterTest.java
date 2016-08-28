package com.example.cristi.fiborv;

import com.example.cristi.fiborv.presenter.MainPresenter;
import com.example.cristi.fiborv.presenter.MainPresenterImpl;
import com.example.cristi.fiborv.ui.MainView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class MainPresenterTest {
    private static final String VALID_USER_INPUT = "100";
    private static final String INVALID_USER_INPUT = "";
    private static final String[] SEQUENCE_FOR_VALID_INPUT = new String[] {
            "0", "1", "1", "2", "3", "5", "8", "13", "21", "34", "55", "89"
    };

    @Mock
    MainView view;

    MainPresenter presenter;

    @Before
    public void setup() {
        initMocks(this);
        presenter = new MainPresenterImpl();
    }

    @Test
    public void testInvalidUserInput() {
        // GIVEN
        presenter.attach(view);
        // WHEN
        presenter.onUserInput(INVALID_USER_INPUT);
        // THEN
        verify(view).onInvalidInput();
    }

    @Test
    public void testValidUserInput() {
        // GIVEN
        presenter.attach(view);
        // WHEN
        presenter.onUserInput(VALID_USER_INPUT);
        // THEN
        verify(view).displaySequence(SEQUENCE_FOR_VALID_INPUT);
    }
}
