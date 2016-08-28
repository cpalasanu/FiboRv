package com.example.cristi.fiborv.presenter;

import com.example.cristi.fiborv.data.FiboSource;
import com.example.cristi.fiborv.ui.MainView;

import java.util.List;

public class MainPresenterImpl implements MainPresenter {
    private MainView view;

    @Override
    public void attach(MainView view) {
        this.view = view;
    }

    @Override
    public void detach() {
        view = null;
    }

    @Override
    public void onUserInput(String input) {
        try {
            int nValue = Integer.parseInt(input);
            List<Integer> newFiboSequence = FiboSource.getFibonacciSequence(nValue);
            view.displaySequence(intListToStringArray(newFiboSequence));
        } catch (NumberFormatException e) {
            view.onInvalidInput();
        }
    }

    private String[] intListToStringArray(List<Integer> list) {
        String[] arr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = String.valueOf(list.get(i));
        }

        return arr;
    }
}
