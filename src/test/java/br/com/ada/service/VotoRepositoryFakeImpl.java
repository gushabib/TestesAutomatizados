package br.com.ada.service;

import br.com.ada.entities.Voto;
import br.com.ada.repository.VotoRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class VotoRepositoryFakeImpl implements VotoRepository {
    @Override
    public void flush() {

    }

    @Override
    public <S extends Voto> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Voto> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Voto> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Voto getOne(Long aLong) {
        return null;
    }

    @Override
    public Voto getById(Long aLong) {
        return null;
    }

    @Override
    public Voto getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Voto> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Voto> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Voto> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Voto> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Voto> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Voto> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Voto, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Voto> S save(S entity) {

        System.out.println("Salvando objeto: " + entity.getNome());
        return null;
    }

    @Override
    public <S extends Voto> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Voto> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<Voto> findAll() {
        return null;
    }

    @Override
    public List<Voto> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Voto entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Voto> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Voto> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Voto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Voto> findVotoByNome(String nome) {
        return Optional.empty();
    }
}
