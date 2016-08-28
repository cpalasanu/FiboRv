package com.example.cristi.fiborv.presenter;

import com.example.cristi.fiborv.ui.MainView;

public interface MainPresenter {
    void attach(final MainView view);

    void detach();

    void onUserInput(String input);
}
