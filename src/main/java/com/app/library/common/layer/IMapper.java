package com.app.library.common.layer;

public interface IMapper<M extends IModel, E extends IEntity> {
    M toModel(E entity);

    E toEntity(M model);

}