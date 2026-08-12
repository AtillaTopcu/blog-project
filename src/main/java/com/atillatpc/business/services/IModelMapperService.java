package com.atillatpc.business.services;

public interface IModelMapperService<D, E> {

    public D entityToDto(E e);
    public E dtoToEntity(D d);
}
