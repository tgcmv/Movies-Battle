package com.battle.movie.service;

import javax.persistence.EntityNotFoundException;

public interface IWriterService<I, E> {

	E insert(E entity);

	E update(I id, E entity) throws EntityNotFoundException;

	void delete(I id) throws EntityNotFoundException;
}
