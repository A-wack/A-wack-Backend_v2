package org.awack.domain.dailysong.dao;

import org.awack.domain.dailysong.model.DailySong;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface DailySongRepository extends JpaRepository<DailySong, Long> {
    Page<DailySong> findAllByUserIdOrderByCreatedAtDesc(Long userId, Pageable pageable);

}
