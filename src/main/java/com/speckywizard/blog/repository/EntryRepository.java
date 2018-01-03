package com.speckywizard.blog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.speckywizard.blog.domain.Entry;

/**
 * Spring Data JPA repository for the Entry entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {
    @Query("select distinct entry from Entry entry left join fetch entry.tags")
    List<Entry> findAllWithEagerRelationships();

    @Query("select entry from Entry entry left join fetch entry.tags where entry.id =:id")
    Entry findOneWithEagerRelationships(@Param("id") Long id);

    Page<Entry> findByBlogUserLoginOrderByDateDesc(Optional<String> currentUserLogin, Pageable pageable);
}
