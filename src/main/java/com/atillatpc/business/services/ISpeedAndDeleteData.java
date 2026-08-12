package com.atillatpc.business.services;

import java.util.List;

public interface ISpeedAndDeleteData<D, E> {

    public List<D> speedData(Integer data);
    public List<D> deleteData();
}
