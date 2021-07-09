package ru.geeekbrains.mynoteapp.domain;

public interface Callback<T> {

    void onSuccess(T result);

}
